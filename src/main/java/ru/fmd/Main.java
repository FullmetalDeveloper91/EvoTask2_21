package ru.fmd;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = "";
        String substr = "";

        System.out.println("Задание 4.1");
        System.out.println("Введите строку");
        str = scan.nextLine();
        System.out.println("Введите строку");
        substr = scan.nextLine();
        System.out.printf("Подстрока '%s' встречается %d раза\n", substr, countSubstr(str, substr));

        System.out.println("\nЗадание 4.2");
        System.out.println("Введите строку");
        str = scan.nextLine();
        System.out.println(censor(str, new String[]{"бяка", "кака"}));

        System.out.println("\nЗадание 4.3");
        System.out.println(formatDataWithReplace(readDateFromConsole()));

        System.out.println("\nЗадание 4.4");
        System.out.println(formatDataWithDateClass(readDateFromConsole()));
    }

    public static int countSubstr(String str, String substr) {
        int count = 0;
        int pos = 0;

        while (str.indexOf(substr, pos) >= 0) {
            count++;
            pos = str.indexOf(substr, pos) + substr.length();
        }

        return count;
    }

    public static String censor(String originalString, String[] censorWords) {
        for (String word : censorWords) {
            originalString = originalString.replaceAll(word, "вырезано цензурой");
        }
        return originalString;
    }

    public static String readDateFromConsole() {
        Scanner scan = new Scanner(System.in);
        String date = "";

        do {
            System.out.println("Введите дату в формате 'дд.мм.гггг'");
            date = scan.nextLine();
        } while (!date.matches("[0-9]{2}.[0-9]{2}.[0-9]{4}"));

        return date;
    }

    public static String formatDataWithReplace(String date) {
        var dateArray = date.split("\\.");
        Collections.reverse(Arrays.asList(dateArray));
        return String.join("-", dateArray);
    }

    public static String formatDataWithDateClass(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date parsingDate;
        try {
            parsingDate = sdf.parse(date);
            sdf.applyPattern("yyyy-MM-dd");
            return sdf.format(parsingDate);
        } catch (ParseException e) {
            System.out.println("Ошибка преобразования даты из строки. Будет возвращена исходная дата.");
            return date;
        }
    }
}