package model;

public class NewUser {

    private int id;
    private String username;
    private String password;
    private int year;

    public NewUser(int id, String username, String password, int year) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.year = year;
    }

    public NewUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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
