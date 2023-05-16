package ru.korotkov.neo.dateUtils;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

public class HolidayService {

    private static final String apiKey = "8e526b2a58ab2ed69dcf1fae54004ce6e9a5215f";

    public static boolean isHoliday(LocalDate date) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://calendarific.com/api/v2/holidays";
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("api_key", apiKey)
                .queryParam("country", "RU")
                .queryParam("year", year)
                .queryParam("month", month)
                .queryParam("day", day);

        try {
            ResponseEntity<CalendarificResponse> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, CalendarificResponse.class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                CalendarificResponse response = responseEntity.getBody();
                if (response != null && response.response() != null && response.response().holidays() != null) {
                    List<Holiday> holidays = response.response().holidays();
                    return holidays.stream().anyMatch(holiday -> holiday.dateInfo().isoDate().equals(date.toString()));
                }
            }
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }

        return false;
    }
}