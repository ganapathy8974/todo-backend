package com.techganu.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techganu.todo.model.Todos;

@Repository
public interface TodosRepository extends JpaRepository<Todos, Long> {
	public List<Todos> findByUsername(String username);
}
