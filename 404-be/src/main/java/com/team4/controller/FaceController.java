package com.team4.controller;

import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.team4.service.FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class FaceController {

    private static final Logger logger = LoggerFactory.getLogger(FaceController.class);

    private FaceService faceService;

    public FaceController(FaceService faceService) {
        this.faceService = faceService;
    }

    @PostMapping("/face-detection")
    public ResponseEntity<List<FaceDetail>> faceDetection(@RequestParam("file") MultipartFile file) throws Exception {

        List<FaceDetail> result = faceService.detectFaces(file.getBytes());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/face-detection-file")
    public ResponseEntity<List<FaceDetail>> faceDetection(@RequestParam("filePath") String filePath) throws Exception {

        List<FaceDetail> result = faceService.detectFaces(filePath);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/face-comparison")
    public ResponseEntity<CompareFacesResult> faceComparison(@RequestParam("usrId") Long usrId, @RequestParam("file") MultipartFile file) throws Exception {

        CompareFacesResult compareFacesResult = faceService.compareFaces(usrId, file.getBytes());

        return new ResponseEntity<>(compareFacesResult, HttpStatus.OK);
    }

}
