package ru.vadim.naujavaprjct.dto.response;

import java.time.OffsetDateTime;

public record CategoryResponseDTO(
        Long id,
        String type,
        String title,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Long userId
) {
}
