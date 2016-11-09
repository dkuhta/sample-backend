package com.sample.accounts;

import com.softteco.toolset.jpa.AbstractJpaDao;

import javax.persistence.Query;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountDaoBean extends AbstractJpaDao<AccountEntity, Long> implements AccountDao {

    @Override
    public AccountEntity findByEmail(final String email) {
        final Query query = getEntityManager().createQuery("select e from AccountEntity e where e.email = :email");
        query.setParameter("email", email);
        return getSingleResult(query);
    }

    @Override
    public void flush() {
        getEntityManager().flush();
    }
}
