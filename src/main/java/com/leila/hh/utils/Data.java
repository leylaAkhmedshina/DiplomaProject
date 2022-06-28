package com.leila.hh.utils;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class Data {

    public static final String EMPTY_STRING_VALUE = "";

    private final List<String> employments = List.of(
            "Юрист",
            "Адвокат",
            "Менеджер проектов",
            "Руководитель отдела маркетинга",
            "Веб-дизайнер",
            "Инженер",
            "Product owner",
            "Экономист",
            "Администратор"
    );
}
