package gdsc.hello.validate.controller;

import gdsc.hello.validate.dto.ValidateRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation")
public class ValidationController {

    @PostMapping("/valid")
    public ResponseEntity<String> checkValidationByValid(@Valid @RequestBody ValidateRequestDto validateRequestDto){
        log.info(validateRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validateRequestDto.toString());
    }
}
