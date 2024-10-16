package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vadim.naujavaprjct.entity.Categories;

import java.util.Optional;

@RepositoryRestResource(path = "categories")
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT c FROM Categories c " +
            "JOIN c.operationsSet o " +
            "WHERE o.id = :operationId")
    Optional<Categories>
    findCategoryByOperationId(@Param("operationId") Long operationId);
}
