package com.quantconsult.performanceReview.model;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import javax.persistence.criteria.CriteriaBuilder;

public class DateSerializer extends TypeAdapter<ZonedDateTime> {

    @Override
    public void write(JsonWriter out, ZonedDateTime value) throws IOException {
        if(value != null) out.value(value.toInstant().toEpochMilli());
        else out.nullValue();
    }

    /*@Override
    public void write(JsonWriter out, Date value) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        Instant instant = value.toInstant();
        out.value(formatter.format(instant.atZone(ZoneId.of("US/Eastern"))));
    }*/

    @Override
    public ZonedDateTime read(JsonReader in) throws IOException {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(in.nextLong()), ZoneId.of("America/Chicago"));
    }

}

