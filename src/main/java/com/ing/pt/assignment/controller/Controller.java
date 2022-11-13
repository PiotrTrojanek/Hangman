package com.ing.pt.assignment.controller;

import com.ing.pt.assignment.model.Model;
import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;
import com.ing.pt.assignment.view.View;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class Controller {

    private final View view;
    private final Model model;

    public void startGame() {
        boolean isDone = false;
        view.printWelcomeMessage();
        while (!isDone) {
            String word = model.generateWord();
            singleTry(word);
            view.printShouldContinue();
            if (!model.shouldTryAgain()) {
                isDone = true;
            }
        }
    }

    private void singleTry(String word) {
        int guessesLeft = 7;
        boolean succeed = false;
        Set<Character> guessedLetters = new HashSet<>();
        while (stillHaveGuesses(guessesLeft)) {
            view.printGuessesLeft(guessesLeft);
            view.printMaskedWord(word, guessedLetters);
            view.printGuessLetter();
            char guessedLetter = guessLetter();

            if (model.isCorrectLetter(guessedLetter, word)) {
                view.printCorrectGuess();
                guessedLetters.add(guessedLetter);
            } else {
                view.printIncorrectGuess();
                view.printHangmanStage(8 - guessesLeft);
                guessesLeft--;
            }
            if (checkIfFinish(word, guessedLetters)) {
                succeed = true;
                break;
            }
        }
        if (succeed) {
            view.printCongratulations(word);
        } else {
            view.printMaybeNextTime(word);
        }
    }

    private boolean stillHaveGuesses(int guessesLeft) {
        return guessesLeft > 0;
    }

    private boolean checkIfFinish(String word, Set<Character> guessedLetter) {
        return word.chars().distinct().count() == guessedLetter.size();
    }

    private char guessLetter() {
        try {
            return model.guessLetter();
        } catch (IncorrectInputLengthException e) {
            view.printShouldBeOneCharLong();
            return guessLetter();
        } catch (NotALetterException e) {
            view.printShouldBeLetter();
            return guessLetter();
        }
    }
}
