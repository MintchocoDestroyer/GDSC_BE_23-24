package gdsc.hello.validate.controller;

import gdsc.hello.common.Constants;
import gdsc.hello.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
@Slf4j
public class ExceptionController {

    @GetMapping
    public void getRuntimeException(){
        throw new RuntimeException("getRuntimeException 메소드 호출");
    }

    @GetMapping("/custom")
    public void getCustomException() throws CustomException{
        throw new CustomException(Constants.ExceptionClass.PRODUCT, HttpStatus.BAD_REQUEST, "getCustomException 메소드 호출");
    }
}
