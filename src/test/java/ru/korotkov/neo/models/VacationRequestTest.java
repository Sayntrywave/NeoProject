package ru.korotkov.neo.models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class VacationRequestTest {
    @Test
    void calculateVacationPayTest() {
        List<VacationRequest> vacationRequests = new ArrayList<>(
                List.of(
                        new VacationRequest(365.0, 20),
                        new VacationRequest(366.0, 20),
                        new VacationRequest(1230.0, 32),
                        new VacationRequest(23233.0, 123),
                        new VacationRequest(12345.0, 100)
                )
        );
        assertAll(() -> assertEquals(20, vacationRequests.get(0).calculateVacationPay()),
                () -> assertEquals(20, vacationRequests.get(1).calculateVacationPay()),
                () -> assertEquals(108, vacationRequests.get(2).calculateVacationPay()),
                () -> assertEquals(7829, vacationRequests.get(3).calculateVacationPay()),
                () -> assertEquals(3382, vacationRequests.get(4).calculateVacationPay()));
    }
}
