package logic.interfaces;

import logic.domain.AccountRequest;

public interface AccountRequestManagerInterface {
    public int addAccountRequest(AccountRequest accountRequest);
    public int deleteAccountRequestById(String id);
    public AccountRequest getAccountRequestById(String id);
}
