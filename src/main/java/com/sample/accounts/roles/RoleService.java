package com.sample.accounts.roles;

import com.google.inject.ImplementedBy;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(RoleServiceBean.class)
public interface RoleService {

    RoleEntity getRole(Role role);
}
