package com.ing.pt.assignment.view;

public class ConsolePresenter implements MessagePresenter {

    @Override
    public void showMessage(String message, boolean expectInput) {
        if (expectInput) {
            System.out.print(message);
        } else {
            System.out.println(message);
        }
    }
}
