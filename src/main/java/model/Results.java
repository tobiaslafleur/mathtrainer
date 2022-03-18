package model;

public class Results {
    private int resultId;
    private int userId;
    private Category category;
    private char grade;
    private int score;
    private int maxScore;

    public Results(int id, int userId, Category category, char grade, int score, int maxScore) {
        this.resultId = id;
        this.userId = userId;
        this.category = category;
        this.grade = grade;
        this.score = score;
        this.maxScore = maxScore;
    }

    public Results(int userId, Category category, char grade, int score, int maxScore) {
        this.userId = userId;
        this.category = category;
        this.grade = grade;
        this.score = score;
        this.maxScore = maxScore;
    }

    public Category getCategoryId() {
        return category;
    }

    public char getGrade() {
        return grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Results{" +
                ", userId=" + userId +
                ", category=" + category +
                ", grade=" + grade +
                ", score=" + score +
                '}';
    }
}
