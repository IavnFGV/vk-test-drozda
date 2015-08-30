package com.drozda.vk.controller;

import com.drozda.vk.MainApp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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


}

