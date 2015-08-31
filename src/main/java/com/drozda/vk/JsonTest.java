package com.drozda.vk;

import org.json.JSONObject;

/**
 * Created by GFH on 31.08.2015.
 */
public class JsonTest {
    public static String jsonString = "{response: {\n" +
            "count: 505,\n" +
            "items: [{\n" +
            "id: '34',\n" +
            "photo: 'http://cs7009.vk....3f2/rj4RvYLCobY.jpg',\n" +
            "name: 'Tatyana Plutalova',\n" +
            "name_gen: 'Tatyana'\n" +
            "}, {\n" +
            "id: 232745053,\n" +
            "owner_id: 34,\n" +
            "artist: 'Ambassadeurs',\n" +
            "title: 'Sparks',\n" +
            "duration: 274,\n" +
            "url: 'http://cs6164.vk....M_lGEJhqRK8d5OQZngI',\n" +
            "lyrics_id: 120266970,\n" +
            "genre_id: 18\n" +
            "}, {\n" +
            "id: 232733966,\n" +
            "owner_id: 34,\n" +
            "artist: 'Aloe Blacc',\n" +
            "title: 'Can You Do This ',\n" +
            "duration: 176,\n" +
            "url: 'http://cs6157.vk....ZaerOa0DvsyOCYTPO1w',\n" +
            "genre_id: 2\n" +
            "}, {\n" +
            "id: 232735496,\n" +
            "owner_id: 34,\n" +
            "artist: 'Aloe Blacc',\n" +
            "title: 'Wake Me Up',\n" +
            "duration: 224,\n" +
            "url: 'http://cs6109.vk....mYFzHJU55ixz8Av8ujc',\n" +
            "lyrics_id: 119056069,\n" +
            "genre_id: 2\n" +
            "}]}}";

    public static void main(String[] args) {
        JSONObject obj = new JSONObject(jsonString);

        System.out.println(obj.getJSONObject("response")); //John
    }
}
