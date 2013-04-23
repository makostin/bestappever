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

            for(JsonElement disciplineElement : object.getAsJsonArray("disciplines")){
                Schedule schedule = new Schedule();
                schedule.setDay(object.get("day").getAsInt());

                JsonObject disciplineObject = disciplineElement.getAsJsonObject();
                schedule.setWeek(disciplineObject.get("week").getAsInt());
                schedule.setClassroom(disciplineObject.get("classroom").getAsInt());
                schedule.setDiscipline(disciplineObject.get("name").getAsString());
                schedule.setCourse(disciplineObject.get("course").getAsInt());
                schedule.setGroupNumber(disciplineObject.get("group").getAsInt());
                if (disciplineObject.get("subGroup").isJsonNull()){
                    schedule.setSubGroup("");
                } else {
                    schedule.setSubGroup(disciplineObject.get("subGroup").getAsString());
                }
                schedule.setLecturer(new Lecturer(disciplineObject.getAsJsonObject("lecturer").get("id").getAsLong()));

                JsonObject timeObject = disciplineObject.getAsJsonObject("time");
                schedule.setNumber(timeObject.get("number").getAsInt());
                schedule.setTime(timeObject.get("startTime").getAsString());

                scheduleList.add(schedule);
            }
        }
        return scheduleList;
    }
}
