package com.swnur.spring.todolist.proxy;

import com.swnur.spring.todolist.model.PublicHoliday;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "publicHolidays", url = "${public-holiday.api.url}")
public interface PublicHolidayProxy {

    @GetMapping("/{year}/{countryCode}")
    List<PublicHoliday> findHolidays(
            @PathVariable Integer year,
            @PathVariable String countryCode);
}
