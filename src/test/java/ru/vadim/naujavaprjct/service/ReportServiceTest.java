package ru.vadim.naujavaprjct.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
public class ReportServiceTest {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private ReportService reportService;

    @Test
    void testThatGenerateReportReturnedSucceed() throws ExecutionException, InterruptedException {
        Long reportId = reportService.creteReport();
        LOGGER.info(String.format("reportId = %d", reportId));
        Thread.sleep(200);
        LOGGER.info("body - " + reportService.findReportById(reportId).getBody());
        LOGGER.info(reportService.findReportById(reportId).getStatus());
    }
}
