package ru.vadim.naujavaprjct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vadim.naujavaprjct.entity.Report;
import ru.vadim.naujavaprjct.service.ReportService;

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
}
