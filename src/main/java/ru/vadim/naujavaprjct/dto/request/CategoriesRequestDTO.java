package ru.vadim.naujavaprjct.dto.request;

import java.time.OffsetDateTime;

public record CategoriesRequestDTO(
        String type,
        String title,
        Long userId
) {
}
