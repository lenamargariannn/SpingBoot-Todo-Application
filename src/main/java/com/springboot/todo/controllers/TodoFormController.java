package com.springboot.todo.controllers;

import com.springboot.todo.models.TodoItems;
import com.springboot.todo.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class TodoFormController {

    private final Logger loggerr = LoggerFactory.getLogger(TodoFormController.class);

    @Autowired
    private TodoItemRepository todoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateFrom(TodoItems todoItems){
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){

        TodoItems item = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found. "));

        model.addAttribute("todo", item);
        return "update-todo-item";

    }


    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model){

        TodoItems item = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found. "));

        todoItemRepository.delete(item);
        return "redirect:/";

    }

}
