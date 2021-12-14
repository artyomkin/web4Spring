package web4.models;

public class LoginResponse {
    User user;
    Long id;

    public LoginResponse(User token, Long id) {
        this.user = user;
        this.id = id;
    }
}
