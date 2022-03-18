package model;

public class DetailedResults {
    private String question;
    private String correctAnswer;
    private String guessedAnswer;

    public DetailedResults(String question, String correctAnswer, String guessedAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.guessedAnswer = guessedAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getGuessedAnswer() {
        return guessedAnswer;
    }

    @Override
    public String toString() {
        return "NewDetailedResults{" +
                "question='" + question + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", guessedAnswer='" + guessedAnswer + '\'' +
                '}';
    }
}
