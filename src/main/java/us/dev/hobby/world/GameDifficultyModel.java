package us.dev.hobby.world;

/**
 * @author Mark Johnson
 */
public enum GameDifficultyModel {
    PEACEFUL(0), EASY(1), NORMAL(2), HARD(3);

    private static final GameDifficultyModel[] ID_MAPPING = new GameDifficultyModel[GameDifficultyModel.values().length];

    private final int difficultyID;

    GameDifficultyModel(int difficultyID) {
        this.difficultyID = difficultyID;
    }

    int getDifficultyID() {
        return difficultyID;
    }

    public static GameDifficultyModel getDifficultyByID(int difficultyID) {
        return ID_MAPPING[difficultyID % ID_MAPPING.length];
    }

    static {
        for (GameDifficultyModel enumdifficulty : values()) {
            ID_MAPPING[enumdifficulty.difficultyID] = enumdifficulty;
        }
    }

}
