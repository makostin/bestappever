package com.mmf.rest.deserializer;

import com.google.gson.*;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Schedule;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleDeserializer implements JsonDeserializer<List<Schedule>> {

    @Override
    public List<Schedule> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonArray elements = jsonElement.getAsJsonArray();
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for(JsonElement element : elements){
            JsonObject object = element.getAsJsonObject();
            Schedule schedule = new Schedule();

            schedule.setDay(object.get("dayOfWeek").getAsInt());
            schedule.setWeek(object.get("week").getAsInt());
            schedule.setClassroom(object.getAsJsonObject("classroom").get("number").getAsInt());
            schedule.setDiscipline(object.getAsJsonObject("discipline").get("name").getAsString());
            schedule.setLecturer(new Lecturer(object.getAsJsonObject("lecturer").get("id").getAsLong()));

            JsonObject timeObject = object.getAsJsonObject("time");
            schedule.setNumber(timeObject.get("number").getAsInt());
            schedule.setTime(timeObject.get("startTime").getAsString());

            scheduleList.add(schedule);

        }
        return scheduleList;
    }
}
