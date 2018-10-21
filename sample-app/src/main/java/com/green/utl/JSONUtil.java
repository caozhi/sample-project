package com.green.utl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

public final class JSONUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    private JSONUtil() {
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

    /**
     * Check if jsonString is a valid JSON string.
     * 
     * @param jsonString
     * @return boolean. true if {@code jsonString} is a valid JSON string.
     */
    public static boolean isJson(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonParser parser = new ObjectMapper().getFactory().createParser(jsonString);
            while (parser.nextToken() != null) {
            }
            objectMapper.readTree(jsonString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Convert {@code jsonString} to {@link JsonNode}
     * 
     * @param jsonString
     * @return JsonNode
     */
    public static JsonNode toJsonNode(String jsonString) {
        try {
            if (jsonString == null) {
                jsonString = "";
            }
            return OBJECT_MAPPER.readTree(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Create a new object node which represents a blank JSON object.
     * 
     * @return {@link ObjectNode}
     */
    public static ObjectNode newObjectNode() {
        return OBJECT_MAPPER.createObjectNode();
    }

    /**
     * Create a new ArrayNode which represents a blank JSON object.
     * 
     * @return {@link ArrayNode}
     */
    public static ArrayNode newArrayNode() {
        return OBJECT_MAPPER.createArrayNode();
    }

    /**
     * Convert {@code jsonString} to an bean class instance based on
     * {@code beanClass}
     * 
     * @param jsonString
     * @param beanClass
     * @return Bean class instance.
     * @throws JsonMappingException
     * @throws JsonParseException
     * @throws IOException
     */
    public static <T> T toBean(String jsonString, Class<T> beanClass) throws JsonParseException, JsonMappingException, IOException {
        return OBJECT_MAPPER.readValue(jsonString, beanClass);
    }

    public static <T> T toBean(String jsonString, Class<T> beanClass, boolean notWhole) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, beanClass);
    }

    public static String toJsonString(Object bean) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(bean);
    }

    /**
     * Convert bean class instance {@code bean} to a JSON string
     * 
     * @param bean
     *            Convert bean class instance {@code bean} to JSON string
     * @return JSON string
     * @throws JsonSyntaxException
     */
    public static String toJsonString(Object bean, boolean notWhole) throws JsonSyntaxException {
        Gson gson = new Gson();
        return gson.toJson(bean);
    }

}
