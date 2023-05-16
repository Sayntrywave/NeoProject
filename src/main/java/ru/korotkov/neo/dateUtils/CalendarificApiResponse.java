package ru.korotkov.neo.dateUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record CalendarificApiResponse(@JsonProperty("holidays") List<Holiday> holidays) {
}
