package com.ing.pt.assignment.model;

import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModelTest {

    @Mock
    private WordGenerator wordGenerator;
    @Mock
    private UserInputRetriever userInputRetriever;

    private Model subject;

    @BeforeEach
    public void setup() {
        subject = new Model(wordGenerator, userInputRetriever);
    }

    @Test
    void generateWordTest() {
        //given
        String expectedWord = "dolphin";
        willGenerate(expectedWord);
        //when
        String generatedWord = subject.generateWord();
        //then
        assertThat(generatedWord).isEqualTo(expectedWord);
    }

    @Test
    void guessLetterTest() throws IncorrectInputLengthException, NotALetterException {
        //given
        char expectedUserInput = 'p';
        userTypes(expectedUserInput);
        //when
        char userInput = subject.guessLetter();
        //then
        assertThat(userInput).isEqualTo(expectedUserInput);
    }

    @Test
    void testIsCorrectLetter_whenCorrectLetter() {
        //when
        boolean result = subject.isCorrectLetter('p', "port");
        //then
        assertThat(result).isTrue();
    }

    @Test
    void testIsCorrectLetter_whenInCorrectLetter() {
        //when
        boolean result = subject.isCorrectLetter('u', "port");
        //then
        assertThat(result).isFalse();
    }

    @Test
    void testShouldTryAgainTest_whenShould() throws IncorrectInputLengthException, NotALetterException {
        //given
        userTypes('y');
        //when
        boolean result = subject.shouldTryAgain();
        //then
        assertThat(result).isTrue();

    }

    @Test
    void testShouldTryAgainTest_whenShouldNot() throws IncorrectInputLengthException, NotALetterException {
        //given
        userTypes('`');
        //when
        boolean result = subject.shouldTryAgain();
        //then
        assertThat(result).isFalse();
    }

    private void userTypes(char userInput) throws IncorrectInputLengthException, NotALetterException {
        when(userInputRetriever.getLetter()).thenReturn(userInput);
    }

    private void willGenerate(String word) {
        when(wordGenerator.generateWord()).thenReturn(word);
    }
}