package com.team4.service;

import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.model.*;
import com.team4.constants.AwsRekognitionConstants;
import com.team4.controller.FaceController;
import com.team4.io.IOUtil;
import com.team4.jpa.entity.Candidate;
import com.team4.util.AwsClientFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FaceService {
	private static final Logger logger = LoggerFactory.getLogger(FaceService.class);

    private CandidateService candidateService;
    private EventLogService eventLogService;

    public FaceService(CandidateService candidateService, EventLogService eventLogService) {
        this.candidateService = candidateService;
        this.eventLogService = eventLogService;
    }

    public List<FaceDetail> detectFaces(byte[] file) {

        ByteBuffer byteBuffer = ByteBuffer.wrap(file);

        AmazonRekognition rekognitionClient = AwsClientFactory.createRekognitionClient();
        DetectFacesRequest request = new DetectFacesRequest()
                .withImage(new Image().withBytes(byteBuffer))
                .withAttributes(Attribute.ALL);

        DetectFacesResult result = rekognitionClient.detectFaces(request);
        logger.info("face detection complete ... faces: {}", result.getFaceDetails().size());
        return result.getFaceDetails();
    }

    public List<FaceDetail> detectFaces(String filePath) {
        byte[] bytes = IOUtil.readFile(filePath);
        return detectFaces(bytes);
    }

    public CompareFacesResult compareFaces(String sourcefile, String targetfile) {
    	byte[] sourceBytes = IOUtil.readFile(sourcefile);
    	byte[] targetBytes = IOUtil.readFile(targetfile);
    	
    	return compareFaces(sourceBytes, targetBytes);
    	
    }
    
    public CompareFacesResult compareFaces(byte[] file1, byte[] file2) {

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

        CompareFacesResult result = rekognitionClient.compareFaces(request);
        
        logger.info("face comparison complete ... faces matched: {}, match ratio: {}, faces un matched: {}", result.getFaceMatches().size(), result.getFaceMatches().size() == 1 ? result.getFaceMatches().get(0).getSimilarity() : "N/A", result.getUnmatchedFaces().size());

        return result;
    }

    public void doFaceOperations(Long candidateId, byte[] file) {

        Candidate candidate = candidateService.findCandidateById(candidateId);
        String photoUrl = candidate.getPhotoUrl();
        byte[] sourceBytes = IOUtil.readFile(photoUrl);

        List<FaceDetail> faceDetails = detectFaces(file);
        eventLogService.logFaceDetectionResult(candidate, faceDetails);

        Path newImagePath = Paths.get(IOUtil.UPLOAD_ROOT_PATH.toString(), String.valueOf(candidate.getJiraId()), candidateId + "-" + System.currentTimeMillis() + ".jpeg");
        IOUtil.writeImageToCandidatePath(file, newImagePath);
        CompareFacesResult compareFacesResult = compareFaces(sourceBytes, file);
        eventLogService.logFaceMatchResult(candidate, compareFacesResult);
    }
}
