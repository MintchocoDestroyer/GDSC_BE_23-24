package com.example.gdsc.config.actuator;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component
public class CustomInfoContributor implements InfoContributor {

  @Override
  public void contribute(Info.Builder builder){
    Map<String, Object> content = new HashMap<>();
    content.put("code-info", "InfoContributor 구현체에서 정의한 정보입니다.");
    builder.withDetail("custom-info-contributor", content);
  }
}
