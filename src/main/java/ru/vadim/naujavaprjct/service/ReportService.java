package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.ReportBodyDTO;
import ru.vadim.naujavaprjct.dto.ReportDTO;
import ru.vadim.naujavaprjct.entity.Report;

import java.util.List;

public interface ReportService {
    Long creteReport();

    Report findReportById(Long reportId);

    //Вот тут не сходится с логикой принимаем и отдаем дто или сущности,
    // но тут и дто не по сущности, а по 1 полю. Надо как-то поменять, пока не придумал как)
    ReportBodyDTO getReportBody(Report report);

    List<ReportDTO> findAll();
}
