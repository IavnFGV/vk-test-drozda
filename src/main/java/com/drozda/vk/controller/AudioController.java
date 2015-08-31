package com.drozda.vk.controller;

import com.drozda.vk.MainApp;
import com.drozda.vk.Utils;
import com.drozda.vk.model.VKAudio;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GFH on 31.08.2015.
 */
public class AudioController {

    private int audioCount;
    private List<VKAudio> vkAudios = new ArrayList<VKAudio>();
    @FXML
    private WebView browser;

    public void getAudio() {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(Utils.getUriAudio());

        ResponseHandler<VKAudio> rh = new ResponseHandler<VKAudio>() {
            public VKAudio handleResponse(HttpResponse httpResponse) throws IOException {
                StatusLine statusLine = httpResponse.getStatusLine();
                HttpEntity entity = httpResponse.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("NO CONTENT");
                }
//                ContentType contentType = ContentType.getOrDefault(entity);
//                Charset charset = contentType.getCharset();
//                Reader reader = new InputStreamReader(entity.getContent(), charset);
                StringBuilder sb = new StringBuilder(EntityUtils.toString(entity));

                JSONObject obj = new JSONObject(sb.toString());
                JSONObject jsonResponse = obj.getJSONObject("response");
                setAudioCount(jsonResponse.getInt("count"));
                JSONArray items = jsonResponse.getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    vkAudios.add(VKAudio.createFromJSON(items.getJSONObject(i)));
                }

                return vkAudios.get(3);
            }
        };
        try {
            VKAudio myjson = httpclient.execute(httpget, rh);
            System.out.println(myjson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAudio3() {
        WebEngine webEngine = browser.getEngine();
        webEngine.load("https://api.vk.com/method/audio.get?owner_id=" + MainApp
                .USER_ID + "&need_user=0&access_token=" + MainApp.TOKEN + "&v=5.37");
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

    public void getAudio2() {
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

        ResponseHandler<VKAudio> rh = new ResponseHandler<VKAudio>() {
            public VKAudio handleResponse(HttpResponse httpResponse) throws IOException {
                StatusLine statusLine = httpResponse.getStatusLine();
                HttpEntity entity = httpResponse.getEntity();
                if (statusLine.getStatusCode() >= 300) {
                    throw new HttpResponseException(
                            statusLine.getStatusCode(),
                            statusLine.getReasonPhrase());
                }
                if (entity == null) {
                    throw new ClientProtocolException("NO CONTENT");
                }
                // Gson gson = new GsonBuilder().create();
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                Reader reader = new InputStreamReader(entity.getContent(), charset);
                StringBuilder sb = new StringBuilder(EntityUtils.toString(entity));

                JSONObject obj = new JSONObject(sb.toString());
                JSONObject jsonResponse = obj.getJSONObject("response");
                AudioController.this.setAudioCount(jsonResponse.getInt("count"));

                System.out.print("static audiocount = " + getAudioCount());

                System.out.println(jsonResponse.getJSONArray("items"));


                System.out.println("Count = " + sb.substring(13, sb.indexOf(",")));
//                AudioController.this.setAudioCount(Integer.parseInt(sb.substring(13, sb.indexOf(","))));
                //    System.out.println(sb.substring(sb.indexOf(",")+1));
                return null;
            }
        };
        try {
            VKAudio myjson = httpclient.execute(httpget, rh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(int audioCount) {
        this.audioCount = audioCount;
    }
}