package com.hardik.newyear.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SpaceXRocket(
        String id,
        String name,
        boolean active,
        @JsonProperty("cost_per_launch") long costPerLaunch,
        String description
) {}
