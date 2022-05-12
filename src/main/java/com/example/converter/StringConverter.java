package com.example.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.gfc.MS950StringConverter;

@Converter
public class StringConverter implements AttributeConverter<String, String>{
	
	MS950StringConverter m = new MS950StringConverter();
	
	@Override
	public String convertToDatabaseColumn(String value) {
        return value;
    }
	
	@Override
    public String convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return m.decode(value);
    }

}
