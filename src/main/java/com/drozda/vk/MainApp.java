package com.drozda.vk;

import com.drozda.vk.controller.AudioController;
import com.drozda.vk.controller.AuthorizationController;
import com.sun.istack.internal.NotNull;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by GFH on 30.08.2015.
 */
public class MainApp extends Application {

    public static String PRIMARY_STAGE = "PRIMARY_STAGE";
    public static String AUTH_STAGE = "AUTH_STAGE";
    public static String AUDIO_STAGE = "AUDIO_STAGE";
    public static String FXML_PATH = "fxml/";
    public static String MAIN_CONTROLLER = "MainApp";
    public static String AUTH_CONTROLLER = "Authorization";
    public static String AUDIO_CONTROLLER = "Audio";
    public static int APP_ID = XXX;
    public static String TOKEN;
    public static String USER_ID;
    public static String CUR_API = "5.37";
    private static MainApp instance;
    private Map<String, Scene> sceneMap = new HashMap();
    private Map<String, Stage> stageMap = new HashMap();
    private Map<String, Object> controllerMap = new HashMap();

    public static boolean isLoggedIn() {
        return TOKEN != null;
    }

    public static MainApp getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Object getController(@NotNull String sceneName) {
        getScene(sceneName);
        return controllerMap.get(sceneName);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        stageMap.put(PRIMARY_STAGE, primaryStage);
        changeScene(PRIMARY_STAGE, MAIN_CONTROLLER);
        showStage(PRIMARY_STAGE);
    }

    public void changeScene(@NotNull String stageName, @NotNull String sceneName) {
        getStage(stageName).setScene(getScene(sceneName));
    }

    public void showStage(@NotNull String stageName) {
        showStage(stageName, false);
    }

    public Stage getStage(@NotNull String stageName) {
        if (!stageMap.containsKey(stageName)) {
            Stage stage = new Stage();
            stageMap.put(stageName, stage);
        }
        return stageMap.get(stageName);
    }

    public Scene getScene(@NotNull String sceneName) {
        if (!sceneMap.containsKey(sceneName)) {
            loadScene(sceneName);
        }
        return sceneMap.get(sceneName);
    }

    public void showStage(@NotNull String stageName, boolean isModal) {
        Stage stage = getStage(stageName);
        if (isModal) {
            if (stage.getModality() != Modality.APPLICATION_MODAL) {
                getStage(stageName).initModality(Modality.APPLICATION_MODAL);
            }
        }
        stage.show();
    }

    private void loadScene(@NotNull String s) {
        try {
            String fxmlFile = FXML_PATH + s + ".fxml";
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            sceneMap.put(s, new Scene(root));
            controllerMap.put(s, loader.getController());
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }

    }

    public void changeScene(@NotNull String sceneName) {
        changeScene(PRIMARY_STAGE, sceneName);
    }

    public void login() {
        changeScene(MainApp.AUTH_STAGE, MainApp.AUTH_CONTROLLER);
        showStage(MainApp.AUTH_STAGE, true);
        WebEngine webEngine = ((AuthorizationController) getController(AUTH_CONTROLLER)).getEngine();
        webEngine.load("https://oauth.vk.com/authorize?" +
                "client_id=" + APP_ID + "&" +
                "scope=audio&" +
                "redirect_uri=http://oauth.vk.com/blank.html&" +
                "display=popup&" +
                "response_type=token" +
                "&v=5.37");
        //  System.out.println(webEngine.getLocation());
    }

    public void showAudio() {
        changeScene(AUDIO_STAGE, AUDIO_CONTROLLER);
        showStage(AUDIO_STAGE);
        ((AudioController) getController(AUDIO_CONTROLLER)).getAudio();
    }
}
