package ru.vadim.naujavaprjct.repository.criteriaAPI.criteriaImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import ru.vadim.naujavaprjct.entity.Categories;
import ru.vadim.naujavaprjct.entity.Operations;
import ru.vadim.naujavaprjct.repository.criteriaAPI.CategoriesRepositoryCriteria;

public class CategoriesRepositoryCriteriaImpl implements CategoriesRepositoryCriteria {
    private final EntityManager entityManager;

    public CategoriesRepositoryCriteriaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Categories findCategoryByOperationId(Long operationId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Categories> criteriaQuery = criteriaBuilder.createQuery(Categories.class);
        Root<Categories> categoriesRoot = criteriaQuery.from(Categories.class);
        Join<Categories, Operations> operationsJoin = categoriesRoot.join("operationsSet");

        criteriaQuery.where(criteriaBuilder.equal(operationsJoin.get("id"), operationId));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }
}
