package com.hardik.newyear.record;

import java.util.List;

public record SpacexQueryResult<T>(
        List<T> docs,
        int totalDocs,
        int limit,
        int totalPages,
        int page,
        boolean pagingCounter,
        boolean hasPrevPage,
        boolean hasNextPage,
        Integer prevPage,
        Integer nextPage
) {}