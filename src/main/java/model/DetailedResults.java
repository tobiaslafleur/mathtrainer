package model;

public class DetailedResults implements IResults{

    private int id;
    private NewQuestions question;
    private int results;
    private Answers guessedAnswer;
    private Answers correctAnswer;

    public DetailedResults(int id, NewQuestions question, int results, Answers guessedAnswer, Answers correctAnswer) {
        this.id = id;
        this.question = question;
        this.results = results;
        this.guessedAnswer = guessedAnswer;
        this.correctAnswer = correctAnswer;
    }

    public DetailedResults(NewQuestions question, Answers guessedAnswer, Answers correctAnswer) {
        this.question = question;
        this.guessedAnswer = guessedAnswer;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NewQuestions getQuestion() {
        return question;
    }

    public void setQuestion(NewQuestions question) {
        this.question = question;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public Answers getGuessedAnswer() {
        return guessedAnswer;
    }

    public void setGuessedAnswer(Answers guessedAnswer) {
        this.guessedAnswer = guessedAnswer;
    }

    public Answers getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answers correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
