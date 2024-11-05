package com.mygym.config;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class StringListNumberConverter implements AttributeConverter<List<Integer>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(List<Integer> stringList) {
        return stringList != null ? stringList.stream().map(Object::toString).collect(Collectors.joining(SPLIT_CHAR)) : "";
    }

    @Override
    public List<Integer> convertToEntityAttribute(String string) {
        return (string != null && !string.isEmpty()) ? new ArrayList<>(Arrays.stream(string.split(SPLIT_CHAR)).map(
                Integer::parseInt).collect(Collectors.toList())
        ) : new ArrayList<>();
    }
}
