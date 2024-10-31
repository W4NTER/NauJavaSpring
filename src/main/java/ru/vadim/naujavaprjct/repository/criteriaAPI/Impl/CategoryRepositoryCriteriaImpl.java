package ru.vadim.naujavaprjct.repository.criteriaAPI.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import ru.vadim.naujavaprjct.entity.Category;
import ru.vadim.naujavaprjct.entity.Operation;
import ru.vadim.naujavaprjct.repository.criteriaAPI.CategoryRepositoryCriteria;

import java.util.Optional;

@Repository
public class CategoryRepositoryCriteriaImpl implements CategoryRepositoryCriteria {
    private final EntityManager entityManager;
    private static final String OPERATION_SET_STRING = "operationSet";
    private static final String OPERATION_ID_STRING = "id";

    public CategoryRepositoryCriteriaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Category> findCategoryByOperationId(Long operationId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
        Root<Category> categoriesRoot = criteriaQuery.from(Category.class);
        Join<Category, Operation> operationsJoin = categoriesRoot.join(OPERATION_SET_STRING);

        criteriaQuery.where(criteriaBuilder.equal(operationsJoin.get(OPERATION_ID_STRING), operationId));

        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}
