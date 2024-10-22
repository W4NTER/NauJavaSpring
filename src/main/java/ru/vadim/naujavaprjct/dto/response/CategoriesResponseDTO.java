package ru.vadim.naujavaprjct.dto.response;

import java.time.OffsetDateTime;

public record CategoriesResponseDTO(
        Long id,
        String type,
        String title,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Long userId
) {
}
