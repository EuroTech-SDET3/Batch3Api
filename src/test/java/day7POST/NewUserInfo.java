package day7POST;

public class NewUserInfo {

    private String email;
    private String password;
    private String name;
    private String google;
    private String facebook;
    private String github;


    public String getEmail() {
        return email;
    }
    public NewUserInfo(){}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public NewUserInfo(String email, String password, String name, String google, String facebook, String github) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.google = google;
        this.facebook = facebook;
        this.github = github;
    }

    @Override
    public String toString() {
        return "NewUserInfo{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", google='" + google + '\'' +
                ", facebook='" + facebook + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}
