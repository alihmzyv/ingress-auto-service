package az.ingress.ingressautoservice.repository.impl;

import az.ingress.ingressautoservice.entity.Account;
import az.ingress.ingressautoservice.entity.Account_;
import az.ingress.ingressautoservice.repository.AccountRepository;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.CriteriaDefinition;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    @PersistenceUnit
    private SessionFactory sessionFactory;

    @Override
    public Account save(Account account) {
        sessionFactory.inTransaction(session -> {
            session.persist(account);
        });
        return account;
    }

    @Override
    public boolean existsByPhoneNumberOrEmailAddress(String emailAddress, String phoneNumber) {
        if (ObjectUtils.isEmpty(emailAddress) && ObjectUtils.isEmpty(phoneNumber)) {
            return false;
        }

        CriteriaQuery<Boolean> criteriaQuery = new CriteriaDefinition<>(sessionFactory, Boolean.class) {{
            Root<Account> root = from(Account.class);
            select(gt(count(root), 0));
            Optional.ofNullable(emailAddress)
                    .ifPresent(emailAddress -> {
                        where(equal(root.get(Account_.emailAddress), emailAddress));
                    });
            Optional.ofNullable(phoneNumber)
                    .ifPresent(emailAddress -> {
                        where(equal(root.get(Account_.phoneNumber), phoneNumber));
                    });
        }};
        return sessionFactory.fromSession(session -> session.createSelectionQuery(criteriaQuery)
                .getSingleResult());
    }

    @Override
    public Optional<Account> findById(Long accountId) {
        return sessionFactory.fromTransaction(session -> Optional.ofNullable(session.find(Account.class, accountId)));
    }

    @Override
    public boolean existsById(Long id) {
        return sessionFactory.fromSession(session -> session.createSelectionQuery(
                "select count(*) from Account account", Long.class)
                .getSingleResult() > 0);
    }
}
