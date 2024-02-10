package ractodev.services.datareaderservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GoogleTakeoutFileReaderTest {
    private static GoogleTakeoutFileReader readerValidData;
    private static GoogleTakeoutFileReader readerInvalidData;
    private static GoogleTakeoutFileReader readerNoFile;
    private static File validTakeoutFile;
    private static File invalidTakeoutFile;
    private static File notATakeoutFile;

    /**
     * Sets up three example Google Takeout files: one with valid structure,
     * one with invalid structure, and lastly a file pointer with no contents.
     */
    @BeforeAll
    static void setUpTakeoutFile() {
        String validTakeoutData = "[{\n" +
                "  \"header\": \"YouTube\",\n" +
                "  \"title\": \"Example title\",\n" +
                "  \"titleUrl\": \"https://www.youtube.com/example\",\n" +
                "  \"subtitles\": [{\n" +
                "    \"name\": \"Example channel name\",\n" +
                "    \"url\": \"https://www.youtube.com/channel/example\"\n" +
                "  }],\n" +
                "  \"time\": \"2024-02-08T14:40:01.763Z\",\n" +
                "  \"products\": [\"YouTube\"],\n" +
                "  \"activityControls\": [\"Watch history on YouTube\"]\n" +
                "}]";
        String invalidTakeoutData = "[{}]";
        try {
            try (FileWriter fileWriter = new FileWriter("historyTestValid.json")) {
                fileWriter.write(validTakeoutData);
            }
            try (FileWriter fileWriter = new FileWriter("historyTestInvalid.json")) {
                fileWriter.write(invalidTakeoutData);
            }
            validTakeoutFile = new File("historyTestValid.json");
            invalidTakeoutFile = new File("historyTestInvalid.json");
            notATakeoutFile = new File("noHistory.json");
            readerValidData = new GoogleTakeoutFileReader(validTakeoutFile);
            readerInvalidData = new GoogleTakeoutFileReader(invalidTakeoutFile);
            readerNoFile = new GoogleTakeoutFileReader(notATakeoutFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Cleans up files after running tests.
     */
    @AfterAll()
    static void cleanUpTakeoutFile() {
        validTakeoutFile.delete();
        invalidTakeoutFile.delete();
        notATakeoutFile.delete();
    }

    /**
     * Tests that takeoutFileExists() returns true if valid Takeout file is passed.
     */
    @Test
    void takeoutFileExists_validFile() {
        Assertions.assertThat(readerValidData.takeoutFileExists()).isTrue();
    }

    /**
     * Tests that takeoutFileExists() returns false if invalid Takeout file is passed.
     */
    @Test
    void takeoutFileExists_invalidFile() {
        Assertions.assertThat(readerNoFile.takeoutFileExists()).isFalse();
    }

    /**
     * Tests that deserializeTakeoutData() correctly creates a raw video object given
     * valid Takeout data.
     */
    @Test
    void deserializeTakeoutData_parseSuccess() throws IOException {
        RawVideo.Creator expectedCreator = new RawVideo.Creator("Example channel name", "https://www.youtube.com/channel/example");
        RawVideo expectedRawVideo = new RawVideo("Example title", List.of(expectedCreator), "https://www.youtube.com/example");
        List<RawVideo> expectedRawVideos = List.of(expectedRawVideo);

        Assertions.assertThat(readerValidData.deserializeTakeoutData()).usingRecursiveComparison().isEqualTo(expectedRawVideos);
    }

    /**
     * Tests that deserializeTakeoutData() correctly creates a raw video with all
     * properties set to "Unknown" given invalid Takeout data.
     */
    @Test
    void deserializeTakeoutData_missingFields() throws IOException {
        RawVideo.Creator expectedCreator = new RawVideo.Creator("Unknown", "Unknown");
        RawVideo expectedRawVideo = new RawVideo("Unknown", List.of(expectedCreator), "Unknown");
        List<RawVideo> expectedRawVideos = List.of(expectedRawVideo);

        Assertions.assertThat(readerInvalidData.deserializeTakeoutData()).usingRecursiveComparison().isEqualTo(expectedRawVideos);
    }


}
