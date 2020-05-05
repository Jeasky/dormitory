package service;

import domain.Account;

public interface AccountService {

    public int addAccount(Account account);

    public int deleteAccount(Integer accountid);

    public Account display(Integer accountid);

    public int updateAccount(Account account);
}
