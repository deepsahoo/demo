package io.pivotal.workshop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

	@RequestMapping("/")
	public String sayHello(){
		return "Hi All";
	}
}
