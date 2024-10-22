package ru.vadim.naujavaprjct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vadim.naujavaprjct.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c " +
            "JOIN c.operationSet o " +
            "WHERE o.id = :operationId")
    Optional<Category>
    findCategoryByOperationId(@Param("operationId") Long operationId);
}
