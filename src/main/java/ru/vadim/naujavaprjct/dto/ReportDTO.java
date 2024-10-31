package ru.vadim.naujavaprjct.dto;

import java.time.OffsetDateTime;

public record ReportDTO(
        Long id,
        String body,
        OffsetDateTime createdAt,
        String status
) {
}
