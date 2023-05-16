package ru.korotkov.neo.dateUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CalendarificResponse(@JsonProperty("response") CalendarificApiResponse response) {

}
