package ru.vadim.naujavaprjct.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vadim.naujavaprjct.dto.request.OperationRequestDTO;
import ru.vadim.naujavaprjct.dto.response.OperationResponseDTO;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.service.OperationsService;



@Controller
@RequestMapping("/operations")
public class OperationController {
    private final OperationsService operationsService;

    public OperationController(OperationsService operationsService) {
        this.operationsService = operationsService;
    }

    @GetMapping("/{category_id}")
    public String allOperations(Model model,
                                @PathVariable("category_id") Long categoryId) {
        var operations = operationsService.findAllByCategory(categoryId);
        model.addAttribute("operations", operations);
        return "operations_list_page";
    }

    @PostMapping("/add")
    @ResponseBody
    public OperationResponseDTO addOperation(@RequestBody OperationRequestDTO operationRequestDTO) {
        return operationsService.addOperation(operationRequestDTO);
    }
}
