package ru.vadim.naujavaprjct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.ReportBodyDTO;
import ru.vadim.naujavaprjct.dto.ReportDTO;
import ru.vadim.naujavaprjct.entity.Report;
import ru.vadim.naujavaprjct.service.ReportService;

import java.util.List;

@Controller
@RequestMapping("report")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public void createReport() {
        reportService.creteReport();
    }

    @GetMapping("/{report_id}")
    public String report(@PathVariable("report_id") Long id, Model model) {
        Report report = reportService.findReportById(id);
        model.addAttribute("reportBody", reportService.getReportBody(report));
        return "report";
    }

    @GetMapping
    @ResponseBody
    public List<ReportDTO> allReports() {
        return reportService.findAll();
    }
}
