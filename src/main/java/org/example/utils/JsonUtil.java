package org.example.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;

public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Integer> parseJsonArray(String json, String arrayName) {
        try {
            if (json == null || json.isEmpty()) {
                return List.of();
            }
            var node = objectMapper.readTree(json);
            var arrayNode = node.get(arrayName);
            if (arrayNode == null || !arrayNode.isArray()) {
                return List.of();
            }
            return objectMapper.convertValue(arrayNode, objectMapper.getTypeFactory().constructCollectionType(List.class, Integer.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON array", e);
        }
    }

    public static String toJsonArray(String arrayName, List<Integer> values) {
        try {
            var node = objectMapper.createObjectNode();
            var arrayNode = node.putArray(arrayName);
            values.forEach(arrayNode::add);
            return objectMapper.writeValueAsString(node);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to create JSON array", e);
        }
    }
} 