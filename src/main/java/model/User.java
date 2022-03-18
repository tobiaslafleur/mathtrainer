package model;

public class User {
    private int id;
    private String username;
    private String password;
    private int year;
    private boolean isGuest;
    private int gameScore;

    public User(int id, String username, String password, int year) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.year = year;
    }

    public User(String username, String password, boolean isGuest) {
        this.username = username;
        this.password = password;
        this.isGuest = isGuest;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isGuest() {
        return isGuest;
    }

    public int getGameScore() {
        return gameScore;
    }

    public void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    @Override
    public String toString() {
        return "NewUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", year=" + year +
                '}';
    }
}
