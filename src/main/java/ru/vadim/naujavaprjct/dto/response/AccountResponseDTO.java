package ru.vadim.naujavaprjct.dto.response;

import java.time.OffsetDateTime;

public record AccountResponseDTO(
        Long id,
        String name,
        Long balance,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
