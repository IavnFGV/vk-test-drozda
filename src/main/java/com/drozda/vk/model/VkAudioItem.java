package com.drozda.vk.model;

import com.sun.istack.internal.NotNull;
import javafx.beans.property.*;

/**
 * Created by GFH on 01.09.2015.
 */
public class VkAudioItem {

    private ObjectProperty<VKAudio> audio;
    //    private LongProperty owner_id;//������������� ��������� �����������.int (�������� ��������)
    private StringProperty artist;//�����������.������
    private StringProperty title;//�������� ����������.������
    private LongProperty duration;//������������ ����������� � ��������.������������� �����
//    private StringProperty url;//������ �� mp3.������
//    private LongProperty lyrics_id;//������������� ������ ����������� (���� ��������).������������� �����
//    private LongProperty album_id;//������������� �������, � ������� ��������� ����������� (���� ��������).������������� �����
//    private LongProperty genre_id;//������������� ����� �� ������ ����� ������.������������� �����
//    private LongProperty date;//���� ����������.������������� �����

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
