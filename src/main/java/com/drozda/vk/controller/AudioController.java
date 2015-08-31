package com.drozda.vk.controller;

import com.drozda.vk.Utils;
import com.drozda.vk.model.VKAudio;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GFH on 31.08.2015.
 */
public class AudioController {

    private int audioCount;
    private List<VKAudio> vkAudios = new ArrayList<VKAudio>();

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


    public int getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(int audioCount) {
        this.audioCount = audioCount;
    }
}