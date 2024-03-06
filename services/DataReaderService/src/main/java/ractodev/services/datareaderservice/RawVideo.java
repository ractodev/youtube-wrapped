package ractodev.services.datareaderservice;

/**
 * Represents a raw video object.
 */
public record RawVideo(String id) {
    /**
     * Gets the id of the video.
     *
     * @return id of video
     */
    @Override
    public String id() {
        return id;
    }

    /**
     * Returns a string representation of the raw video.
     *
     * @return string representation of raw video
     */
    @Override
    public String toString() {
        return "{id: " + id + "}";
    }

}
