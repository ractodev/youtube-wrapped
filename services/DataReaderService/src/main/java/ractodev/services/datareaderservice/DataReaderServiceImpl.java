package ractodev.services.datareaderservice;

import net.devh.boot.grpc.server.service.GrpcService;
import com.ractodev.services.datareaderservice.DataReaderServiceGrpc;
import com.ractodev.services.datareaderservice.RawVideoOuterClass;
import io.grpc.stub.StreamObserver;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Service implementation for DataReaderService.
 */
@GrpcService
public class DataReaderServiceImpl extends DataReaderServiceGrpc.DataReaderServiceImplBase {
    private String filePath;

    /**
     * Creates a DataReaderService implementation.
     *
     * @param filePath Path to the Google Takeout file
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Function that reads the Google Takeout file and returns a list of raw video objects.
     *
     * @return List of raw video objects
     */
    public List<RawVideo> getRawVideos() {
        System.out.println("Reading file: " + filePath);
        GoogleTakeoutFileReader reader = new GoogleTakeoutFileReader(new File(filePath));
        System.out.println("Processing file...");
        System.out.println("Result: " + reader.processTakeoutFile());
        return reader.processTakeoutFile();
    }

    @Override
    public void getRawVideos(RawVideoOuterClass.Empty request, StreamObserver<RawVideoOuterClass.RawVideo> responseObserver) {
        List<RawVideo> rawVideos = getRawVideos();
        for (RawVideo rawVideo : rawVideos) {
            responseObserver.onNext(RawVideoOuterClass.RawVideo.newBuilder()
                    .setId(rawVideo.id())
                    .build());
        }
        responseObserver.onCompleted();
    }

}
