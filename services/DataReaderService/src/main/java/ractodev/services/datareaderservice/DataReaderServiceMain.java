package ractodev.services.datareaderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DataReaderServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(DataReaderServiceMain.class, args);
    }

    @Bean
    CommandLineRunner runner(DataReaderServiceImpl dataReaderService) {
        return args -> {
            if (args.length > 0) {
                String filePath = args[0];
                dataReaderService.setFilePath(filePath);
                dataReaderService.getRawVideos();
            } else {
                System.out.println("Please provide a file path as a program argument.");
            }
            System.exit(0);
        };
    }

}
