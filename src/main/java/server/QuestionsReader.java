package server;

import model.Answers;
import model.NewQuestions;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuestionsReader {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("åk6questions.txt")));
        while (reader.ready()) {
            int id = Integer.parseInt(reader.readLine());
            int catId = 3;
            String question = reader.readLine();
            int year = 6;

            String ans1 = reader.readLine();
            Answers answer1 = new Answers(1, 1, ans1, true);

            String ans2 = reader.readLine();
            Answers answer2 = new Answers(2, 1, ans2, false);

            String ans3 = reader.readLine();
            Answers answer3 = new Answers(3, 1, ans3, false);

            String ans4 = reader.readLine();
            Answers answer4 = new Answers(4, 1, ans4, false);

            ArrayList<Answers> answers = new ArrayList<>();
            answers.add(answer1);
            answers.add(answer2);
            answers.add(answer3);
            answers.add(answer4);

            NewQuestions newQuestion = new NewQuestions(id, catId, question, year, answers);

            System.out.println(newQuestion);
        }
    }
}
