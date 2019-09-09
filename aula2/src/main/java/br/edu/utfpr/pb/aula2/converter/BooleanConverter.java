package br.edu.utfpr.pb.aula2.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class BooleanConverter implements AttributeConverter<Boolean, String>{

    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        return (Boolean.TRUE.equals(attribute) ? "V" : "F");
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "V".equals(dbData);
    }
    
}
