package ru.korotkov.neo.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VacationRequestTest {
    private Double salary;
    private List<LocalDate> dates;

    @BeforeEach
    public void createNewDate() {
        salary = 365.0;

        dates = new ArrayList<>();
        dates.add(LocalDate.of(2023, 3, 1));
        dates.add(LocalDate.of(2023, 3, 8));
        dates.add(LocalDate.of(2023, 3, 10));
        dates.add(LocalDate.of(2023, 3, 31));

    }

    @Test
    void calculateVacationPayTest() {
        List<VacationRequest> vacationRequests = new ArrayList<>(
                List.of(
                        new VacationRequest(salary, dates.get(0), dates.get(1)),
                        new VacationRequest(salary, dates.get(1), dates.get(2)),
                        new VacationRequest(salary, dates.get(2), dates.get(3)),
                        new VacationRequest(salary, dates.get(0), dates.get(2)),
                        new VacationRequest(salary, dates.get(1), dates.get(3))
                )
        );
        assertAll(() -> assertEquals(5, vacationRequests.get(0).calculateVacationPay()),
                () -> assertEquals(2, vacationRequests.get(1).calculateVacationPay()),
                () -> assertEquals(15, vacationRequests.get(2).calculateVacationPay()),
                () -> assertEquals(7, vacationRequests.get(3).calculateVacationPay()),
                () -> assertEquals(16, vacationRequests.get(4).calculateVacationPay()));
    }
}