package com.pos.components;

import com.pos.store.State;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SalePanel extends GridPane {

	public SalePanel(State store) {

        this.getStyleClass().add("pay-info");

		// Creating Rows and coulumn
		RowConstraints row25 = new RowConstraints();
		row25.setPercentHeight(10);

		RowConstraints row50 = new RowConstraints();
		row50.setPercentHeight(80);

		ColumnConstraints col100 = new ColumnConstraints();
		col100.setPercentWidth(100);

		this.getRowConstraints().addAll(row25, row50, row25);
		this.getColumnConstraints().addAll(col100);

		// Title label
        Label title = new Label("Total");
        title.getStyleClass().add("font-20");

        // Title box
        VBox header = new VBox();
		header.getStyleClass().add("sale-header");
		header.getChildren().add(title);
	    header.setAlignment(Pos.CENTER);

		this.add(header, 0, 0);
		this.add(store.getProductsContainer(), 0, 1);
	}
}