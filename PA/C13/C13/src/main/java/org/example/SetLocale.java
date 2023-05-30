package org.example;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    public static void setLocale(String command, ResourceBundle messages) {
        String[] bucati = command.split(" ");
        if (bucati.length != 2) {
            System.out.println(messages.getString("invalid"));
            return;
        }

        String languageTag = bucati[1];
        Locale newLocale = Locale.forLanguageTag(languageTag);
        if (!newLocale.getLanguage().equals("")) {
            LocaleExplore.currentLocale = newLocale;
            System.out.println(messages.getString("locale.set") + " " + newLocale);
        } else {
            System.out.println(messages.getString("invalid"));
        }
    }
}
