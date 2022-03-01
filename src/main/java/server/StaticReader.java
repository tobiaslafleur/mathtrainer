package server;

import model.Answers;
import model.NewQuestions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class StaticReader {
    public static void printAnswers(int questId, int year) throws IOException {
        String filename = "åk" + year + "questions.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));

        while (reader.ready()) {
            String line = reader.readLine();
            String question;
            int categoryId = 0;
            int answerId = 1;
            int questionId;

            switch (line) {
                case "statistic" -> categoryId = 1;
                case "geometry" -> categoryId = 2;
                case "arithmetic" -> categoryId = 3;
                case "random" -> categoryId = 4;
            }

            questionId = Integer.parseInt(reader.readLine());
            question = reader.readLine();

            String ans1 = reader.readLine();
            Answers answer1 = new Answers(answerId++, questionId, ans1, true);

            String ans2 = reader.readLine();
            Answers answer2 = new Answers(answerId++, questionId, ans2, false);

            String ans3 = reader.readLine();
            Answers answer3 = new Answers(answerId++, questionId, ans3, false);

            String ans4 = reader.readLine();
            Answers answer4 = new Answers(answerId++, questionId, ans4, false);

            ArrayList<Answers> answers = new ArrayList<>();
            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);
            answers.add(answer4);

            NewQuestions newQuestion = new NewQuestions(questionId, categoryId, question, year, answers);

            System.out.println(newQuestion);
            if (questionId == 10) {
                System.out.println();
            }
        }
    }

    public static ArrayList<String> printQuestions(int year) throws IOException {
        String filename = "åk" + year + "questions.txt";
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        String question = "";

        ArrayList<String> questions = new ArrayList<>();

        while (reader.ready()) {
            question = reader.readLine();
            questions.add(question);
        }

        return questions;
    }
}
