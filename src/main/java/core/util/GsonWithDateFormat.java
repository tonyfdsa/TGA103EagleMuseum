package core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.sql.DataSource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import core.adapter.LocalDateAdapter;
import core.adapter.LocalDateTimeAdapter;
import core.adapter.LocalTimeAdapter;

public class GsonWithDateFormat {
	public static DataSource DATASOURCE;
	public static final Gson GSON = new GsonBuilder()
	.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
	.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
	.registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
	.create();
}
