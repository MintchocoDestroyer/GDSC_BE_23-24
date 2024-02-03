package gdsc.hello.validate.controller;

import gdsc.hello.validate.dto.ValidateRequestDto;
import gdsc.hello.validate.dto.ValidatedRequestDto;
import gdsc.hello.validate.group.ValidationGroup1;
import gdsc.hello.validate.group.ValidationGroup2;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation")
public class ValidationController {

    @PostMapping("/valid")
    public ResponseEntity<String> checkValidationByValid(
            @Valid @RequestBody ValidateRequestDto validateRequestDto){
        log.info(validateRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validateRequestDto.toString());
    }

    @PostMapping("/validated")
    public ResponseEntity<String> checkValidation(
            @Validated @RequestBody ValidatedRequestDto validatedRequestDto){
        log.info(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("validated/group1")
    public ResponseEntity<String> checkValidation1(
            @Validated(ValidationGroup1.class)
            @RequestBody ValidatedRequestDto validatedRequestDto){
        log.info(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("validated/group2")
    public ResponseEntity<String> checkValidation2(
            @Validated(ValidationGroup2.class)
            @RequestBody ValidatedRequestDto validatedRequestDto){
        log.info(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

    @PostMapping("validated/all-group")
    public ResponseEntity<String> checkValidation3(
            @Validated({ValidationGroup1.class, ValidationGroup2.class})
            @RequestBody ValidatedRequestDto validatedRequestDto){
        log.info(validatedRequestDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(validatedRequestDto.toString());
    }

}
