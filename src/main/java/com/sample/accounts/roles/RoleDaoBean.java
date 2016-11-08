package com.sample.accounts.roles;

import com.softteco.toolset.jpa.AbstractJpaDao;

import javax.persistence.Query;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class RoleDaoBean extends AbstractJpaDao<RoleEntity, Long> implements RoleDao {

    @Override
    public RoleEntity findByRole(final Role role) {
        final Query query = getEntityManager().createQuery("select e from RoleEntity e where e.role = :role");
        query.setParameter("role", role);
        return getSingleResult(query);
    }
}
