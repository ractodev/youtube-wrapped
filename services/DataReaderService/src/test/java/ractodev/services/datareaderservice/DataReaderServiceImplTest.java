package ractodev.services.datareaderservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataReaderServiceImplTest {
    private DataReaderServiceImpl dataReaderService;

    /**
     * Sets up the test environment by creating a new DataReaderServiceImpl object.
     */
    @BeforeEach
    public void setUp() {
        dataReaderService = new DataReaderServiceImpl();
    }

    /**
     * Tests that getRawVideos returns the expected list of raw videos.
     *
     * @throws IOException if an error occurs when reading the file
     */
    @Test
    @DisplayName("Test getRawVideos valid input")
    public void testGetRawVideosSuccess() throws IOException {
        URL resource = getClass().getClassLoader().getResource("valid_history.json");
        assert resource != null;
        dataReaderService.setFilePath(resource.getPath());

        List<RawVideo> expectedRawVideos = List.of(new RawVideo("1"), new RawVideo("2"), new RawVideo("3"));
        List<RawVideo> actualRawVideos = dataReaderService.getRawVideos();
        Assertions.assertEquals(expectedRawVideos, actualRawVideos);
    }

    /**
     * Tests that getRawVideos returns an empty list when the input file is empty.
     *
     * @throws IOException if an error occurs when reading the file
     */
    @Test
    @DisplayName("Test getRawVideos empty input")
    public void testGetRawVideosEmpty() throws IOException {
        URL resource = getClass().getClassLoader().getResource("invalid_history.json");
        assert resource != null;
        dataReaderService.setFilePath(resource.getPath());

        List<RawVideo> expectedRawVideos = new ArrayList<>();
        List<RawVideo> actualRawVideos = dataReaderService.getRawVideos();
        Assertions.assertEquals(expectedRawVideos, actualRawVideos);
    }

    /**
     * Tests that getRawVideos returns null when the input file is invalid.
     *
     * @throws IOException if an error occurs when reading the file
     */
    @Test
    @DisplayName("Test getRawVideos invalid input")
    public void testGetRawVideosInvalid() throws IOException {
        dataReaderService.setFilePath("does_not_exist.json");

        List<RawVideo> actualRawVideos = dataReaderService.getRawVideos();
        Assertions.assertNull(actualRawVideos);
    }

}