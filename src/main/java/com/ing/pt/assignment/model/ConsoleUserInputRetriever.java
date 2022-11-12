package com.ing.pt.assignment.model;

import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class ConsoleUserInputRetriever implements UserInputRetriever {

    private final Scanner scanner;

    @Override
    public char getLetter() throws IncorrectInputLengthException, NotALetterException {
        return Character.toLowerCase(validateLetter(scanner.next()));
    }

    private char validateLetter(String letter) throws IncorrectInputLengthException, NotALetterException {
        if (letter.length() != 1) {
            throw new IncorrectInputLengthException();
        }
        char retrievedLetter = letter.charAt(0);
        if (!Character.isLetter(retrievedLetter)) {
            throw new NotALetterException();
        }
        return retrievedLetter;
    }
}
