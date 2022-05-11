package com.example.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.gfc.MS950_GFC;

@Converter
public class StringConverter implements AttributeConverter<String, String>{
	
	MS950_GFC m = new MS950_GFC();
	
	@Override
	public String convertToDatabaseColumn(String value) {
        return value;
    }
	
	@Override
    public String convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return value;
    }

}
