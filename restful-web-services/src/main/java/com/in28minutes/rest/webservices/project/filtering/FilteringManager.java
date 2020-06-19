package com.in28minutes.rest.webservices.project.filtering;

import org.springframework.http.converter.json.MappingJacksonValue;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class FilteringManager {

	public MappingJacksonValue getFilterFor(String field1, String field2, String filterName, Object JsonValue) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field1, field2);

		FilterProvider filters = new SimpleFilterProvider().addFilter(filterName, filter);

		MappingJacksonValue mapping = new MappingJacksonValue(JsonValue);
		mapping.setFilters(filters);

		return mapping;
	}
}
