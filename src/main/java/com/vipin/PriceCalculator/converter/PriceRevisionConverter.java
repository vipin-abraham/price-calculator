package com.vipin.PriceCalculator.converter;

import java.io.IOException;
import java.util.List;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vipin.PriceCalculator.PriceRevision;

public class PriceRevisionConverter implements AttributeConverter<List<PriceRevision>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(final List<PriceRevision> attribute) {
        if (null == attribute) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (final JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<PriceRevision> convertToEntityAttribute(final String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return OBJECT_MAPPER.readValue(dbData, new TypeReference<List<PriceRevision>>() {
            });
        } catch (final IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
