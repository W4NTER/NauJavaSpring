package ru.vadim.naujavaprjct.dto;

import java.time.OffsetDateTime;

public record CategoriesDTO(
        String type,
        String title,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Long userId
) {
}
