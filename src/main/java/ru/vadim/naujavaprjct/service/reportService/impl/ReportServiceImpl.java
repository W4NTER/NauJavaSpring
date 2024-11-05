package ru.vadim.naujavaprjct.service.reportService.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.vadim.naujavaprjct.dto.ReportBodyDTO;
import ru.vadim.naujavaprjct.dto.ReportDTO;
import ru.vadim.naujavaprjct.dto.response.AccountResponseDTO;
import ru.vadim.naujavaprjct.entity.Report;
import ru.vadim.naujavaprjct.exception.EntityNotFoundException;
import ru.vadim.naujavaprjct.exception.ReportException;
import ru.vadim.naujavaprjct.report.ReportStatus;
import ru.vadim.naujavaprjct.repository.ReportRepository;
import ru.vadim.naujavaprjct.service.AccountService;
import ru.vadim.naujavaprjct.service.reportService.AccountsReportPart;
import ru.vadim.naujavaprjct.service.reportService.ReportService;
import ru.vadim.naujavaprjct.service.UserService;
import ru.vadim.naujavaprjct.service.reportService.UsersCountReportPart;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ReportServiceImpl implements ReportService {
    private final UserService userService;
    private final ReportRepository reportRepository;
    private final AccountService accountService;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String EMPTY_STRING = "";

    public ReportServiceImpl(UserService userService,
                             ReportRepository reportRepository,
                             AccountService accountService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.reportRepository = reportRepository;
        this.accountService = accountService;
        this.objectMapper = objectMapper;
    }

    @Override
    public Long creteReport() {
        Report report = reportRepository.save(
                new Report(
                        ReportStatus.CREATED.toString(),
                        EMPTY_STRING,
                        OffsetDateTime.now()));

        new Thread(() -> generateReport(report)).start();

        return report.getId();
    }

    private void generateReport(Report report) {
       long startTime = System.currentTimeMillis();

        try {
            var futureUsers = futureUsers();
            var futureAccounts = futureAccounts();

            report.setStatus(ReportStatus.COMPLETED.toString());

            var accounts = futureAccounts.get();
            var users = futureUsers.get();
            report.setBody(
                   objectMapper.writeValueAsString(
                           new ReportBodyDTO(
                                   users.countUsers(),
                                   users.timeToCountReport(),
                                   accounts.timeToCountReport(),
                                   System.currentTimeMillis() - startTime,
                                   accounts.accounts()

                           )
                   )
            );
            reportRepository.save(report);
       } catch (ExecutionException | JsonProcessingException | InterruptedException e) {
            LOGGER.info(e.getMessage());
            report.setStatus(ReportStatus.ERROR.toString());
            reportRepository.save(report);
           throw new ReportException();
       }
    }

    private CompletableFuture<UsersCountReportPart> futureUsers() {
        return CompletableFuture.supplyAsync(() -> {
            long startTimeUsers = System.currentTimeMillis();
            Integer countUsers = userService.listAll().size();
            return new UsersCountReportPart(
                    System.currentTimeMillis() - startTimeUsers,
                    countUsers);
        });
    }

    private CompletableFuture<AccountsReportPart> futureAccounts() {
        return CompletableFuture.supplyAsync(() -> {
            long startTime = System.currentTimeMillis();
            List<AccountResponseDTO> accounts = accountService.findAll();
            return new AccountsReportPart(
                    System.currentTimeMillis() - startTime,
                    accounts);
        });
    }

    @Override
    public ReportDTO findReportById(Long reportId) {
        var rep = reportRepository.findById(reportId);
        if (rep.isPresent()) {
            return parseReportToReportDTO(rep.get());
        } else {
            throw new EntityNotFoundException(Report.class.getSimpleName());
        }
    }

    private ReportDTO parseReportToReportDTO(Report report) {
        try {
            return new ReportDTO(
                    report.getId(),
                    objectMapper.readValue(report.getBody(), ReportBodyDTO.class),
                    report.getCreatedAt(),
                    report.getStatus()
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReportBodyDTO getReportBody(ReportDTO report) {
            return report.body();
    }

    @Override
    public List<ReportDTO> findAll() {
        return reportRepository.findAll().stream()
                .map(rep -> objectMapper.convertValue(rep, ReportDTO.class)).toList();
    }
}
