package com.ing.pt.assignment.view;

import java.util.HashMap;
import java.util.Map;

public class AsciiHangmanProvider implements HangmanProvider<String> {

    private static final Map<Integer, String> HANGMAN_STAGES;
    private static final String STAGE_1 = """
              +---+
              |   |
                  |
                  |
                  |
                  |
            =========""";
    private static final String STAGE_2 = """
              +---+
              |   |
              O   |
                  |
                  |
                  |
            =========""";
    private static final String STAGE_3 = """
              +---+
              |   |
              O   |
              |   |
                  |
                  |
            =========""";
    private static final String STAGE_4 = """
              +---+
              |   |
              O   |
             /|   |
                  |
                  |
            =========""";
    private static final String STAGE_5 = """
              +---+
              |   |
              O   |
             /|\\  |
                  |
                  |
            =========""";
    private static final String STAGE_6 = """
              +---+
              |   |
              O   |
             /|\\  |
             /    |
                  |
            =========""";
    private static final String STAGE_7 = """
              +---+
              |   |
              O   |
             /|\\  |
             / \\  |
                  |
            =========""";

    static {
        HANGMAN_STAGES = new HashMap<>();
        HANGMAN_STAGES.put(1, STAGE_1);
        HANGMAN_STAGES.put(2, STAGE_2);
        HANGMAN_STAGES.put(3, STAGE_3);
        HANGMAN_STAGES.put(4, STAGE_4);
        HANGMAN_STAGES.put(5, STAGE_5);
        HANGMAN_STAGES.put(6, STAGE_6);
        HANGMAN_STAGES.put(7, STAGE_7);
    }

    @Override
    public String stage(int stage) {
        return HANGMAN_STAGES.get(stage);
    }
}
