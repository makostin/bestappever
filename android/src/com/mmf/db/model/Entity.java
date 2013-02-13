package com.mmf.db.model;

import java.io.Serializable;

public interface Entity extends Serializable {

	public abstract void setId(Long id);

	public abstract Long getId();

}