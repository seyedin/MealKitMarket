package ir.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public record ExceptionResponseDTO(
        HttpStatus status,
        String message,
        LocalDateTime timestamp,
        Map<String, List<String>> fieldErrors
) {
}
