package model;

public class DetailedResults {

    private int id;
    private int questionId;
    private Results results;
    private int guessedId;
    private int correctId;

    public DetailedResults(int id, int questionId, Results results, int guessedId, int correctId) {
        this.id = id;
        this.questionId = questionId;
        this.results = results;
        this.guessedId = guessedId;
        this.correctId = correctId;
    }

    public DetailedResults(int questionId, Results results, int guessedId, int correctId) {
        this.questionId = questionId;
        this.results = results;
        this.guessedId = guessedId;
        this.correctId = correctId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public int getGuessedId() {
        return guessedId;
    }

    public void setGuessedId(int guessedId) {
        this.guessedId = guessedId;
    }

    public int getCorrectId() {
        return correctId;
    }

    public void setCorrectId(int correctId) {
        this.correctId = correctId;
    }
}
