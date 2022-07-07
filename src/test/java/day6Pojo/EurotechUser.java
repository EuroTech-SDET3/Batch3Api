package day6Pojo;

public class EurotechUser {

                /*
{
    "id": 34,
    "email": "sdet_blg@gmail.com",
    "name": "Blg",
    "company": "Amazon",
    "status": "Junior Developer",
    "profileId": 7
}
                  */
    private double id;
    private String email;
    private String name;
    private String company;
    private String status;
    private double profileId;

    public EurotechUser(double id, String email, String name, String company, String status, double profileId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.company = company;
        this.status = status;
        this.profileId = profileId;
    }
    public EurotechUser(){}

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getProfileId() {
        return profileId;
    }

    public void setProfileId(double profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "EurotechUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", status='" + status + '\'' +
                ", profileId=" + profileId +
                '}';
    }
}
