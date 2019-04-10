package com.team4.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.team4.constants.AwsRekognitionConstants;
import com.team4.io.IOUtil;
import com.team4.util.AwsClientFactory;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;
import java.util.List;

@Service
public class FaceService {

    public List<FaceDetail> detectFaces(byte[] file) {

        ByteBuffer byteBuffer = ByteBuffer.wrap(file);

        AmazonRekognition rekognitionClient = AwsClientFactory.createRekognitionClient();
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(byteBuffer))
                .withAttributes(Attribute.ALL);

        DetectFacesResult result = rekognitionClient.detectFaces(request);
        return result.getFaceDetails();
    }

    public List<FaceDetail> detectFaces(String filePath) {
        byte[] bytes = IOUtil.readFile(filePath);
        return detectFaces(bytes);
    }

    public void compareFaces(byte[] file1, byte[] file2) {

        ByteBuffer byteBuffer1 = ByteBuffer.wrap(file1);
        ByteBuffer byteBuffer2 = ByteBuffer.wrap(file2);

        AmazonRekognition rekognitionClient = AwsClientFactory.createRekognitionClient();

        Image source = new Image()
                .withBytes(byteBuffer1);
        Image target = new Image()
                .withBytes(byteBuffer2);

        CompareFacesRequest request = new CompareFacesRequest()
                .withSourceImage(source)
                .withTargetImage(target)
                .withSimilarityThreshold(AwsRekognitionConstants.FACE_SIMILARITY_TRESHOLD);

        CompareFacesResult compareFacesResult=rekognitionClient.compareFaces(request);
    }

}
