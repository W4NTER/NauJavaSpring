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
    private static final String USER_PREDICATE = "user";
    private static final String NAME_PREDICATE = "name";

    public AccountRepositoryCriteriaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Account> findByUserAndName(User user, String name) throws EmptyResultDataAccessException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);

        Root<Account> accountsRoot = criteriaQuery.from(Account.class);
        Predicate userPredicate = criteriaBuilder.equal(accountsRoot.get(USER_PREDICATE), user);
        Predicate namePredicate = criteriaBuilder.equal(accountsRoot.get(NAME_PREDICATE), name);

        criteriaQuery.select(accountsRoot).where(userPredicate, namePredicate);
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}
