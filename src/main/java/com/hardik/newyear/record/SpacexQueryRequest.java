package com.hardik.newyear.record;

import java.util.List;
import java.util.Map;

public record SpacexQueryRequest(
        Map<String, Object> query,
        QueryOptions options
) {
    public record QueryOptions(
            int limit,
            int page,
            Map<String, Integer> sort,
            List<Object> populate // Can be Strings or nested Map for "select"
    ) {}
}