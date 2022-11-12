package com.ing.pt.assignment.model;

import com.ing.pt.assignment.model.exception.IncorrectInputLengthException;
import com.ing.pt.assignment.model.exception.NotALetterException;

public interface UserInputRetriever {
    char getLetter() throws IncorrectInputLengthException, NotALetterException;
}
