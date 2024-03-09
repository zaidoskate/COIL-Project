package logic.domain;

public class Professor extends User{
    private String academicArea; 

    public String getAcademicArea() {
        return academicArea;
    }

    public void setAcademicArea(String academicArea) {
        this.academicArea = academicArea;
    }
   
}
