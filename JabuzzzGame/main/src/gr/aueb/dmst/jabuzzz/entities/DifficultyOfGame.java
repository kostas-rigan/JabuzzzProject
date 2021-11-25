package gr.aueb.dmst.jabuzzz.entities;

public class DifficultyOfGame {
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
                //do stuff
                break;
            case NORMAL:
                //do stuff
                break;
            case HARD:
                //do stuff
                break;
        }

        }
}





