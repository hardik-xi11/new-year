package com.hardik.newyear.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpaceXLaunch(
        String id,
        String name,
        @JsonProperty("flight_number") int flightNumber,
        @JsonProperty("date_utc") String dateUtc,
        boolean success,
        String details,
        @JsonProperty("rocket") String rocketId,
        Links links
) {
    public record Links(Patch patch, String webcast) {
    }

    public record Patch(String small, String large) {
    }
}