package ractodev.services.datareaderservice;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataReaderServiceMain {
    public static void main(String[] args) throws IOException {
        GoogleTakeoutFileReader reader = new GoogleTakeoutFileReader(new File(args[0]));
        List<RawVideo> rawVideos = reader.processTakeoutFile();
        for (RawVideo video : rawVideos) {
            System.out.println(video.toString());
        }

    }
}