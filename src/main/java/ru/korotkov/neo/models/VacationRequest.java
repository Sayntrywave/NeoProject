package ru.korotkov.neo.models;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import ru.korotkov.neo.dateUtils.HolidayService;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record VacationRequest(
        @NotNull @Range(min = 0, max = 1_000_000, message = "Salary should be between 0 and 1m") Double salary,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstDayOfVacation,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastDayOfVacation) {


    public long calculateVacationPay() {
        long vacationDays = firstDayOfVacation.datesUntil(lastDayOfVacation.plusDays(1))
                .filter(localDate -> !(localDate.getDayOfWeek() == DayOfWeek.SATURDAY)
                        && !(localDate.getDayOfWeek() == DayOfWeek.SUNDAY)
                        && !(HolidayService.isHoliday(localDate)))
                .count();
        return Math.round(salary / 365 * vacationDays);
    }

}
