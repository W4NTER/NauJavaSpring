package ru.vadim.naujavaprjct.repository.criteriaAPI.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.vadim.naujavaprjct.entity.Account;
import ru.vadim.naujavaprjct.entity.User;
import ru.vadim.naujavaprjct.repository.criteriaAPI.AccountRepositoryCriteria;

import java.util.Optional;

@Repository
public class AccountRepositoryCriteriaImpl implements AccountRepositoryCriteria {
    private final EntityManager entityManager;

    public AccountRepositoryCriteriaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Account> findByUserAndName(User user, String name) throws EmptyResultDataAccessException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

        Root<Account> accountsRoot = criteriaQuery.from(Account.class);
        Predicate userPredicate = criteriaBuilder.equal(accountsRoot.get("user"), user);
        Predicate namePredicate = criteriaBuilder.equal(accountsRoot.get("name"), name);

        criteriaQuery.select(accountsRoot).where(userPredicate, namePredicate);
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}
