package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.OperationRequestDTO;
import ru.vadim.naujavaprjct.entity.Operation;

import java.util.List;

public interface OperationsService {
    List<Operation> findAllByCategory(Long categoryId);
    Operation addOperation(OperationRequestDTO operationRequestDTO);
}
