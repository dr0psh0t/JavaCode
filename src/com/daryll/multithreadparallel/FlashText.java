package com.daryll.multithreadparallel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//  Flash Text
public class FlashText extends Application {
    private String text = "";
    private Label lblText;

    @Override
    public void start(Stage primaryStage) {
        StackPane pane = new StackPane();
        lblText = new Label("TEST");

        pane.getChildren().add(lblText);

        new Thread(new FlashTask()).start();

        Scene scene = new Scene(pane, 200, 50);
        primaryStage.setTitle("FlashText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class FlashTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {

                    if (lblText.getText().trim().length() == 0) {
                        text = "Welcome";
                    } else {
                        text = "";
                    }

                    Platform.runLater(new SetTextTask());

                    Thread.sleep(500);
                }

            } catch (InterruptedException ex) {}
        }
    }

    class SetTextTask implements Runnable {
        @Override
        public void run() {
            lblText.setText(text);
        }
    }
}
