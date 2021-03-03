package com.example.tugaspb.quiz;

public class QuestionBank {

    private String textQuestions [] = {
            "Apa warna bendera indonesia?",
            "siapa presiden pertama indonesia?",
            "siapa prseiden indonesia sekarang?",
            "tahun berapa indonesia merdeka",
    };

    // array of multiple choices for each question
    private String multipleChoice [][] = {
            {"Merah - Putih", "putih merah", "kuning", "hijau"},
            {"Soeharto", "Jokowi", "SBY", "Ir Soekarno"},
            {"Megawati", "Prabowo", "Bj Habibi", "Jokowi"},
            {"1999", "2018", "1945", "2000"},
    };

    private String mCorrectAnswers[] = {"Merah - Putih", "Ir Soekarno", "Jokowi", "1945", "2018"};

    public int getLength(){
        return textQuestions.length;
    }

    public String getQuestion(int a) {
        String question = textQuestions[a];
        return question;
    }

    public String getChoice(int index, int num) {
        String choice0 = multipleChoice[index][num-1];
        return choice0;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }
}
