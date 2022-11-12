package com.ing.pt.assignment.model;

import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Model {

    public final WordGenerator wordGenerator;
    public final UserInputRetriever userInputRetriever;

    public String generateWord() {
        return wordGenerator.generateWord();
    }

    public char guessLetter() throws IncorrectInputLengthException, NotALetterException {
        return userInputRetriever.getLetter();
    }

    public boolean isCorrectLetter(char guessedLetter, String word) {
        return word.contains(Character.toString(guessedLetter));
    }

    public boolean shouldTryAgain() {
        try {
            return 'y' == userInputRetriever.getLetter();
        } catch (IncorrectInputLengthException e) {
            return false;
        } catch (NotALetterException e) {
            return false;
        }
    }
}
