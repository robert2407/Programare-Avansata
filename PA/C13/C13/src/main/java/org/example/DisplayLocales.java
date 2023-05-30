package org.example;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    public static void displayLocales(ResourceBundle messages) {
        Locale[] availableLocales = Locale.getAvailableLocales();

        System.out.println(messages.getString("locales"));
        for (Locale locale : availableLocales) {
            System.out.println(locale.toString());
        }
    }
}
