package sptest.domain.service;

public class Auth {
    private String id;
    private String name;

    public Auth(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
