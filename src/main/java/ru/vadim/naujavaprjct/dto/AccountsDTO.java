package ru.vadim.naujavaprjct.dto;

import java.time.OffsetDateTime;

public record AccountsDTO(
        String name,
        Long balance,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Long userId
) {
}
