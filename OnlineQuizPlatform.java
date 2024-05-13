import java.util.Scanner;

class Question {
    private String questionText;
    private String[] options;
    private int correctOption;

    public Question(String questionText, String[] options, int correctOption) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }
}

class Quiz {
    private Question[] questions;
    private int score;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }
            System.out.print("Enter your answer: ");
            int userAnswer = scanner.nextInt();
            if (userAnswer == question.getCorrectOption()) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect!");
            }
        }
        System.out.println("Quiz completed! Your score: " + score + "/" + questions.length);
    }
}

public class OnlineQuizPlatform {
    public static void main(String[] args) {
        Question[] questions = {
            new Question("What is the capital of France?",
                         new String[]{"Paris", "London", "Berlin", "Rome"},
                         1),
            new Question("What is the largest planet in our solar system?",
                         new String[]{"Jupiter", "Saturn", "Mars", "Earth"},
                         0),
            new Question("What is the powerhouse of the cell?",
                         new String[]{"Nucleus", "Mitochondria", "Chloroplast", "Ribosome"},
                         1)
        };

        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
