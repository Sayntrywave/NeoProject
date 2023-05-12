package ru.korotkov.neo.models;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

public record VacationRequest(
        @NotNull @Range(min = 0, max = 1_000_000, message = "Salary should be between 0 and 1m") Double salary,
        @NotNull @Range(min = 1, max = 365, message = "amount of vacation days should be between 1 and 365") Integer vacationDays) {
    public long calculateVacationPay() {
        return Math.round(salary / 365 * vacationDays);
    }

}
