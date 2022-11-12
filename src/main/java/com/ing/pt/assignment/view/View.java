package com.ing.pt.assignment.view;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

@RequiredArgsConstructor
public class View {
    private static final String SHOULD_BE_ONE_CHAR_LONG = "Please provide exactly one letter: ";
    private static final String WELCOME_MESSAGE = """
            Welcome to Hangman
            Your word will be generated shortly.
            Guess a letter, if you fail to guess correct one more then 7 times you will fail""";
    private static final String SHOULD_BE_LETTER = "Please provider letter [a-z]: ";
    private static final String CONGRATULATIONS = "Congratulations you successfully guessed the word: %s";
    private static final String GUESS_LETTER = "Guess letter: ";
    private static final String MAYBE_NEXT_TIME = """
            Unfortunately you did not guess the word fast enough, maybe next time you will do it.
            The word was: %s""";
    private static final String GUESSES_LEFT = "You have %s guesses left.";
    private static final String CORRECT_GUESS = "Well done, this is correct letter.";
    private static final String INCORRECT_GUESS = "Letter incorrect, try another one.";
    private static final String SHOULD_CONTINUE = "If you want to try again type [y]: ";

    private final MessagePresenter presenter;

    public void printWelcomeMessage() {
        presenter.showMessage(WELCOME_MESSAGE, false);
    }

    public void printMaskedWord(String word, Set<Character> correctlyGuessedLetters) {
        if (!correctlyGuessedLetters.isEmpty()) {
            presenter.showMessage(word.replaceAll("[^" + StringUtils.join(correctlyGuessedLetters, "") + "]", "*"), false);
        } else {
            presenter.showMessage("*".repeat(word.length()), false);
        }
    }

    public void printShouldBeOneCharLong() {
        presenter.showMessage(SHOULD_BE_ONE_CHAR_LONG, true);
    }

    public void printShouldBeLetter() {
        presenter.showMessage(SHOULD_BE_LETTER, true);
    }

    public void printCongratulations(String word) {
        presenter.showMessage(String.format(CONGRATULATIONS, word), false);
    }

    public void printGuessLetter() {
        presenter.showMessage(GUESS_LETTER, true);
    }

    public void printMaybeNextTime(String word) {
        presenter.showMessage(String.format(MAYBE_NEXT_TIME, word), false);
    }

    public void printGuessesLeft(int guessesLeft) {
        presenter.showMessage(String.format(GUESSES_LEFT, guessesLeft), false);
    }

    public void printCorrectGuess() {
        presenter.showMessage(CORRECT_GUESS, false);
    }

    public void printIncorrectGuess() {
        presenter.showMessage(INCORRECT_GUESS, false);
    }

    public void printShouldContinue() {
        presenter.showMessage(SHOULD_CONTINUE, true);
    }
}
