package com.example.springbootjwtAuth.exceptions;

import com.example.springbootjwtAuth.shared.utils.codes.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceException extends Exception{

    protected String message;
    protected ErrorCode errorCode;

}
