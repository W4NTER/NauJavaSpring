package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.ReportDTO;
import ru.vadim.naujavaprjct.entity.Report;

public interface ReportService {
    Long creteReport();

    Report findReportById(Long reportId);

    //Вот тут не сходится с логикой принимаем и отдаем дто или сущности,
    // но тут и дто не по сущности, а по 1 полю. Надо как-то поменять, пока не придумал как)
    ReportDTO getReportBody(Report report);
}
