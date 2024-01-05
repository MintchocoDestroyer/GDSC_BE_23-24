package com.example.gdsc.Controller;

import com.example.gdsc.Dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);
    @GetMapping(value="/name")
    public String getName(){
        LOGGER.info("getName 메소드가 호출되었습니다.");
        return "Flature";

    }

    //http://localhost:8080/api/v1/get-api/hello
    @RequestMapping(value="/hello",method= RequestMethod.GET)
    public String getHello(){
        return "HelloWorld";
    }





    @GetMapping(value="/variable1/{variable}")
    public String getVariable1(@PathVariable String variable){
        return variable;
    }
    @GetMapping(value="/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    @GetMapping(value="/request")
    public String getRequest(
            @RequestParam Map<String, String> param
            ){
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map->{
            sb.append(map.getKey() + ":" + map.getValue() + "\n");
        });
        return sb.toString();
    }
    @GetMapping(value = "/dto")
    public String dto(MemberDto memberDto){
        return memberDto.toString();
    }


    @ApiOperation(value = "GET 메소드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    @ApiOperation(value = "GET 메소드 예제", notes = "DTO를 활용한 GET Method")
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDTO) {
        return memberDTO.toString();
    }
}
