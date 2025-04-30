package com.example.demo.handler;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ответ при ошибке")
public record ErrorMessage(
        @Schema(description = "Сообщение ошибки")
        String errorMessage
) {
}
