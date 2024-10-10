package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;

import java.util.List;

public interface OperationsRepository extends JpaRepository<Operations, Long> {
    List<Operations> findAllByCategory(Categories categoryId);
}
