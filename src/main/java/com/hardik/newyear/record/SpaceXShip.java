package com.hardik.newyear.record;

import java.util.List;

public record SpaceXShip(
        String id,
        String name,
        String type,
        boolean active,
        String home_port,
        String image,
        List<String> roles
) {}