package ru.vadim.naujavaprjct.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.vadim.naujavaprjct.entity.User;

import java.time.OffsetDateTime;

public record AccountResponseDTO(
        Long id,
        String name,
        Long balance,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
