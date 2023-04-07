package com.indytskyi.userservice.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class ValidEnumValidator implements ConstraintValidator<ValidEnum, String> {

  private Class<? extends Enum<?>> enumClass;

  @Override
  public void initialize(ValidEnum constraintAnnotation) {
    enumClass = constraintAnnotation.enumClass();
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) {
      return true;
    }
    return Arrays.stream(enumClass.getEnumConstants()).anyMatch(e -> e.name().equals(value));
  }
}