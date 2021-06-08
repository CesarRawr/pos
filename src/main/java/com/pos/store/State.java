package com.pos.store;

import com.pos.components.ProductCard;
import com.pos.components.ProductsContainer;
import com.pos.controllers.Productos;
import com.pos.models.InvoiceEntity;
import com.pos.models.LineEntity;
import com.pos.models.ProductoEntity;

import java.util.ArrayList;
import java.util.List;

public class State {

	State currentStatus = this;

	// El tipo sirve para saber que tipo de lista se va a refrescar u obtener
	String type = "default";

	// Productos Abarrotes
	List<ProductoEntity> abarrotesList = Productos.getAbarrotes();
	List<ProductCard> abarrotesCards = new ArrayList<>();

	// Producto Bebidas
	List<ProductoEntity> bebidasList = Productos.getBebidas();

	// Producto Botanas
	List<ProductoEntity> botanasList = Productos.getBotanas();

	// Datos actuales 
	LineEntity line;

	// Información de factura para bd
    InvoiceEntity invoice = new InvoiceEntity();

    // Contenedor de productos
    ProductsContainer productsContainer = new ProductsContainer();

    // ---------------Type--------------
		// Settear el tipo
	    public void setType(String type) {
	    	this.type = type;
	    }
	    
    // +++++++++++++++Type++++++++++++++    

    // ---------------Listas--------------
		// Refresca una lista dependiendo del tipo actual
	    public void refreshList() {
	    	switch (this.type) {
	    		case "abarrotes":
	    			this.abarrotesList = Productos.getAbarrotes();
	    			break;
	    		case "bebidas":
	    			this.bebidasList = Productos.getBebidas();
	    			break;
	    		case "botanas":
	    			this.botanasList = Productos.getBotanas();
	    			break;
				default:
					throw new IllegalStateException("Unexpected value: " + type);
			}
	    }

	    // Regresa la lista del tipo actual
	    public List<ProductoEntity> getProductList() {
			List<ProductoEntity> listTo = new ArrayList();
	    	switch (this.type){
	    		case "abarrotes":
	    			listTo = this.abarrotesList;
	    			break;
	    		case "bebidas":
					listTo = this.bebidasList;
	    			break;
	    		case "botanas":
					listTo = this.botanasList;
	    			break;
				default:
					throw new IllegalStateException("Unexpected value: " + type);
			}
	    	return listTo;
	    }
	    
    // +++++++++++++++Listas++++++++++++++

	// ---------------Linea--------------

	public void pushAbarroteCard(ProductCard card) {
	    	this.abarrotesCards.add(card);
	}

	public List<ProductCard> getAbarrotesCards() {
		return this.abarrotesCards;
	}

	// +++++++++++++++Linea++++++++++++++

    // ---------------Linea--------------

	    public void setLine(int cant, int total, ProductoEntity product) {
	    	this.line = new LineEntity();
	    	this.line.setCant(cant);
	    	this.line.setTotal(total);
	    	this.line.setProduct(product);
	    }

	    public LineEntity getCurrentLine() {
	    	return this.line;
		}
	    
    // +++++++++++++++Linea++++++++++++++

    // ---------------Invoice--------------
	    public List<LineEntity> getILines() {
	    	return this.invoice.getLines();
	    }

	    public void setILines(List<LineEntity> lines) {
	    	this.invoice.setLines(lines);
	    }
    // +++++++++++++++Invoice++++++++++++++

    // ---------------Contenedor de productos--------------
	    public ProductsContainer getProductsContainer() {
	    	return this.productsContainer;
	    }

	    public void pushLine() {
	    	this.productsContainer.addProduct(currentStatus);
	    }
    // +++++++++++++++Contenedor de productos++++++++++++++

}