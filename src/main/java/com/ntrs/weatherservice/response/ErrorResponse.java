package com.ntrs.weatherservice.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
}