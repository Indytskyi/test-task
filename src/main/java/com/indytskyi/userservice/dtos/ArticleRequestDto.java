package com.indytskyi.userservice.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleRequestDto {

    @NotEmpty
    private String text;

    @NotNull
    @Pattern(regexp = " GREEN|RED|BLACK|BLUE", message = "Incorrect color")
    private String color;
}
