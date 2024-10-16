package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;

import java.util.List;

@RepositoryRestResource(path = "operations")
public interface OperationsRepository extends JpaRepository<Operations, Long> {
    List<Operations> findAllByCategory(Categories categoryId);
}
