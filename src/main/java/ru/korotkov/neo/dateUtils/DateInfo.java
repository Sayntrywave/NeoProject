package ru.korotkov.neo.dateUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DateInfo(@JsonProperty("iso") String isoDate) {
}
