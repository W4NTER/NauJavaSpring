package ru.vadim.naujavaprjct.repository.criteriaAPI.criteriaImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import ru.vadim.naujavaprjct.entity.Accounts;
import ru.vadim.naujavaprjct.entity.Users;
import ru.vadim.naujavaprjct.repository.criteriaAPI.AccountsRepositoryCriteria;

import java.util.Optional;

@Repository
public class AccountsRepositoryCriteriaImpl implements AccountsRepositoryCriteria {
    private final EntityManager entityManager;

    public AccountsRepositoryCriteriaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Accounts> findByUserAndName(Users user, String name) throws EmptyResultDataAccessException {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Accounts> criteriaQuery = criteriaBuilder.createQuery(Accounts.class);

        Root<Accounts> accountsRoot = criteriaQuery.from(Accounts.class);
        Predicate userPredicate = criteriaBuilder.equal(accountsRoot.get("user"), user);
        Predicate namePredicate = criteriaBuilder.equal(accountsRoot.get("name"), name);

        criteriaQuery.select(accountsRoot).where(userPredicate, namePredicate);
        return Optional.ofNullable(entityManager.createQuery(criteriaQuery).getSingleResult());
    }
}
