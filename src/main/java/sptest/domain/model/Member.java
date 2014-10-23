package sptest.domain.model;

public class Member {
    private String id;
    private String password;
    private String name;

    public Member() {
    }

    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}
