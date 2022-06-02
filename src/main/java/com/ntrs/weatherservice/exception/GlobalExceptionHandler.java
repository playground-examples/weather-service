package com.ntrs.weatherservice.exception;

import com.ntrs.weatherservice.constants.ErrorCode;
import com.ntrs.weatherservice.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(HttpServletRequest req, Exception e) {
        log.error(e.getMessage(), e);

        String errorMsg = (e.getMessage() == null) ? e.getClass().getSimpleName() : e.getMessage();
        Map<String, Object> error = Collections.singletonMap("error", errorMsg);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity handleSystemException(HttpServletRequest req, Exception e) {
        SystemException sysEx = (SystemException) e;

        String errorMsg = (e.getMessage() == null) ? e.getClass().getSimpleName() : e.getMessage();
        log.error("[" + sysEx.getErrorCode() + "] " + sysEx.getMessage());

        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(sysEx.getErrorCode());
        response.setErrorMessage(errorMsg);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(HttpServletRequest req, Exception e) {
        BusinessException businessEx = (BusinessException) e;

        String errorMsg = (e.getMessage() == null) ? e.getClass().getSimpleName() : e.getMessage();
        log.error("[" + businessEx.getErrorCode() + "] " + businessEx.getMessage());

        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(businessEx.getErrorCode());
        response.setErrorMessage(errorMsg);

        return ResponseEntity.status(businessEx.getHttpStatus()).body(response);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseBody
    public ResponseEntity handleMethodArgumentTypeMismatchException(HttpServletRequest req, Exception e) {
        log.error(e.getMessage());

        String errorMsg = (e.getMessage() == null) ? e.getClass().getSimpleName() : e.getMessage();

        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(ErrorCode.CLIENT_ERROR);
        response.setErrorMessage(errorMsg);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public ResponseEntity handleValidationException(HttpServletRequest req, Exception e) {
        log.error(e.getMessage());

        String errorMsg = (e.getMessage() == null) ? e.getClass().getSimpleName() : e.getMessage();

        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(ErrorCode.CLIENT_ERROR);
        response.setErrorMessage(errorMsg);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}