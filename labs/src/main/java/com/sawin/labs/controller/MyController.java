package com.sawin.labs.controller;

import com.sawin.labs.model.Request;
import com.sawin.labs.model.Response;
import com.sawin.labs.service.ModifyRequestService;
import com.sawin.labs.service.MyModifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {
    private final MyModifyService myModifyService;
    private final ModifyRequestService modifyRequestService;

    @Autowired
    public MyController(@Qualifier("ModifySystemTime") MyModifyService myModifyService, ModifyRequestService modifyRequestService) {
        this.myModifyService = myModifyService;
        this.modifyRequestService = modifyRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@RequestBody Request request) {

        log.warn("Входящий запрос: "+String.valueOf(request));

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(request.getSystemTime())
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();

        Response responseModified = myModifyService.modify(response);
        log.warn("Ответ: "+String.valueOf(responseModified));

        return new ResponseEntity<>(responseModified, HttpStatus.OK);
    }
}
