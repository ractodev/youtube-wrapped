package ractodev.services.datareaderservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataReaderServiceImplTest {

    /**
     * Tests that getRawVideos returns the expected list of raw videos.
     */
    @Test
    @DisplayName("Test getRawVideos valid input")
    public void testGetRawVideosSuccess() {
        String path = "src/test/resources/valid_history.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        DataReaderServiceImpl dataReaderService = new DataReaderServiceImpl();
        dataReaderService.setFilePath(absolutePath);

        List<RawVideo> expectedRawVideos = List.of(new RawVideo("1"), new RawVideo("2"), new RawVideo("3"));
        List<RawVideo> actualRawVideos = dataReaderService.getRawVideos();
        Assertions.assertEquals(expectedRawVideos, actualRawVideos);
    }

    /**
     * Tests that getRawVideos returns an empty list when the input file is empty.
     */
    @Test
    @DisplayName("Test getRawVideos empty input")
    public void testGetRawVideosEmpty() {
        String path = "src/test/resources/invalid_history.json";
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        DataReaderServiceImpl dataReaderService = new DataReaderServiceImpl();
        dataReaderService.setFilePath(absolutePath);

        List<RawVideo> expectedRawVideos = new ArrayList<>();
        List<RawVideo> actualRawVideos = dataReaderService.getRawVideos();
        Assertions.assertEquals(expectedRawVideos, actualRawVideos);
    }

    /**
     * Tests that getRawVideos returns null when the input file is invalid.
     */
    @Test
    @DisplayName("Test getRawVideos invalid input")
    public void testGetRawVideosInvalid() {
        DataReaderServiceImpl dataReaderService = new DataReaderServiceImpl();
        dataReaderService.setFilePath("does_not_exist.json");

        List<RawVideo> actualRawVideos = dataReaderService.getRawVideos();
        Assertions.assertNull(actualRawVideos);
    }

}
