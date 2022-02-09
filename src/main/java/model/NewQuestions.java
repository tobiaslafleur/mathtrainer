package model;

import java.util.ArrayList;

public class NewQuestions {
    private int id;
    private int categoryId;
    private String question;
    private int year;
    private ArrayList<Answers> answers;

    public NewQuestions(int id, int categoryId, String question, int year, ArrayList<Answers> answers) {
        this.id = id;
        this.categoryId = categoryId;
        this.question = question;
        this.year = year;
        this.answers = answers;
    }

    public NewQuestions(int id, int categoryId, String question, int year) {
        this.id = id;
        this.categoryId = categoryId;
        this.question = question;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answers> answers) {
        this.answers = answers;
    }
}
