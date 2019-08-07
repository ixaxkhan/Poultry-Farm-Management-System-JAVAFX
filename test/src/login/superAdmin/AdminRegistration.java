
package login.superAdmin;


public class AdminRegistration {
    
    private String name;
    private String userName;
    private String Password;
    private String phone;

    public AdminRegistration(String name, String userName, String Password, String phone, String email, String ID) {
        this.name = name;
        this.userName = userName;
        this.Password = Password;
        this.phone = phone;
        this.email = email;
        this.ID = ID;
    }
    private String email;
    private String  ID ;   

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
}
