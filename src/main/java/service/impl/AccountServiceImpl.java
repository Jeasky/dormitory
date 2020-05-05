package service.impl;

import domain.Account;
import mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.AccountService;

@Service("AccountServiceImpl")
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int addAccount(Account account){

        int result = this.accountMapper.insert(account);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int deleteAccount(Integer accountid){

        int result = this.accountMapper.deleteByPrimaryKey(accountid);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = true)
    public Account display(Integer accountid){

        Account account = this.accountMapper.selectByPrimaryKey(accountid);

        return account;
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public int updateAccount(Account account){

        int result = this.accountMapper.updateByPrimaryKey(account);

        return result;
    }
}
