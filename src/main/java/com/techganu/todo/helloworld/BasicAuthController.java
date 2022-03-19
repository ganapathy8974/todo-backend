package com.techganu.todo.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class BasicAuthController {
	@RequestMapping(method=RequestMethod.GET, path = "/basicauth")
	public BasicAuthBeen helloWorld() {
		return new BasicAuthBeen("Vanga");
	}
	
}
