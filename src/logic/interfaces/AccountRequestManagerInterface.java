package logic.interfaces;

import logic.domain.AccountRequest;

public interface AccountRequestManagerInterface {
    public int addAccountRequest(AccountRequest accountRequest);
    public int deleteAccountRequestById(int id);
    public AccountRequest getAccountRequestById(int id);
}
