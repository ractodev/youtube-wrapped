package ractodev.services.datareaderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.List;

@SpringBootApplication
public class DataReaderServiceMain {
    private final String takeoutFilePath = "history.json";

    public static void main(String[] args) {
        SpringApplication.run(DataReaderServiceMain.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            GoogleTakeoutFileReader reader = new GoogleTakeoutFileReader(new File(takeoutFilePath));
            List<RawVideo> rawVideos = reader.processTakeoutFile();
            for (RawVideo rawVideo : rawVideos) {
                System.out.println(rawVideo.toString());
            }
            System.exit(0);
        };
    }

}
