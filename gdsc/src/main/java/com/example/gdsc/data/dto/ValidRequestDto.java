package com.example.gdsc.data.dto;

import com.example.gdsc.data.group.ValidationGroup1;
import com.example.gdsc.data.group.ValidationGroup2;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDto {

  @NotBlank
  String name;

  @Email
  String email;

  @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
  String phoneNumber;

  @Min(value = 20)
  @Max(value = 40)
  int age;

  @Size(min = 0, max = 40)
  String description;

  @Positive
  int count;

  @AssertTrue
  boolean booleanCheck;
}
