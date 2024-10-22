package ru.vadim.naujavaprjct.dto.request;

public record AccountsRequestDTO(
        String name,
        Long balance,
        Long userId
) {
}
