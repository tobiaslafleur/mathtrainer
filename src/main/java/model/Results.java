package model;

public class Results {

    private int resultId;
    private int userId;
    private int categoryId;
    private char grade;
    private int score;
    private int maxScore;

    public Results(int id, int userId, int categoryId, char grade, int score, int maxScore) {
        this.resultId = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.grade = grade;
        this.score = score;
        this.maxScore = maxScore;
    }

    public Results(int userId, int categoryId, char grade, int score, int maxScore) {
        this.userId = userId;
        this.categoryId = categoryId;
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

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
}
