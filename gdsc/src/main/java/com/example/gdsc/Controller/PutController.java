package com.example.gdsc.Controller;

import com.example.gdsc.Dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
    @PutMapping(value="/member")
    public String postMember(@RequestBody Map<String, Object> putData){
        StringBuilder sb = new StringBuilder();
        putData.entrySet().forEach(map->{
            sb.append(map.getKey()+" : "+map.getValue() + "\n");
        });
        return sb.toString();
    }
    @PutMapping(value="/member2")
    public String putMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }
    @PutMapping(value="/member3")
    public ResponseEntity<MemberDto> putMemberDto3(@RequestBody MemberDto memberDto){
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(memberDto);
    }
}
