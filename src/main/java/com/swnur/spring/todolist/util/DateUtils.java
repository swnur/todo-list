package com.swnur.spring.todolist.util;

import com.swnur.spring.todolist.model.PublicHoliday;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DateUtils {

    private DateUtils() {}

    public static boolean isTaskCreationDateHoliday(LocalDate creationDate, List<PublicHoliday> list) {
        return list.stream().anyMatch(publicHoliday -> publicHoliday.getDate().isEqual(creationDate));
    }

    public static LocalDate findClosestAvailableDate(LocalDate creationDate, List<PublicHoliday> holidays) {
        Set<LocalDate> holidayDates = holidays.stream()
                .map(PublicHoliday::getDate)
                .collect(Collectors.toSet());

        LocalDate availableDate = creationDate;
        while (holidayDates.contains(availableDate)) {
            availableDate = availableDate.plusDays(1);
        }

        return availableDate;
    }
}
