package logic.domain;

public class Department {
    private String idDepartment;
    private String name;
    private String region;
    private int idAcademicArea;
    
    public Department() {
        
    }
    public String getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(String idFacultad) {
        this.idDepartment = idFacultad;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public int getIdAcademicArea() {
        return idAcademicArea;
    }

    public void setIdAcademicArea(int idAcademicArea) {
        this.idAcademicArea = idAcademicArea;
    }
    
    @Override
    public boolean equals(Object object) {
        Department departmentToCompare = (Department) object;
        if (!this.idDepartment.equals(departmentToCompare.getIdDepartment())) {
            return false;
        }
        if (!this.name.equals(departmentToCompare.getName())) {
            return false; 
        }
        if (!this.region.equals(departmentToCompare.getRegion())) {
            return false;
        }
        return true;
    }
}
