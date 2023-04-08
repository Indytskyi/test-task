package com.indytskyi.userservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
  @Email
  @NotEmpty
  private String email;
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
          message = "Incorrect format of password")
  @NotEmpty
  private String password;
  @Size(min = 3, message = "Input correct firstName")
  @NotEmpty
  private String name;
  @Min(value = 5, message = "The minimum age for creating an account must be over 5")
  @NotNull
  private Integer age;
}