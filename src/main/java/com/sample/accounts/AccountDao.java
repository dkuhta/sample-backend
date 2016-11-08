package com.sample.accounts;

import com.google.inject.ImplementedBy;
import com.softteco.toolset.dto.PageInfoDto;
import java.util.List;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(AccountDaoBean.class)
public interface AccountDao {

    AccountEntity findByEmail(String email);

    List<AccountEntity> findAll(PageInfoDto pageInfo);

    void persist(AccountEntity entity);
}
