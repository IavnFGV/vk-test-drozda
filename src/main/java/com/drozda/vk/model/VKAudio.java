package com.drozda.vk.model;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by GFH on 31.08.2015.
 */
public class VKAudio implements Serializable {

    private long id;//идентификатор аудиозаписи. положительное число
    private long owner_id;//идентификатор владельца аудиозаписи.int (числовое значение)
    private String artist;//исполнитель.строка
    private String title;//название композиции.строка
    private long duration;//длительность аудиозаписи в секундах.положительное число
    private String url;//ссылка на mp3.строка
    private long lyrics_id;//идентификатор текста аудиозаписи (если доступно).положительное число
    private long album_id;//идентификатор альбома, в котором находится аудиозапись (если присвоен).положительное число
    private long genre_id;//идентификатор жанра из списка аудио жанров.положительное число
    private long date;//дата добавления.положительное число

    //its not the best decision, but fast to develop (GSON in future??)
    public static VKAudio createFromJSON(JSONObject jsonObject) {
        VKAudio result = new VKAudio();
        List<Field> fields = asList(VKAudio.class.getDeclaredFields());
        for (Field field : fields) {
            if (!jsonObject.isNull(field.getName())) {
                //      field.setAccessible(true);
                try {
                    field.set(result, jsonObject.get(field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(long owner_id) {
        this.owner_id = owner_id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLyrics_id() {
        return lyrics_id;
    }

    public void setLyrics_id(long lyrics_id) {
        this.lyrics_id = lyrics_id;
    }

    public long getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(long album_id) {
        this.album_id = album_id;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VKAudio{" +
                "id=" + id +
                ", owner_id=" + owner_id +
                ", artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", url='" + url + '\'' +
                ", lyrics_id=" + lyrics_id +
                ", album_id=" + album_id +
                ", genre_id=" + genre_id +
                ", date=" + date +
                '}';
    }
}
