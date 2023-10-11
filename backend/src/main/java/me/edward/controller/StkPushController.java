package me.edward.controller;

import me.edward.dto.mpesaCallback.CallbackUrlBody;
import me.edward.dto.PostBodyDTO;
import me.edward.dto.StkPushResponse;
import me.edward.model.Transaction;
import me.edward.service.MpesaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    ResponseEntity<StkPushResponse> stkPush(@RequestBody PostBodyDTO postBodyDTO) {
        logger.info("stkPush:\npostBodyDTO => " + postBodyDTO);
        StkPushResponse response = mpesaService.mpesaExpress(postBodyDTO.phonenumber(), postBodyDTO.callbackUrl());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = "/callback")
    ResponseEntity<Object> mpesaCallback(@RequestBody CallbackUrlBody body) {
        logger.info("mpesaCallback:\nbody => " + body);
        Transaction response = mpesaService.saveCallback(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/transactions")
    ResponseEntity<Page<Transaction>> getTransactions(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(mpesaService.getTransactions(pageable));
    }
}
