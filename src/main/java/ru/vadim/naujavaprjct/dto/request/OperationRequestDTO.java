package ru.vadim.naujavaprjct.dto.request;

public record OperationRequestDTO(
        Long sum,
        String comment,
        Long account_id,
        Long category_id
) {
}
