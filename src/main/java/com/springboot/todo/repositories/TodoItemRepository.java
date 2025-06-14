package com.springboot.todo.repositories;

import com.springboot.todo.models.TodoItems;
import org.springframework.data.repository.CrudRepository;

public interface TodoItemRepository extends CrudRepository<TodoItems, Long> {



}
