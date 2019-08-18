package model;

public class User {
    private long id;
    private String login;
    private String email;
    private String password;
    private String rank;
    private Long rating;

    public User() {
    }

    public User(long id, String login, String email, String password, String rank, Long rating) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.rank = rank;
        this.rating = rating;
    }

    public User(String name, String email, String password) {
        this.login = name;
        this.email = email;
        this.password = password;
    }

    public User(String login, String email, String password, String rank, Long rating) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.rank = rank;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
