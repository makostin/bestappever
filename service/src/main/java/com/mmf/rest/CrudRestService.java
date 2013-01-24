package com.mmf.rest;

import com.mmf.db.model.EntityClass;

import javax.ws.rs.core.Response;
import java.io.Serializable;

/**
 * @author svetlana.voyteh
 * @date: 5/13/12
 */
public interface CrudRestService<Identifier extends Serializable, EntityObject extends EntityClass<Identifier>> {

    Response create(EntityObject entity);
    Response update(EntityObject entity);
    Response get(Identifier id);
    Response list();
    Response delete(Identifier id);
}
