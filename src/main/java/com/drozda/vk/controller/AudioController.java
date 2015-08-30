package com.drozda.vk.controller;

import com.drozda.vk.MainApp;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by GFH on 31.08.2015.
 */
public class AudioController {
    @FXML
    private WebView browser;


    public void getAudio() {
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://api.vk.com/method/audio.get?owner_id=" + MainApp
                .USER_ID + "&need_user=0&access_token=" + MainApp.TOKEN);
    }

    public void getAudio1() {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.vk.com")
                    .setPath("/method/audio.get")
                    .setParameter("owner_id", MainApp.USER_ID)
                    .setParameter("need_user", "0")
                    .setParameter("access_token", MainApp.TOKEN)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
