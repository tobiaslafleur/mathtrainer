package model;

import java.util.ArrayList;

public class Questions {
    private int id;
    private int categoryId;
    private String question;
    private int year;
    private ArrayList<Answers> answers;

    public Questions(int id, int categoryId, String question, int year, ArrayList<Answers> answers) {
        this.id = id;
        this.categoryId = categoryId;
        this.question = question;
        this.year = year;
        this.answers = answers;
    }

    public Questions(int id, int categoryId, String question, int year) {
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

    @Override
    public String toString() {
        return "NewQuestions{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", question='" + question + '\'' +
                ", year=" + year +
                ", answers=" + answers +
                '}';
    }
}
