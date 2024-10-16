package ru.vadim.naujavaprjct.service;

import ru.vadim.naujavaprjct.dto.OperationsDTO;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;

import java.util.List;

public interface OperationsService {
    List<Operations> findAllByCategory(Long categoryId);
    Operations addOperation(OperationsDTO operationsDTO);
}
