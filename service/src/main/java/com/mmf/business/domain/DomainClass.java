package com.mmf.business.domain;

import java.io.Serializable;

/**
 * Base interface for all domain objects. Declares that:
 * <ul>
 *  <li>domain object should implement java.io.Serializable</li>
 *  <li>each domain object should have id property</li>
 * </ul>
 *
 * svetlana.voyteh
 * 05.03.13
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
