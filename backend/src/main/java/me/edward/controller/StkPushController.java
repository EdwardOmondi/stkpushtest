package me.edward.controller;

import me.edward.dto.mpesaCallback.CallbackUrlBody;
import me.edward.dto.PostBody;
import me.edward.dto.StkPushResponse;
import me.edward.model.Transaction;
import me.edward.service.MpesaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class StkPushController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    final MpesaService mpesaService;

    public StkPushController(MpesaService mpesaService) {
        this.mpesaService = mpesaService;
    }
    @PostMapping(path = "/push")
    ResponseEntity<StkPushResponse> stkPush(@RequestBody PostBody postBody){
        logger.info("stkPush:\npostBody => " + postBody);
        StkPushResponse response = mpesaService.mpesaExpress(postBody.phonenumber(), postBody.callbackUrl());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/callback")
    ResponseEntity<Object>  mpesaCallback(@RequestBody CallbackUrlBody body){
        logger.info("mpesaCallback:\nbody => " + body);
        Transaction response = mpesaService.saveCallback(body);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }




}
