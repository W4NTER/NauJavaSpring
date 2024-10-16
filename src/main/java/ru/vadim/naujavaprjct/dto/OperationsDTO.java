package ru.vadim.naujavaprjct.dto;

import java.time.OffsetDateTime;

public record OperationsDTO(
        Long sum,
        String comment,

        Long account_id,
        Long category_id
) {
}
