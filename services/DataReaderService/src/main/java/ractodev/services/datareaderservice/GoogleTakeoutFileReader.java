package ractodev.services.datareaderservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Contains Google Takeout file parsing functionality.
 */
public class GoogleTakeoutFileReader {
    private final File takeoutFile;

    /**
     * Creates a Google Takeout file parser.
     *
     * @param takeoutFile Google Takeout File
     */
    public GoogleTakeoutFileReader(File takeoutFile) {
        this.takeoutFile = takeoutFile;
    }

    /**
     * Function that proceeds to deserialize json data in takeout file if
     * the input file is valid.
     *
     * @return List of raw video objects if input file is valid, null otherwise
     */
    public List<RawVideo> processTakeoutFile() throws IOException {
        if (takeoutFileExists()) {
            return deserializeTakeoutData();
        } else {
            return null;
        }
    }

    /**
     * Function that verifies that the Takeout file exists by checking that
     * it is accessible and readable.
     *
     * @return true if it exists, false otherwise
     */
    public boolean takeoutFileExists() {
        return takeoutFile.isFile() && takeoutFile.canRead();
    }

    /**
     * Function that deserializes json contents of Takeout file and maps each json object
     * to a raw video object.
     *
     * @return List of raw videos
     */
    public List<RawVideo> deserializeTakeoutData() throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);                             // ignore unknown properties in Takeout data
        return mapper.readValue(takeoutFile, new TypeReference<List<RawVideo>>() {
        });
    }

}
