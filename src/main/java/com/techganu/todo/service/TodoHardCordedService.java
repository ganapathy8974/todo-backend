package com.techganu.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techganu.todo.model.Todos;

@Service
public class TodoHardCordedService {
	private static List<Todos> todo = new ArrayList<Todos>();
	private static long idCounter = 0;
	static {
		todo.add(new Todos(++idCounter,"ganu","Want to have fun", new Date(), true));
		todo.add(new Todos(++idCounter,"ganu","Want to have gun", new Date(), true));
		todo.add(new Todos(++idCounter,"ganu","Want to have cool", new Date(), false));
	}
	
	public List<Todos> findAll(){
		return TodoHardCordedService.todo;
	}
	
	public Todos deleteByid(long id) {
		Todos todo = findByid(id);
		
		if(TodoHardCordedService.todo.remove(todo)) {
			return todo;
		}
		return null;
	}

	public Todos findByid(long id) {
		for(Todos todo : TodoHardCordedService.todo) {
			if(todo.getId() == id) {
				return todo;
			}			
		}
		return null;
	}
	
	public Todos save(Todos todo) {
		if(todo.getId()==-1 || todo.getId()==0) {
			todo.setId(++idCounter);
			TodoHardCordedService.todo.add(todo);
		} else {
			deleteByid(todo.getId());
			TodoHardCordedService.todo.add(todo);
		}
		return todo;
	}
}
