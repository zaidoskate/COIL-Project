package logic.domain;

/**
 *
 * @author chuch
 */
public class University {
    private int universityId; 
    private String name; 
    private String country; 

    /**
     *
     * @return
     */
    public int getUniversityId() {
        return universityId;
    }

    /**
     *
     * @param universityId
     */
    public void setUniversityId(int universityId) {
        this.universityId = universityId;
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
    public String getCountry() {
        return country;
    }
    
    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        University universityToCompare = (University) object;
        if(this.getUniversityId() != universityToCompare.getUniversityId()) {
            return false;
        }
        if(!this.getName().equals(universityToCompare.getName())) {
            return false;
        }
        if(!this.getCountry().equals(universityToCompare.getCountry())) {
            return false;
        }
        return true;
    }
}
