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
import java.util.List;

@RestController
public class FaceController {

    private FaceService faceService;

    public FaceController(FaceService faceService) {
        this.faceService = faceService;
    }

    @PostMapping("/face/{userId}")
    public ResponseEntity<String> faceComparison(@PathVariable("userId") Long usrId, @RequestParam("file") MultipartFile file) throws Exception {
        faceService.doFaceOperations(usrId, file.getBytes());
        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}
