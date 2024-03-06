package ractodev.services.datareaderservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main class for DataReaderService.
 */
@SpringBootApplication
public class DataReaderServiceMain {

    /**
     * Main method for DataReaderService.
     *
     * @param args Program arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DataReaderServiceMain.class, args);
    }

    /**
     * Command line runner for DataReaderService.
     *
     * @param dataReaderService DataReaderService implementation
     * @return Command line runner
     */
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
