package ru.vadim.naujavaprjct.service.reportService;

public record UsersCountReportPart(
        Long timeToCountReport,
        Integer countUsers
) {
}
