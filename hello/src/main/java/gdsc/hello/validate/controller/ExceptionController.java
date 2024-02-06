package gdsc.hello.validate.controller;

import lombok.extern.slf4j.Slf4j;
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
}
