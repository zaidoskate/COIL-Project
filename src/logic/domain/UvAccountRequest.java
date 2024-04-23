/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic.domain;

/**
 *
 * @author zaido
 */
public class UvAccountRequest extends AccountRequest{
    private int idRequest;
    private String name;
    private String lastName;
    private String email;
    private String personalNumber;
    private String idDepartment;
    
    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPersonalNumber() {
        return personalNumber;
    }
}
