package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "operation")
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByCategory(Category categoryId);
}
