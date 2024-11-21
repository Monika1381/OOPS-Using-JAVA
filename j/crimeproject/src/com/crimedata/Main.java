package com.crimedata;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the Dashboard FXML as the main entry page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/crimedata/views/Main.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Crime Data Analysis System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
