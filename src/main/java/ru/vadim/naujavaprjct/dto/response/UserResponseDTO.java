package ru.vadim.naujavaprjct.dto.response;

import java.time.OffsetDateTime;

public record UserResponseDTO(
        Long id,
        String username,
        String password,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
