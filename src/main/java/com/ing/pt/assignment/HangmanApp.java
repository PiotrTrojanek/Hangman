package com.ing.pt.assignment;

import com.ing.pt.assignment.controller.Controller;
import com.ing.pt.assignment.model.*;
import com.ing.pt.assignment.view.ConsolePresenter;
import com.ing.pt.assignment.view.MessagePresenter;
import com.ing.pt.assignment.view.View;

import java.util.Scanner;

public class HangmanApp {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            MessagePresenter presenter = new ConsolePresenter();
            View view = new View(presenter);
            WordGenerator wordGenerator = new StaticWordGenerator();
            UserInputRetriever userInputRetriever = new ConsoleUserInputRetriever(sc);
            Model model = new Model(wordGenerator, userInputRetriever);
            new Controller(view, model).startGame();
        }
    }
}
