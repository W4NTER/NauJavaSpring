package ru.vadim.naujavaprjct.dto;

import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;

import java.util.List;

public record ReportDTO(
        Integer countUsers,
        Long timeToCountUsers,
        Long timeToListAccounts,
        Long timeToGenerateReport,
        List<AccountResponseDTO> accounts
        ) {
}
