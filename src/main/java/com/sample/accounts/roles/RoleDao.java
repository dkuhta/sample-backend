package com.sample.accounts.roles;

import com.google.inject.ImplementedBy;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(RoleDaoBean.class)
public interface RoleDao {

    RoleEntity findByRole(Role role);
}
