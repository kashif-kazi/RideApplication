package com.RideApp.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;

import com.RideApp.dto.PointDto;

import org.locationtech.jts.geom.Point;
public class GeometryUtil {
	
	
	
	public static Point createPoint(PointDto pointDto) {
		
		// Cordinate for Earcth is 4326
		
		GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(),4326);
		  Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0],//longitude
	                pointDto.getCoordinates()[1]   					//latitude
	                );
		return geometryFactory.createPoint(coordinate);
		  
		
	}

}
