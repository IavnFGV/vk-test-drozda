package com.drozda.vk.controller;

import com.drozda.vk.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by GFH on 30.08.2015.
 */
public class MainAppController {


    @FXML
    private Button logInButton;

    @FXML
    private Button audioButton;

    @FXML
    private void handleLogInPress() {
        MainApp mainApp = MainApp.getInstance();
        mainApp.login();
    }

    @FXML
    private void handleAudioPress() {
        MainApp mainApp = MainApp.getInstance();
        mainApp.showAudio();
    }

}
