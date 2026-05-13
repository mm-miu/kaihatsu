package bean;

public class Teacher extends User implements java.io.Serializable{
    
    private String id;
    private String password;
    private String name;
    private School school;
    private String authority;

    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public School getSchool() {
        return school;
    }
    public String getAuthority(){
        return authority;
    }

    public void setId(String id) {
        this.id=id;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setSchool(School school) {
        this.school=school;
    }

    public void setAuthority(String authority){
        this.authority=authority;
    }
}
