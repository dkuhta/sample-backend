package com.sample.accounts.roles;

import com.google.inject.Inject;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class RoleServiceBean implements RoleService {

    @Inject
    private RoleDao roleDao;

    @Override
    public RoleEntity getRole(final Role role) {
        final RoleEntity roleE = roleDao.findByRole(role);
        if (roleE == null) {
            throw new InternalError();
        }
        return roleE;
    }
}
