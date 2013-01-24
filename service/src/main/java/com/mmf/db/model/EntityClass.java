package com.mmf.db.model;

import java.io.Serializable;

/**
 * @author svetlana.voyteh
 * @date: 5/11/12
 */
public interface EntityClass <T extends Serializable> extends Serializable {

     /**
     * The identifier of the entity.
     *
     * @return identifier instance
     */
    T getId();

    /**
     * Sets identifier for the entity.
     *
     * @param id identifier instance
     */
    void setId(T id);
}
