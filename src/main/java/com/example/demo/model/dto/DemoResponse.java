package com.example.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ на запрос на получение N-ого минимального числа из файла")
public record DemoResponse(
        @Schema(description = "Строка ответа")
        String message
) {
}
