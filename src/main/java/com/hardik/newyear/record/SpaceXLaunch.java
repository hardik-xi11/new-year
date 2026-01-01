package com.hardik.newyear.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpaceXLaunch(
        String id,
        String name,
        @JsonProperty("flight_number") int flightNumber,
        @JsonProperty("date_utc") String dateUtc,
        Object rocket,
        boolean success,
        String details,
        Links links
) {

    public record Links(String webcast, Patch patch) {}
    public record Patch(String small, String large) {}
}