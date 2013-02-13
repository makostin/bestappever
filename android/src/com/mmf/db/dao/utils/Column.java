package com.mmf.db.dao.utils;

public class Column {
	private String _name;
	private String _definition;
	private int 	_version;
	
	public Column( String name, String definition, int version ) {
		_name = name;
		_definition = definition;
		_version = version;
	}
	public String name() {
		return _name;
	}
	public String definition() {
		return _definition;
	}
	public int version() {
		return _version;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_name == null) ? 0 : _name.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Column other = (Column) obj;
		if (_name == null) {
			if (other._name != null)
				return false;
		} else if (!_name.equals(other._name))
			return false;
		return true;
	}
}