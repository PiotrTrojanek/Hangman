package com.ing.pt.assignment.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ViewTest {

    @Mock
    private MessagePresenter messagePresenter;
    @Mock
    private HangmanProvider hangmanProvider;

    private View subject;

    @BeforeEach
    public void setup() {
        subject = new View(hangmanProvider, messagePresenter);
    }

    @Test
    void testPrintWelcomeMessage() {
        //when
        subject.printWelcomeMessage();
        //then
        verify(messagePresenter).showMessage("""
                Welcome to Hangman
                Your word will be generated shortly.
                Guess a letter, if you fail to guess correct one more then 7 times you will fail""", false);
    }

    @Test
    void testPrintMaskedWord() {
        //when
        subject.printMaskedWord("dolphin", Set.of('o', 'p'));
        //then
        verify(messagePresenter).showMessage("*o*p***", false);
    }

    @Test
    void testPrintShouldBeOneCharLong() {
        //when
        subject.printShouldBeOneCharLong();
        //then
        verify(messagePresenter).showMessage("Please provide exactly one letter: ", true);
    }

    @Test
    void testPrintShouldBeLetter() {
        //when
        subject.printShouldBeLetter();
        //then
        verify(messagePresenter).showMessage("Please provider letter [a-z]: ", true);
    }

    @Test
    void testPrintCongratulations() {
        //when
        String word = "dolphin";
        subject.printCongratulations(word);
        //then
        verify(messagePresenter)
                .showMessage("Congratulations you successfully guessed the word: " + word, false);
    }

    @Test
    void testPrintGuessLetter() {
        //when
        subject.printGuessLetter();
        //then
        verify(messagePresenter).showMessage("Guess letter: ", true);
    }

    @Test
    void testPrintMaybeNextTime() {
        //when
        String word = "dolphin";
        subject.printMaybeNextTime(word);
        //then
        verify(messagePresenter).showMessage("""
                Unfortunately you did not guess the word fast enough, maybe next time you will do it.
                The word was:\s""" + word, false);
    }

    @Test
    void testPrintGuessesLeft() {
        //when
        int guessesLeft = 5;
        subject.printGuessesLeft(guessesLeft);
        //then
        verify(messagePresenter).showMessage("You have " + guessesLeft + " guesses left.", false);
    }

    @Test
    void testPrintCorrectGuess() {
        //when
        subject.printCorrectGuess();
        //then
        verify(messagePresenter).showMessage("Well done, this is correct letter.", false);
    }

    @Test
    void testPrintIncorrectGuess() {
        //when
        subject.printIncorrectGuess();
        //then
        verify(messagePresenter).showMessage("Letter incorrect.", false);
    }

    @Test
    void testPrintShouldContinue() {
        //when
        subject.printShouldContinue();
        //then
        verify(messagePresenter).showMessage("If you want to try again type [y]: ", true);
    }

    @Test
    void testPrintHangmanStage() {
        //given
        when(hangmanProvider.stage(1)).thenReturn("test");
        //when
        subject.printHangmanStage(1);
        //then
        verify(messagePresenter).showGraphic("test");
    }
}