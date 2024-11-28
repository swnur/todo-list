package com.swnur.spring.todolist.util;

import com.swnur.spring.todolist.model.PublicHoliday;

import java.time.LocalDate;
import java.util.List;

public class DateUtils {

    private DateUtils() {}

    public static boolean isTaskCreationDateHoliday(LocalDate creationDate, List<PublicHoliday> list) {
        return list.stream().anyMatch(publicHoliday -> publicHoliday.getDate() == creationDate);
    }

    public static LocalDate findClosestAvailableDate(LocalDate creationDate, List<PublicHoliday> list) {
        return list.stream()
                .filter(publicHoliday -> publicHoliday.getDate().isEqual(creationDate))
                .findFirst()
                .map(publicHoliday -> list.get(list.indexOf(publicHoliday) + 1).getDate())
                .orElseThrow(() -> new IllegalStateException("No available date found after the given creation date."));
    }
}
