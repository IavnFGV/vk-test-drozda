package com.drozda.vk;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by GFH on 01.09.2015.
 */
public class Utils {
    public static URI getUriAudio() {
        return getUriAudio(MainApp.USER_ID, MainApp.TOKEN);
    }

    public static URI getUriAudio(String ownerId, String accessToken) {
        if (MainApp.TOKEN == null) {
            throw new IllegalStateException("App does not logged on!!!");
        }
        try {
            return new URIBuilder()
                    .setScheme("https")
                    .setHost("api.vk.com")
                    .setPath("/method/audio.get")
                    .setParameter("owner_id", ownerId)
                    .setParameter("need_user", "0")
                    .setParameter("access_token", accessToken)
                    .setParameter("v", MainApp.CUR_API)
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
