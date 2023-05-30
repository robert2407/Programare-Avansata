package org.example;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    public static void displayInfo(Locale locale, ResourceBundle messages) {
        String country = locale.getDisplayCountry(locale);
        String language = locale.getDisplayLanguage(locale);
        String currencyCode;
        Currency currency;

        try {
            currency = Currency.getInstance(locale);
            currencyCode = currency.getCurrencyCode();
        } catch (IllegalArgumentException e) {
            currencyCode = "Not found";
        }
        String[] weekDays = new DateFormatSymbols(locale).getWeekdays();
        String[] months = new DateFormatSymbols(locale).getMonths();

        SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG, locale);
        String today = dateFormat.format(new Date());

        System.out.println(messages.getString("info") + " " + locale.toString());
        System.out.println("Country: " + country);
        System.out.println("Language: " + language);
        System.out.println("Currency: " + currencyCode);
        System.out.println("Week Days: " + Arrays.toString(weekDays));
        System.out.println("Months: " + Arrays.toString(months));
        System.out.println("Today: " + today);
    }
}
