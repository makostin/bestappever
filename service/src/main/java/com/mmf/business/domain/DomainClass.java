package com.mmf.business.domain;

import java.io.Serializable;

/**
 * @author svetlana.voyteh
 * @date: 5/12/12
 */
public interface DomainClass<T extends Serializable> extends Serializable {

    /**
     * The identifier of the domain object.
     * @return identifier instance
     */
    T getId();

    /**
     * Sets identifier for the domain object
     * @param id identifier instance
     */
    void setId(T id);
}
