package org.example.utils;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ArrayList;

@Converter
public class ListIntegerConverter implements AttributeConverter<List<Integer>, String> {
    private static final Logger logger = LoggerFactory.getLogger(ListIntegerConverter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        try {
            if (attribute == null) {
                return "[]";
            }
            String json = objectMapper.writeValueAsString(attribute);
            logger.debug("Converting to database column: {} -> {}", attribute, json);
            return json;
        } catch (JsonProcessingException e) {
            logger.error("Error converting to database column", e);
            return "[]";
        }
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        try {
            if (dbData == null || dbData.isEmpty() || dbData.equals("[]")) {
                return new ArrayList<>();
            }
            
            // Remove any whitespace
            String cleanData = dbData.trim();
            
            // If the data is not in JSON array format, wrap it
            if (!cleanData.startsWith("[")) {
                cleanData = "[" + cleanData;
            }
            if (!cleanData.endsWith("]")) {
                cleanData = cleanData + "]";
            }
            
            // Parse the JSON array
            List<Integer> result = objectMapper.readValue(cleanData, new TypeReference<List<Integer>>() {});
            logger.debug("Converting from database column: {} -> {}", dbData, result);
            return result;
        } catch (JsonProcessingException e) {
            logger.error("Error converting from database column: {}", dbData, e);
            return new ArrayList<>();
        }
    }
} 