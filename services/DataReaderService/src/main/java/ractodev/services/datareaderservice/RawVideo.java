package ractodev.services.datareaderservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents a raw video object containing only the information
 * available directly in the Google Takeout data.
 */
public class RawVideo {
    /*
        TODO: "title" found in Takeout file contains additional text such as "Tittade på ...".
              Maybe avoid this by enriching a Youtube supplied title?
    */
    private final String title;
    private final String channel;
    private final String url;

    /**
     * Creates a raw video object with essential details for enrichment.
     * If any of the fields are missing during JSON parsing, they are
     * defaulted to "Unknown".
     *
     * @param title   Title of the video
     * @param creator Creator (channel) of the video (name + URL)
     * @param url     URL of video
     */
    public RawVideo(@JsonProperty("title") String title, @JsonProperty("subtitles") List<Creator> creator, @JsonProperty("titleUrl") String url) {
        this.title = title != null ? title : "Unknown";
        this.channel = (creator != null && !creator.isEmpty()) ? creator.getFirst().name : "Unknown";
        this.url = url != null ? url : "Unknown";
    }

    /**
     * Video creator (channel) name.
     *
     * @param name Name of creator
     * @param url  URL of creator
     */
    public record Creator(String name, String url) {
    }

    /**
     * Gets the video's title.
     *
     * @return title of the video
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the channel that uploaded the video.
     *
     * @return channel that uploaded the video
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Gets the url of the video.
     *
     * @return url of video
     */
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "{title: " + title + ", Channel: " + channel + ", URL: " + url + "}";
    }
}
