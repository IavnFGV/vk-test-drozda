package com.drozda.vk.controller;

import com.drozda.vk.MainApp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSException;

/**
 * Created by GFH on 30.08.2015.
 */
public class AuthorizationController {

    @FXML
    private WebView browser;
    private Stage stage;

    public WebEngine getEngine() {
        return browser.getEngine();
    }

    @FXML
    private void initialize() {
        final WebEngine webEngine = browser.getEngine();
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                        System.out.println(newValue);
                        if (newValue == Worker.State.SUCCEEDED) {
                            adjustSize();
                            System.out.println(webEngine.getLocation());
                            String s = webEngine.getLocation();
                            if (s.contains("#access_token")) {
                                String[] a = s.split("#")[1].split("&");
                                MainApp.TOKEN = a[0].split("=")[1];
                                System.out.println("TOKEN" + MainApp.TOKEN);
                                MainApp.USER_ID = a[2].split("=")[1];
                                System.out.println("ISER_ID" + MainApp.USER_ID);
                                stage = (Stage) browser.getScene().getWindow();
                                stage.close();
                            }
                        }
                    }
                });
    }

    private void adjustSize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Stage stage = MainApp.getInstance().getStage(MainApp.AUTH_STAGE);
                    Object result = browser.getEngine().executeScript(
                            "document.body.scrollHeight"

//                            "document.getElementById('mydiv').offsetHeight"
                    );
                    if (result instanceof Integer) {
                        Integer i = (Integer) result;
                        double height = new Double(i);
                        height = height + 20;
                        stage.setHeight(height);
                    }
                    result = browser.getEngine().executeScript(
                            "document.body.scrollWidth"

//                            "document.getElementById('mydiv').offsetHeight"
                    );
                    if (result instanceof Integer) {
                        Integer i = (Integer) result;
                        double width = new Double(i);
                        width = width + 20;
                        stage.setWidth(width);
                    }
                } catch (JSException e) {
                    // not important
                }
            }
        });
    }
}

