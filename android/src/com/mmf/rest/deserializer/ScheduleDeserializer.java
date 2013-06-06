package com.mmf.rest.deserializer;

import com.google.gson.*;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Schedule;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 19.03.13
 */
public class ScheduleDeserializer implements JsonDeserializer<List<Schedule>> {

    private List<String> weekDays = Arrays.asList("monday", "tuesday", "wednesday", "thursday", "friday", "saturday");

    @Override
    public List<Schedule> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        List<Schedule> scheduleList = new ArrayList<Schedule>();
        for(String day : weekDays){
            JsonArray daySchedule = jsonObject.getAsJsonArray(day);
            for(JsonElement element : daySchedule){
                JsonObject object = element.getAsJsonObject();
                Schedule schedule = new Schedule();

                schedule.setDay(object.get("dayOfWeek").getAsInt());
                schedule.setWeek(object.get("week").getAsInt());
                schedule.setClassroom(object.getAsJsonObject("classroom").get("number").getAsInt());
                schedule.setDiscipline(object.getAsJsonObject("discipline").get("name").getAsString());
                JsonObject group = object.getAsJsonObject("group");
                schedule.setCourse(group.get("course").getAsInt());
                schedule.setGroupNumber(group.get("number").getAsInt());
                String subgroup = group.get("subgroup").isJsonNull() ? "" : group.get("subgroup").getAsString();
                schedule.setSubGroup(subgroup);

                schedule.setLecturer(new Lecturer(object.getAsJsonObject("lecturer").get("id").getAsLong()));

                JsonObject time = object.getAsJsonObject("disciplineTime");
                schedule.setNumber(time.get("number").getAsInt());
                schedule.setTime(time.get("startTime").getAsString());

                scheduleList.add(schedule);

            }

//            for(JsonElement disciplineElement : object.getAsJsonArray("disciplines")){
//                Schedule schedule = new Schedule();
//                schedule.setDay(object.get("day").getAsInt());
//
//                JsonObject disciplineObject = disciplineElement.getAsJsonObject();
//                schedule.setWeek(disciplineObject.get("week").getAsInt());
//                schedule.setClassroom(disciplineObject.get("classroom").getAsInt());
//                schedule.setDiscipline(disciplineObject.get("name").getAsString());
//                schedule.setCourse(disciplineObject.get("course").getAsInt());
//                schedule.setGroupNumber(disciplineObject.get("group").getAsInt());
//                if (disciplineObject.get("subGroup").isJsonNull()){
//                    schedule.setSubGroup("");
//                } else {
//                    schedule.setSubGroup(disciplineObject.get("subGroup").getAsString());
//                }
//                schedule.setLecturer(new Lecturer(disciplineObject.getAsJsonObject("lecturer").get("id").getAsLong()));
//
//                JsonObject timeObject = disciplineObject.getAsJsonObject("time");
//                schedule.setNumber(timeObject.get("number").getAsInt());
//                schedule.setTime(timeObject.get("startTime").getAsString());
//
//                scheduleList.add(schedule);
//            }
        }
        return scheduleList;
    }
}
