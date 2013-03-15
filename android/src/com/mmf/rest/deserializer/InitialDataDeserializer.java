package com.mmf.rest.deserializer;

import com.google.gson.*;
import com.mmf.db.model.Department;
import com.mmf.db.model.Lecturer;
import com.mmf.db.model.Specialty;
import com.mmf.rest.domain.InitialData;

import java.lang.reflect.Type;
import java.util.List;

/**
 * svetlana.voyteh
 * 14.03.13
 */
public class InitialDataDeserializer implements JsonDeserializer<InitialData> {


    @Override
    public InitialData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject data = jsonElement.getAsJsonObject();
        InitialData initialData = new InitialData();
        initialData.setCourseAmount(data.get("courseAmount").getAsInt());
        initialData.setGroupAmount(data.get("groupAmount").getAsInt());

        JsonArray array = data.getAsJsonArray("subGroups");
        for (JsonElement element : array){
            initialData.getSubGroups().add(element.getAsString());
        }

        array = data.getAsJsonArray("departments");
        for (JsonElement departmentJson : array){
            JsonObject departmentObject = departmentJson.getAsJsonObject();
            Department department = new Department();
            department.setId(departmentObject.get("id").getAsLong());
            department.setName(departmentObject.get("name").toString());
            initialData.getDepartments().add(department);

            for (JsonElement lecturerJson : departmentObject.get("lecturers").getAsJsonArray()){
                JsonObject lecturerObject = lecturerJson.getAsJsonObject();
                Lecturer lecturer = new Lecturer();
                lecturer.setId(lecturerObject.get("id").getAsLong());
                StringBuilder fullName = new StringBuilder(lecturerObject.get("surname").getAsString());
                fullName.append(" ").append(lecturerObject.get("name")).append(" ").append(lecturerObject.get("patronymic"));
                lecturer.setFullName(fullName.toString().trim());
                lecturer.setDepartment(department);
                initialData.getLecturers().add(lecturer);
            }
        }

        array = data.getAsJsonArray("specialties");
        for(JsonElement specialtyJson : array){
            JsonObject specialtyObject = specialtyJson.getAsJsonObject();
            Specialty specialty = new Specialty();
            specialty.setName(specialtyObject.get("name").getAsString());

            for(JsonElement groupNumber : specialtyObject.getAsJsonArray("groupNumbers")){
                specialty.setNumber(groupNumber.getAsInt());
                initialData.getSpecialties().add(specialty);
            }

        }

        return initialData;
    }
}
