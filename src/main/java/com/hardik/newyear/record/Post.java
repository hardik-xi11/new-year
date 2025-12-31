package com.hardik.newyear.record;

public record Post(
        Integer id,
        Integer userId,
        String title,
        String body
) {
}
