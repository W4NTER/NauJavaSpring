package ru.vadim.naujavaprjct.dto;

import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;

import java.util.List;

public record ReportBodyDTO(
        Integer countUsers,
        Long timeToCountUsers,
        Long timeToListAccounts,
        Long timeToGenerateReport,
        List<AccountResponseDTO> accounts
        ) {
}
