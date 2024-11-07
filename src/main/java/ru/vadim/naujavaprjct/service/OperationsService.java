package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.request.OperationRequestDTO;
import ru.vadim.naujavaprjct.dto.response.OperationResponseDTO;
import ru.vadim.naujavaprjct.entity.Operation;

import java.util.List;

public interface OperationsService {
    List<OperationResponseDTO> findAllByCategory(Long categoryId);
    OperationResponseDTO addOperation(OperationRequestDTO operationRequestDTO);
}
