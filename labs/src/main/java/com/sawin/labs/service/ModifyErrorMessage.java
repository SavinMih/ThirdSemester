package com.sawin.labs.service;

import com.sawin.labs.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("ModifyErrorMessage")
public class ModifyErrorMessage implements MyModifyService {
    @Override
    public Response modify(Response response) {
        response.setErrorMessage("Что-то сломалось");
        return response;
    }
}
