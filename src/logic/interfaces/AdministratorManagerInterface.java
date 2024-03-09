package logic.interfaces;

import logic.domain.Administrator;

public interface AdministratorManagerInterface {
    int insertAdministrator(Administrator administrator);
    Administrator getAdministratorByIdUser(int id);
    Administrator getAdministratorByIdAdministrator(int id);
}
