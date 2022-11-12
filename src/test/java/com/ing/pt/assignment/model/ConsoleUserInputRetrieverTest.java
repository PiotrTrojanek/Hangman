package com.ing.pt.assignment.model;

import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class ConsoleUserInputRetrieverTest {

    private Scanner scanner;
    private ConsoleUserInputRetriever subject;

    @Test
    void testGetLetter_whenLetterReceived() throws IncorrectInputLengthException, NotALetterException {
        //given
        userTypes("p");
        //when
        char userInput = subject.getLetter();
        //then
        assertThat(userInput).isEqualTo('p');
    }

    @Test
    void testGetLetter_whenNotLetter() {
        //given
        userTypes("_");
        //when
        ThrowableAssert.ThrowingCallable throwableResult = () -> subject.getLetter();
        //then
        assertThatThrownBy(throwableResult).isInstanceOf(NotALetterException.class);
    }

    @Test
    void testGetLetter_whenLetterCorrect() {
        //given
        userTypes("test");
        //when
        ThrowableAssert.ThrowingCallable throwableResult = () -> subject.getLetter();
        //then
        assertThatThrownBy(throwableResult).isInstanceOf(IncorrectInputLengthException.class);
    }

    private void userTypes(String userInput) {
        scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes(StandardCharsets.UTF_8)));
        subject = new ConsoleUserInputRetriever(scanner);
    }
}