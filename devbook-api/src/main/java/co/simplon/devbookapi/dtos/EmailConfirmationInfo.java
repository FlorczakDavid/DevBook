package co.simplon.devbookapi.dtos;

public record EmailConfirmationInfo(String email, String token) {
    public EmailConfirmationInfo(String email, String token) {
        this.email = email;
        this.token = token;
    }

    @Override
    public String toString() {
        return "EmailConfirmationInfo{" +
                "email='" + email + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
