package model;

public class Results implements IResults {

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

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Category getCategoryId() {
        return category;
    }

    public void setCategoryId(Category category) {
        this.category = category;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
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
