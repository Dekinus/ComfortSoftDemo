package com.example.demo.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Запрос на получение N-ого минимального числа из файла")
public class DemoRequest {

    @Schema(description = "Путь до xslx файла")
    private String filePath;

    @Schema(description = "Число N")
    private int number;
}
