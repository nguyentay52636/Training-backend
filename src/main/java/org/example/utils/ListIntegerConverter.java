package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.ArrayList;

@Converter
public class ListIntegerConverter implements AttributeConverter<List<Integer>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        try {
            if (attribute == null) {
                return "[]";
            }
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty() || dbData.equals("[]")) {
                return new ArrayList<>();
            }
            // Remove all quotes and whitespace
            String cleanData = dbData.replaceAll("['\"\\s]", "");
            // Ensure the string is properly formatted as JSON
            if (!cleanData.startsWith("[")) {
                cleanData = "[" + cleanData;
            }
            if (!cleanData.endsWith("]")) {
                cleanData = cleanData + "]";
            }
            return objectMapper.readValue(cleanData, new TypeReference<List<Integer>>() {});
        } catch (JsonProcessingException e) {
            return new ArrayList<>();
        }
    }
} 