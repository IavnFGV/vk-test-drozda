package com.drozda.vk.controller;

import com.drozda.vk.Utils;
import com.drozda.vk.model.VKAudio;
import com.drozda.vk.model.VkAudioItem;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by GFH on 31.08.2015.
 */
public class AudioController {

    private int audioCount;
    private List<VKAudio> VKAudios = new ArrayList<VKAudio>();
    private Map<String, Media> mediaMap = new HashMap<>();
    private ObservableList<VkAudioItem> audioItems = FXCollections.observableArrayList();
    private Media currentMedia;
    @FXML
    private TableView<VkAudioItem> audioTable;
    @FXML
    private TableColumn<VkAudioItem, String> artistColumn;
    @FXML
    private TableColumn<VkAudioItem, String> compositionColumn;
    @FXML
    private Button playButton;
    @FXML
    private Label currentLabel;
    @FXML
    private Slider slider;
    private MediaView mediaView = new MediaView();
    private MediaPlayer mediaPlayer;

    public Media getCurrentMedia() {
        return currentMedia;
    }

    private void setCurrentMedia(VkAudioItem audioItem) {
        setCurrentMedia(getMedia(audioItem.getAudio().getUrl()));
        currentLabel.setText("NEXT COMPOSITION\n" + audioItem.getArtist() + " - " + audioItem.getTitle());

    }

    @FXML
    private void initialize() {
        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        compositionColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        //showPersonDetails(null);
        audioTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> setCurrentMedia(newValue)
        );
    }

    public void setCurrentMedia(Media currentMedia) {
        this.currentMedia = currentMedia;
    }

    private Media getMedia(String uri) {
        if (!mediaMap.containsKey(uri)) {
            mediaMap.put(uri, new Media(uri));
        }
        return mediaMap.get(uri);

    }

    @FXML
    private void handlePlayButtonPress() {
        playPauseComposition();

        slider.valueProperty().

                addListener(ov -> {
                            if (slider.isValueChanging()) {
                                if (mediaPlayer.getTotalDuration() != null) {
                                    mediaPlayer.seek(mediaPlayer.getTotalDuration().multiply(slider.getValue() / 100.0));
                                }
                                updateValues();

                            }
                        }

                );

        mediaPlayer.currentTimeProperty().

                addListener((observable, oldValue, newValue) -> {
                            updateValues();
                        }

                );

    }

    private void playPauseComposition() {

        if (mediaPlayer != null) {

            MediaPlayer.Status status = mediaPlayer.getStatus();
            if (status == MediaPlayer.Status.PAUSED
                    || status == MediaPlayer.Status.READY
                    || status == MediaPlayer.Status.STOPPED) {
                mediaPlayer.play();
                playButton.setText("PLAY");
//                playButton.setGraphic(imageViewPlay);
            } else {
                mediaPlayer.pause();
                playButton.setText("PAUSE");
                // playButton.setGraphic(imageViewPause);
            }
        }
        if (currentMedia != null) {
            mediaPlayer = new MediaPlayer(currentMedia);
            mediaPlayer.setAutoPlay(true);
            mediaView = new MediaView(mediaPlayer);
            mediaPlayer.play();
            playButton.setText("PLAY");
        }

    }

    private void updateValues() {

        Platform.runLater(() -> {
            Duration currentTime = mediaPlayer.getCurrentTime();
            // time.setText(formatTime(currentTime, duration));
            //   slider.setDisable(duration.isUnknown());
            //    if (!slider.isDisabled() && duration.greaterThan(Duration.ZERO) && !slider.isValueChanging()) {
            slider.setValue(currentTime.divide(mediaPlayer.getTotalDuration().toMillis()).toMillis() * 100.0);
            //  }

        });

    }

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
                StringBuilder sb = new StringBuilder(EntityUtils.toString(entity));
                VKAudios = com.drozda.vk.json.Utils.getAudioListFromJson(sb.toString());
                audioItems.addAll(VKAudios.stream().map(vkAudio -> new VkAudioItem(vkAudio)).collect(Collectors
                        .toList()));
                audioTable.setItems(getAudioItems());
                return null;
            }
        };
        try {
            httpclient.execute(httpget, rh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<VkAudioItem> getAudioItems() {
        return audioItems;
    }

    public void setAudioItems(ObservableList<VkAudioItem> audioItems) {
        this.audioItems = audioItems;
    }

    public int getAudioCount() {
        return audioCount;
    }

    public void setAudioCount(int audioCount) {
        this.audioCount = audioCount;
    }
}