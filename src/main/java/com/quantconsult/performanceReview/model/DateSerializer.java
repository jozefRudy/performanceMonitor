package com.quantconsult.performanceReview.model;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import javax.persistence.criteria.CriteriaBuilder;

public class DateSerializer extends TypeAdapter<Date> {

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if(value != null) out.value(value.getTime());
        else out.nullValue();
    }

    /*@Override
    public void write(JsonWriter out, Date value) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        Instant instant = value.toInstant();
        out.value(formatter.format(instant.atZone(ZoneId.of("US/Eastern"))));
    }*/

    @Override
    public Date read(JsonReader in) throws IOException {
        return new Date(in.nextLong());
    }

}

