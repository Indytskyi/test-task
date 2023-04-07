package com.indytskyi.userservice.dtos.request;

import com.indytskyi.userservice.annotation.ValidEnum;
import com.indytskyi.userservice.models.enums.Color;
import jakarta.validation.constraints.NotEmpty;
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
    @ValidEnum(enumClass = Color.class)
    private String color;
}
