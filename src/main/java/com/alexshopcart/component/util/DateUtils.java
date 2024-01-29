package com.alexshopcart.component.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private DateUtils() {
		throw new IllegalStateException("DateUtils class component util");
	}

	public static String formaterDate() {
		// Especificar la zona horaria de El Salvador
		ZoneId zonaElSalvador = ZoneId.of("America/El_Salvador");

		// Obtener la fecha y hora actual en la zona de El Salvador
		ZonedDateTime timestamp = ZonedDateTime.now(zonaElSalvador);

		// Crear un formateador de fecha y hora
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");

		// Formatear la fecha y hora en el formato deseado
		return timestamp.format(formatter);
	}

}
