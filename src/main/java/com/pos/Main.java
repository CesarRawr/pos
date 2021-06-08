package com.pos;

import com.pos.store.State;

import com.pos.components.PanelGrid;
import com.pos.components.ProductsContainer;
import com.pos.components.SalePanel;
import com.pos.controllers.Productos;

import javafx.application.Application;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    State store = new State();

    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Test app");

        // Contenedor de productos
        SalePanel salePanel = new SalePanel(store);

        //Panel Abarrotes
        store.setType("abarrotes");
        PanelGrid panelAbarrotes = new PanelGrid(store);

        // Panel Bebidas
        store.setType("bebidas");
        PanelGrid panelBebidas = new PanelGrid(store);

        // Panel Botanas
        store.setType("botanas");
        PanelGrid panelBotanas = new PanelGrid(store);

        store.setType("default");

        // +++++++++++++++++++++++++++++ TABS +++++++++++++++++++++++++++++
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tab1 = new Tab("Abarrotes", panelAbarrotes);
        Tab tab2 = new Tab("Bebidas", panelBebidas);
        Tab tab3 = new Tab("Botanas", panelBotanas);

        tabPane.getTabs().add(tab1);
        tabPane.getTabs().add(tab2);
        tabPane.getTabs().add(tab3);

        VBox tabsContainer = new VBox(tabPane);
        tabsContainer.setVgrow(tabPane, Priority.ALWAYS);
        tabsContainer.getStyleClass().add("tabs-container");
        // ---------------------------- TABS ----------------------------

        // +++++++++++++++++++++++++++++ SETTING MAIN PANE +++++++++++++++++++++++++++++
            GridPane mainContainer = new GridPane();
            mainContainer.getStyleClass().add("main");
            mainContainer.setGridLinesVisible(true);

            ColumnConstraints tabsColumn = new ColumnConstraints();
            tabsColumn.setPercentWidth(70);

            RowConstraints mainRow = new RowConstraints();
            mainRow.setPercentHeight(100);

            ColumnConstraints payColumn = new ColumnConstraints();
            payColumn.setPercentWidth(30);

            mainContainer.getColumnConstraints().addAll(tabsColumn, payColumn);
            mainContainer.getRowConstraints().addAll(mainRow);

            mainContainer.add(tabsContainer, 0, 0);
            mainContainer.add(salePanel, 1, 0);
        // ---------------------------- SETTING MAIN PANE ----------------------------

        // +++++++++++++++++++++++++++++ LAST SETTINGS +++++++++++++++++++++++++++++
            Scene scene = new Scene(mainContainer, 1080, 680);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        // ---------------------------- LAST SETTINGS ----------------------------
    }

    public static void main(String[] args) {
        launch(args);
    }
}