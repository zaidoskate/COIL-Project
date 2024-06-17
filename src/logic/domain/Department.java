package logic.domain;

/**
 *
 * @author chuch
 */
public class Department {
    private String idDepartment;
    private String name;
    private String region;
    private int idAcademicArea;
    
    /**
     *
     */
    public Department() {
        
    }

    /**
     *
     * @return
     */
    public String getIdDepartment() {
        return idDepartment;
    }

    /**
     *
     * @param idFacultad
     */
    public void setIdDepartment(String idFacultad) {
        this.idDepartment = idFacultad;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getRegion() {
        return region;
    }

    /**
     *
     * @param region
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     *
     * @return
     */
    public int getIdAcademicArea() {
        return idAcademicArea;
    }

    /**
     *
     * @param idAcademicArea
     */
    public void setIdAcademicArea(int idAcademicArea) {
        this.idAcademicArea = idAcademicArea;
    }
    
    /**
     *
     * @param object
     * @return
     */
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
