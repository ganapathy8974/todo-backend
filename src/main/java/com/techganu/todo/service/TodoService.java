package com.techganu.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.techganu.todo.model.Todos;
import com.techganu.todo.repository.TodosRepository;

@Service
public class TodoService{
	
	@Autowired
	TodosRepository todosRepository;
	
	public List<Todos> findByUsername(String username){
		return todosRepository.findByUsername(username);
	}
	
	public boolean deleteByid(long id) {		
		try {
			todosRepository.deleteById(id);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	public Todos findByid(Long id) {
		Todos todo = todosRepository.findById(id).get();
			if(todo.getId() == id) {
				return todo;
			}		
		return null;
	}
	
	public Todos save(Todos todo) {
		if(todo.getId()==-1 || todo.getId()==0) {			
			//TodoHardCordedService.todo.add(todo);
			todosRepository.save(todo);
		} else {
			//deleteByid(todo.getId());
			//TodoHardCordedService.todo.add(todo);
			todosRepository.save(todo);
		}
		return todo;
	}
}
