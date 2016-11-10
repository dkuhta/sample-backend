package com.sample.accounts;

import com.sample.accounts.roles.RoleEntity;
import com.softteco.toolset.bl.AutofillAbstractAssembler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountDtoAssembler extends AutofillAbstractAssembler<AccountEntity, AccountDto> {

    @Override
    public void assemble(final AccountDto dto, final AccountEntity entity) {
        super.assemble(dto, entity);
        Set<String> roles = new HashSet<>();
        for (RoleEntity roleE : entity.getRoles()) {
            roles.add(roleE.getRole().name());
        }
        dto.roles = roles;
    }
}
