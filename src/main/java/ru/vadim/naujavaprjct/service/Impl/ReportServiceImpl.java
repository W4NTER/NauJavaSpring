package ru.vadim.naujavaprjct.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import ru.vadim.naujavaprjct.service.ReportService;
import ru.vadim.naujavaprjct.service.UserService;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {
    private final UserService userService;
    private final ReportRepository reportRepository;
    private final AccountService accountService;
    private final ObjectMapper objectMapper;
    private static final Logger LOGGER = LogManager.getLogger();
    private Integer countUsers = null;
    private List<AccountResponseDTO> accounts;
    private Long countUsersTime;
    private Long findAllAccountsTime;
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
        generateReport(report);
        return report.getId();
    }

    private void generateReport(Report report) {
        CompletableFuture.runAsync(() -> {
            long startTime = System.currentTimeMillis();

            Thread countUsersThread = new Thread(() -> {
                countUsersTime = System.currentTimeMillis();
                countUsers = userService.listAll().size();
                countUsersTime = System.currentTimeMillis() - countUsersTime;
            });

            Thread accountsThread = new Thread(() -> {
                findAllAccountsTime = System.currentTimeMillis();
                accounts = accountService.findAll();
                findAllAccountsTime = System.currentTimeMillis() - findAllAccountsTime;
            });

            countUsersThread.start();
            accountsThread.start();

            try {
                countUsersThread.join();
                accountsThread.join();
                report.setStatus(ReportStatus.COMPLETED.toString());
                report.setBody(
                        objectMapper.writeValueAsString(
                                new ReportBodyDTO(
                                        countUsers,
                                        countUsersTime,
                                        findAllAccountsTime,
                                        System.currentTimeMillis() - startTime,
                                        accounts)));

                reportRepository.save(report);
            } catch (InterruptedException | JsonProcessingException e) {
                LOGGER.info(e.getMessage());
                report.setStatus(ReportStatus.ERROR.toString());
                reportRepository.save(report);
                throw new ReportException();
            }
        });
    }

    @Override
    public Report findReportById(Long reportId) {
        var rep = reportRepository.findById(reportId);
        if (rep.isPresent()) {
            return rep.get();
        } else {
            throw new EntityNotFoundException(Report.class.getSimpleName());
        }
    }

    @Override
    public ReportBodyDTO getReportBody(Report report) {
        try {
            return objectMapper.readValue(report.getBody(), ReportBodyDTO.class);
        } catch (JsonProcessingException e) {
            throw new ReportException();
        }
    }

    @Override
    public List<ReportDTO> findAll() {
        return reportRepository.findAll().stream()
                .map(rep -> objectMapper.convertValue(rep, ReportDTO.class)).toList();
    }
}
