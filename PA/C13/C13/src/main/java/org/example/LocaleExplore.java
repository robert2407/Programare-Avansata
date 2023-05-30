package org.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static Locale currentLocale = Locale.getDefault();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", currentLocale, LocaleExplore.class.getClassLoader());

        System.out.println(messages.getString("prompt"));

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                break;
            }

            executeCommand(command, messages);
        }
    }

    private static void executeCommand(String command, ResourceBundle messages) {
        if (command.equals("locales")) {
            DisplayLocales.displayLocales(messages);
        } else if (command.startsWith("locale.set ")) {
            SetLocale.setLocale(command, messages);
        } else if (command.equals("info")) {
            Info.displayInfo(currentLocale, messages);
        } else {
            System.out.println(messages.getString("invalid"));
        }
    }
}
