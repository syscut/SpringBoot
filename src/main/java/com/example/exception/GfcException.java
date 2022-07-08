package com.example.exception;

import java.sql.SQLSyntaxErrorException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

import org.hibernate.exception.GenericJDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GfcException {
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss", Locale.TAIWAN);

	private StringBuilder appendNewLine(StringBuilder sb, String replaceMent) {
		return sb.replace(sb.indexOf(replaceMent), sb.indexOf(replaceMent) + replaceMent.length(),
				"\n" + replaceMent + "\n\t");
	}

	private String sqlFormatter(String sqlString) {
		StringBuilder sql = new StringBuilder(sqlString);
		LinkedList<String> replaceStr = new LinkedList<>(Arrays.asList("select ", "update ", "insert into ", "delete ",
				"from ", "set ", "values ", "where ", "and ", "order by "));
		for (String str : replaceStr) {
			if (sql.indexOf(str) > -1) {
				appendNewLine(sql, str);
			}
		}
		return sql.toString();
	}

	private String translateMsg(String msg) {
		LinkedHashMap<String, String> translateMap = new LinkedHashMap<>(
				Map.of("Wildcard matching may not be used with non-character types.", "萬用字元搜尋不能使用在非文字格式的欄位",
						"could not prepare statement", "SQL執行失敗",
						"A syntax error has occurred.","符號錯誤"));
		if (translateMap.containsKey(msg)) {
			return translateMap.get(msg);
		} else {
			return msg;
		}
	}

	@ExceptionHandler({ GenericJDBCException.class })
	public ResponseEntity<Object> genericJDBCException(GenericJDBCException e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("以下錯誤訊息請提供給資訊：" + "\nError Code:" + e.getErrorCode() + "\nSQL:" + sqlFormatter(e.getSQL()) + "\n"
						+ "SQL Error Message:" + translateMsg(e.getSQLException().getMessage()) + "\nError Message:"
						+ translateMsg(e.getMessage()) + "\nTime:" + LocalDateTime.now().format(dateTimeFormatter));
	}

	@ExceptionHandler({ SQLSyntaxErrorException.class })
	public ResponseEntity<Object> sqlSyntaxErrorException(SQLSyntaxErrorException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("以下錯誤訊息請提供給資訊：" + "\nError Code:" + e.getErrorCode() + "\nSQL State:" + e.getSQLState()
						+ "\nError Message:" + translateMsg(e.getMessage())
						+ "\nTime:" + LocalDateTime.now().format(dateTimeFormatter));
	}

}
