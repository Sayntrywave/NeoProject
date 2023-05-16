package ru.korotkov.neo.models;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public record VacationRequest(
        @NotNull @Range(min = 0, max = 1_000_000, message = "Salary should be between 0 and 1m") Double salary,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstDayOfVacation,
        @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastDayOfVacation) {

    public long calculateVacationPay() {
        long numberOfDaysBetween = ChronoUnit.DAYS.between(firstDayOfVacation, lastDayOfVacation);
        return Math.round(salary / 365 * numberOfDaysBetween);
    }

}
