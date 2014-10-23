package sptest.domain.service;

public class RegisterRequest {
    private String id;
    private String password;
    private String confirmPassword;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasConfirmPassword() {
        return confirmPassword != null && !confirmPassword.isEmpty();
    }

    public boolean matchPassword() {
        return confirmPassword.equals(password);
    }
}
