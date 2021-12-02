package gr.aueb.dmst.jabuzzz.entities;

public class DifficultyOfGame {
    //number of answer
    public static int numberOfAnswers;
    public enum Difficulty {
        EASY, NORMAL, HARD
    }
    Difficulty difficulty;

    //constructor to select difficulty of game(Difficulty obj1 = Difficulty.NORMAL)
    public DifficultyOfGame(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void handleDifficulty() {
        switch (difficulty) {
            case EASY:
                numberOfAnswers = 3;
                break;
            case NORMAL:
                numberOfAnswers = 4;
                break;
            case HARD:
                numberOfAnswers = 5;
                break;
        }


        }
}





