package ru.vadim.naujavaprjct.service.reportService;

import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;

import java.util.List;

public record AccountsReportPart(
        Long timeToCountReport,
        List<AccountResponseDTO> accounts
) {
}
