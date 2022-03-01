package server;

import model.Answers;
import model.NewQuestions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StaticReader {
    public static ArrayList<String> printAnswers(int year) throws IOException {
        String filename = "åk" + year + "answers.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String answer;

        ArrayList<String> answers = new ArrayList<>();

        while (reader.ready()) {
            answer = reader.readLine();
            answers.add(answer);
        }

        return answers;
    }

    public static ArrayList<String> printQuestions(int year) throws IOException {
        String filename = "åk" + year + "questions.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String question;

        ArrayList<String> questions = new ArrayList<>();

        while (reader.ready()) {
            question = reader.readLine();
            questions.add(question);
        }

        return questions;
    }
}
