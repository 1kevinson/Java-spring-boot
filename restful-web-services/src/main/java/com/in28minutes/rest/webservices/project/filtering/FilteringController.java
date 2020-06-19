package com.in28minutes.rest.webservices.project.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public SomeBean retrieveSomeBean() {
		return new SomeBean("val1", "val2", "val3");
	}

	@GetMapping("/filtering-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		return Arrays.asList(new SomeBean("val1", "val2", "val3"), new SomeBean("val12", "val22", "val32"),
				new SomeBean("val13", "val23", "val33"));
	}
}
