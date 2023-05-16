package ru.korotkov.neo.dateUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Holiday(@JsonProperty("date") DateInfo dateInfo) {

}
