package com.ing.pt.assignment.controller;

import com.ing.pt.assignment.model.Model;
import com.ing.pt.assignment.model.UserInputRetriever;
import com.ing.pt.assignment.model.WordGenerator;
import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;
import com.ing.pt.assignment.view.ConsolePresenter;
import com.ing.pt.assignment.view.HangmanProvider;
import com.ing.pt.assignment.view.View;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ControllerTest {

    private Controller subject;

    @Test
    void testStartGame_whenOnlyCorrectLetters() throws IncorrectInputLengthException, NotALetterException {
        //given
        wordIs("dolphin").andUserTypesConsecutively('o', 'p', 'i', 'n', 'd', 'l', 'h').build();

        //when
        boolean result = subject.startGame();

        //then
        assertThat(result).isTrue();
    }

    @Test
    void testStartGame_whenOnlyInCorrectLetters() throws IncorrectInputLengthException, NotALetterException {
        //given
        wordIs("dolphin").andUserTypesConsecutively('z', 'x', 'c', 'v', 'b', 'm', 'a').build();

        //when
        boolean result = subject.startGame();

        //then
        assertThat(result).isFalse();
    }

    @Test
    void testStartGame_whenMixedLetters() throws IncorrectInputLengthException, NotALetterException {
        //given
        wordIs("dolphin").andUserTypesConsecutively('z', 'x', 'c', 'v', 'b', 'm', 'o', 'p', 'i', 'n', 'd', 'l', 'h').build();

        //when
        boolean result = subject.startGame();

        //then
        assertThat(result).isTrue();
    }

    @Test
    void testStartGame_whenNonLetters() throws IncorrectInputLengthException, NotALetterException {
        //given
        wordIs("dolphin").andUserTypesConsecutively(',', '.', ';', '[', ']', '|', 'o', 'p', 'i', 'n', 'd', 'l', 'h').build();

        //when
        boolean result = subject.startGame();

        //then
        assertThat(result).isTrue();
    }

    public SetupBuilder wordIs(String word) {
        return new SetupBuilder(word);
    }

    private class SetupBuilder {
        private String word;
        private char[] userInput;

        public SetupBuilder(String word) {
            this.word = word;
        }

        public SetupBuilder andUserTypesConsecutively(char... userInput) {
            this.userInput = userInput;
            return this;
        }

        public void build() throws IncorrectInputLengthException, NotALetterException {
            View view = new View(mock(HangmanProvider.class), mock(ConsolePresenter.class));
            WordGenerator wordGenerator = mock(WordGenerator.class);
            when(wordGenerator.generateWord()).thenReturn(word);
            UserInputRetriever userInputRetriever = mock(UserInputRetriever.class);
            OngoingStubbing<Character> stub = when(userInputRetriever.getLetter());
            for (char value : userInput) {
                stub = stub.thenReturn(value);
            }
            Model model = new Model(wordGenerator, userInputRetriever);
            subject = new Controller(view, model);
        }
    }
}