package com.mmf.db.model;

/**
 * User: svetlana.voyteh
 * Date: 21.04.13
 */
public enum ScheduleType {

    STUDENT(1),
    LECTURER(2);

    private int id;

    ScheduleType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
