package com.pos.components;

import com.pos.models.ProductoEntity;
import com.pos.store.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ProductCard extends VBox {

	Label nameLabel;
	Label total;
	Label stock;
	Pane imagen; 	
	Button btn;
	Spinner<Integer> spinner;

	ProductoEntity producto;

	public ProductCard(State store, ProductoEntity producto) {

		this.producto = producto;

		// Label del nombre de producto
		nameLabel = new Label(producto.getNombre());
		nameLabel.getStyleClass().add("font-16");

		// Si las existencias son 0, simplemente mandamos un mensaje de que no hay existencias y salimos
		if(producto.getStock()-1 < 0) {

			getStyleClass().add("item-card");
			setAlignment(Pos.CENTER);
			setPadding(new Insets(10, 10, 15, 10));
			setSpacing(15);

			getChildren().add(nameLabel);
			getChildren().add(new Label("Existencias agotadas"));
			return;
		}

		// Imagen pendiente
		imagen = new Pane();
		imagen.getStyleClass().add("imagen");

		// Label para el stock de productos
		stock = new Label(Integer.toString(producto.getStock()-1));
		stock.getStyleClass().add("font-16");

		// Label para el costo total
		total = new Label("$" + Integer.toString(producto.getPrecio()));
		total.getStyleClass().add("font-14");

		// Boton para agregar al sale panel
	    btn = new Button("Agregar");
		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e)
			{
				int cant = spinner.getValue();
				int totalToLine = Integer.parseInt(total.getText().replace("$", ""));

				store.setLine(cant, totalToLine, producto);
				store.pushLine();

				stock.setText(Integer.toString(producto.getStock()-spinner.getValue()-1));
				producto.setStock(producto.getStock()-spinner.getValue());
				spinner.getValueFactory().setValue(1);

				if(producto.getStock() == 0) {
					getChildren().clear();
					getChildren().add(nameLabel);
					getChildren().add(new Label("Existencias Agotadas"));
				}
			}
		};
		// Setting event
		btn.setOnAction(event);

		int maxValue = producto.getStock();
		spinner = new Spinner<Integer>(1, 100, 1);
		// Spinner de cantidad de la tarjeta
	    spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
	    spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (producto.getStock()-newValue >= 0) {
				stock.setText(Integer.toString(producto.getStock() - newValue));
				total.setText("$" + Integer.toString(producto.getPrecio()*newValue));
			}
			else {
				spinner.decrement(1);
			}
		});

	    VBox bottomPanel = new VBox();

	    bottomPanel.getChildren().add(stock);
	   	bottomPanel.getChildren().add(spinner);
	    bottomPanel.getChildren().add(total);
	    bottomPanel.getChildren().add(btn);

	    bottomPanel.setAlignment(Pos.CENTER);
	    bottomPanel.setPadding(new Insets(5, 0, 5, 0));
	    bottomPanel.setSpacing(8);

	    getStyleClass().add("item-card");
	    setAlignment(Pos.CENTER);
	    setPadding(new Insets(10, 10, 15, 10));
	    setSpacing(15);

	    getChildren().add(nameLabel);
	    getChildren().add(imagen);
	    getChildren().add(bottomPanel);
	}

	public ProductoEntity getProduct() {
		return this.producto;
	}

	public void setTotal(String total) {
		this.total.setText(total);
	}
}