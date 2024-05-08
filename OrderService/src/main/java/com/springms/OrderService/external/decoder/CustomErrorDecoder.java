package com.springms.OrderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springms.OrderService.exception.CustomException;
import com.springms.OrderService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        ObjectMapper om = new ObjectMapper();

        log.info("::{}", response.request().url());
        log.info("::{}", response.request().headers());

        try {
            ErrorResponse er = om.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomException(er.getErrorMessage(), er.getErrorCode(), response.status());
        } catch (IOException e) {
            throw new CustomException("Internal Server Error", "INTERNAL_SERVER_ERROR", 500);
        }

    }
}
