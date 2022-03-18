package model;

public class Answers {
    private int id;
    private int questionId;
    private String answer;
    private boolean correct;

    public Answers(int id, int questionId, String answer, boolean correct) {
        this.id = id;
        this.questionId = questionId;
        this.answer = answer;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", answer='" + answer + '\'' +
                ", correct=" + correct +
                '}';
    }
}
