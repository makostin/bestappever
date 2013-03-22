package com.mmf.rest.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.mmf.db.model.Schedule;

import java.lang.reflect.Type;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleDeserializer implements JsonDeserializer<Schedule> {

    @Override
    public Schedule deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
