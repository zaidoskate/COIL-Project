package logic.interfaces;

import java.util.ArrayList;
import logic.LogicException;
import logic.domain.UvAccountRequest;
public interface UvAccountRequestManagerInterface {
    public int insertUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException;

    public int deleteUvAccountRequest(UvAccountRequest uvAccountRequest) throws LogicException;
    public ArrayList<UvAccountRequest> getUvAccountRequests() throws LogicException;
    
    public boolean checkEmailRegistered(String email) throws LogicException;
    public boolean checkPersonalNumberRegistered(String personalNumber) throws LogicException;
}
