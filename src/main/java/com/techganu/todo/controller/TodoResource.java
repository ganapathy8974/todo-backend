package com.techganu.todo.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.techganu.todo.model.Todos;
import com.techganu.todo.service.TodoService;


@RestController
@CrossOrigin(origins = "*")
public class TodoResource {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/users/{username}/todos")	
	public List<Todos> getTodos(@PathVariable String username){
		return todoService.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todo/{id}")	
	public Todos getTodo(@PathVariable Long id){
		return todoService.findByid(id);
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")	
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id){
		if(todoService.deleteByid(id) == true) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Todos> updateTodo(@PathVariable String username,@PathVariable long id, @RequestBody Todos todo){
		todo.setUsername(username);
		Todos todoUpdated = todoService.save(todo);
		return new ResponseEntity<Todos>(todo, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> updateTodo(@PathVariable String username, @RequestBody Todos todo){
		todo.setUsername(username);
		Todos createdTodo = todoService.save(todo);
		//Location
		//Get current resource url
		///{id}
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
