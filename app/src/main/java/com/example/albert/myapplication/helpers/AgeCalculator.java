package com.example.albert.myapplication.helpers;

import java.util.Calendar;
import java.util.Date;

public class AgeCalculator {
    public static String getAge(Date dateOfBirth) {
        return getYears(dateOfBirth) + " г.(л.) " + getMonthes(dateOfBirth) + " м.";

    }

    private static int getYears(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        int age = 0;
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if ((birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
                (birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH))) {
            age--;
        } else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH)) &&
                (birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }

    private static int getMonthes(Date dateOfBirth) {
        Calendar today = Calendar.getInstance();
        Calendar birthDate = Calendar.getInstance();
        int months = 0;
        birthDate.setTime(dateOfBirth);
        if (birthDate.after(today)) {
            throw new IllegalArgumentException("Can't be born in the future");
        }
        months = today.get(Calendar.MONTH) - birthDate.get(Calendar.MONTH);
        return months;
    }


}
