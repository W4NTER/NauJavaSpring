package ru.vadim.naujavaprjct.service.reportService;

import ru.vadim.naujavaprjct.dto.ReportBodyDTO;
import ru.vadim.naujavaprjct.dto.ReportDTO;
import ru.vadim.naujavaprjct.entity.Report;

import java.util.List;

public interface ReportService {
    Long creteReport();

    ReportDTO findReportById(Long reportId);

    ReportBodyDTO getReportBody(ReportDTO  report);

    List<ReportDTO> findAll();
}
