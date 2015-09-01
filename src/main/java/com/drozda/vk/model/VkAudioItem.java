package com.drozda.vk.model;

import com.sun.istack.internal.NotNull;
import javafx.beans.property.*;

/**
 * Created by GFH on 01.09.2015.
 */
public class VkAudioItem {

    private ObjectProperty<VKAudio> audio;
    //    private LongProperty owner_id;//идентификатор владельца аудиозаписи.int (числовое значение)
    private StringProperty artist;//исполнитель.строка
    private StringProperty title;//название композиции.строка
    private LongProperty duration;//длительность аудиозаписи в секундах.положительное число
//    private StringProperty url;//ссылка на mp3.строка
//    private LongProperty lyrics_id;//идентификатор текста аудиозаписи (если доступно).положительное число
//    private LongProperty album_id;//идентификатор альбома, в котором находится аудиозапись (если присвоен).положительное число
//    private LongProperty genre_id;//идентификатор жанра из списка аудио жанров.положительное число
//    private LongProperty date;//дата добавления.положительное число

    {
        audio = new SimpleObjectProperty<VKAudio>();
        artist = new SimpleStringProperty();
        title = new SimpleStringProperty();
        duration = new SimpleLongProperty();
    }

    public VkAudioItem() {
    }

    public VkAudioItem(@NotNull VKAudio vkAudio) {
        this.setAudio(vkAudio);
        this.setArtist(getAudio().getArtist());
        this.setDuration(getAudio().getDuration());
        this.setTitle(getAudio().getTitle());
    }

    public VKAudio getAudio() {
        return audio.get();
    }

    public void setAudio(VKAudio audio) {
        this.audio.set(audio);
    }

    public ObjectProperty<VKAudio> audioProperty() {
        return audio;
    }

    public String getArtist() {
        return artist.get();
    }

    public void setArtist(String artist) {
        this.artist.set(artist);
    }

    public StringProperty artistProperty() {
        return artist;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public long getDuration() {
        return duration.get();
    }

    public void setDuration(long duration) {
        this.duration.set(duration);
    }

    public LongProperty durationProperty() {
        return duration;
    }
}
