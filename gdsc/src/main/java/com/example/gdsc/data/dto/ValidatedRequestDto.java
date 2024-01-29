package com.example.gdsc.data.dto;

import com.example.gdsc.config.annotation.Telephone;
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
public class ValidatedRequestDto {

  @NotBlank
  private String name;

  @Email
  private String email;

  @Telephone
  private String phoneNumber;

  @Min(value = 20, groups = ValidationGroup1.class)
  @Max(value = 40, groups = ValidationGroup1.class)
  private int age;

  @Size(min = 0, max = 40)
  private String description;

  @Positive(groups = ValidationGroup2.class)
  private int count;

  @AssertTrue
  private boolean booleanCheck;

}
