package com.example.demo.controller;

import com.example.demo.handler.ErrorMessage;
import com.example.demo.model.dto.DemoRequest;
import com.example.demo.model.dto.DemoResponse;
import com.example.demo.service.DemoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Демо контроллер", description = "Контроллер для работы с xlsx файлами")
@RestController
@RequiredArgsConstructor
@RequestMapping("xlsx/")
public class DemoController {

    private final DemoService demoService;

    @Operation(summary = "Нахождение N-ного минимального числа",
            description = "Позволяет N-ное минимальное число из xlsx файла",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = DemoResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping("demo")
    public ResponseEntity<DemoResponse> getNthSmallestElement(@RequestBody DemoRequest demoRequest) {
        return ResponseEntity.ok(demoService.getNthSmallestElement(demoRequest));
    }
}
