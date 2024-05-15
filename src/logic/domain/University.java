package logic.domain;

public class University {
    private int universityId; 
    private String name; 
    private String country; 

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

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
