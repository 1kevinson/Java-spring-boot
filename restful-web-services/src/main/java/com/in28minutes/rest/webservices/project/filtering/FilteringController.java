package com.in28minutes.rest.webservices.project.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	FilteringManager fm = new FilteringManager();

	@GetMapping("/filtering")
	public MappingJacksonValue retrieveSomeBean() {

		SomeBean someBean = new SomeBean("val1", "val2", "val3");
		MappingJacksonValue mapping = fm.getFilterFor("field1", "field3", "SomeBeanFilter", someBean);

		return mapping;
	}

	@GetMapping("/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {

		List<SomeBean> list = Arrays.asList(new SomeBean("val1", "val2", "val3"),
				new SomeBean("val12", "val22", "val32"), new SomeBean("val13", "val23", "val33"));

		MappingJacksonValue mapping = fm.getFilterFor("field2", "field3", "SomeBeanFilter", list);

		return mapping;
	}
}
