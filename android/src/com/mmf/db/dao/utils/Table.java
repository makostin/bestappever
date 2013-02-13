package com.mmf.db.dao.utils;

import java.util.ArrayList;
import java.util.List;

public class Table {
	String _name;
    String[] 			_columnsNames;
	ArrayList< Column > _columns = new ArrayList< Column >();
	int 				_inDbSinceVersion;
	
	public Table( String name, int inDbSinceVersion ) {
		_name = name;
		_inDbSinceVersion = inDbSinceVersion;
	}
	
	public int getInDbSinceVersion() {
		return _inDbSinceVersion;
	}
	
	public Table addColumn( Column column ) {
		if( column == null ) {
			throw new IllegalArgumentException();
		}
		if( _columns.indexOf( column ) != -1 ) {
			throw new IllegalArgumentException( String.format("Collumn '%s' was added before.", column.name()));
		}
		
		_columns.add( column );

		return this;
	}
	
	public List<Column> getColumns() {
		return _columns;
	}
	
	public List<Column> getColumns( int minVersion, int maxVersion ){
		ArrayList< Column > versioned = new ArrayList< Column >();
		for( Column column : _columns ) {
			if( column.version() >= minVersion && column.version() <= maxVersion ) {
				versioned.add( column );
			}
		}
		return versioned;
	}
    
    public String[] getColumnsNames() {
    	if( _columnsNames == null ) {
	    	String[] columnsNames = new String[ _columns.size() ];
	    	
	    	for( int i=0; i < _columns.size(); i++ ) {
	    		Column column = _columns.get( i );
	    		columnsNames[ i ] = column.name();
	    	}
	    	_columnsNames = columnsNames;
    	}
    	return _columnsNames;
    }

	public String getName() {
		return _name;
	}
    
    public String getCreationScript() {
    	StringBuilder script = new StringBuilder( "create table " )
    		.append( getName() )
    		.append( "(" );
    	
    	List<Column> columns = getColumns();
    	
    	for( int i=0; i < columns.size(); i++ ) {
    		Column column = columns.get( i );
    		if( i > 0 ) {
    	    	script.append( "," );
    		}
	    	script.append( column.name() )
	    		.append( ' ' )
	    		.append( column.definition() );
    	}
    	
    	script.append( ");" );
    	return script.toString();
    }
}
