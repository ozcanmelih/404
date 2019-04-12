package com.team4.controller;

import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.team4.service.FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;

import java.util.Base64;
import java.util.List;

@RestController
public class FaceController {

	private static final Logger logger = LoggerFactory.getLogger(FaceController.class);
    private FaceService faceService;

    public FaceController(FaceService faceService) {
        this.faceService = faceService;
        logger.info("face controller initialized");
    }

    @PostMapping("/face/{userId}")
    public ResponseEntity<String> faceComparison(@PathVariable("userId") Long usrId, @RequestParam("imageBase64") String imageBase64) throws Exception {
    	byte[] decodedBytes = Base64.getDecoder().decode(imageBase64.replaceAll("data:image/.+;base64,", ""));
    	logger.info("init face rekognition for user: {}", usrId);
        faceService.doFaceOperations(usrId, decodedBytes);
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}
