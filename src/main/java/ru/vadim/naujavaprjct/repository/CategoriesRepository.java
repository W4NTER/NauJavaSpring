package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vadim.naujavaprjct.entity.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    @Query("SELECT c FROM Categories c " +
            "JOIN c.operationsSet o " +
            "WHERE o.id = :operationId")
    Categories
    findCategoryByOperationId(@Param("operationId") Long operationId);
}
