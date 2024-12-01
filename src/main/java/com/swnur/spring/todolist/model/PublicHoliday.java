package com.swnur.spring.todolist.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PublicHoliday {

    private LocalDate date;
    private String name;
    private String countryCode;
}
