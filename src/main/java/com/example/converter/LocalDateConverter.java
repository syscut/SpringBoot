package com.example.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class LocalDateConverter implements AttributeConverter<String, String>{
	
	DateTimeFormatter formatterFrom = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	DateTimeFormatter formatterTo = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@Override
	public String convertToDatabaseColumn(String value) {
        return value;
    }
	
	@Override
    public String convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        
        return String.valueOf(LocalDate.parse(value.replaceAll("/(\\d{1})(?!\\d)", "/0$1"), formatterFrom).format(formatterTo));
    }

}
