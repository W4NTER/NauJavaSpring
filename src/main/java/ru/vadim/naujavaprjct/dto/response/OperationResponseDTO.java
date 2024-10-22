package ru.vadim.naujavaprjct.dto.response;

import java.time.OffsetDateTime;

public record OperationResponseDTO(
        Long id,
        Long sum,
        String comment,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        Long account_id,
        Long category_id
) {
}
