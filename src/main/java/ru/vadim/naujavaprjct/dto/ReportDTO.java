package ru.vadim.naujavaprjct.dto;

import java.time.OffsetDateTime;

public record ReportDTO(
        Long id,
        ReportBodyDTO body,
        OffsetDateTime createdAt,
        String status
) {
}
