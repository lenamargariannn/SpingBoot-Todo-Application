package com.springboot.todo.controllers;

import com.springboot.todo.models.TodoItems;
import com.springboot.todo.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.time.Instant;
import java.time.ZoneId;


@Controller
public class TodoItemController {

    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @GetMapping("/")
    public ModelAndView index(){
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("todoItems",todoItemRepository.findAll());

        modelAndView.addObject("today",Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;

    }

    @PostMapping("/todo")
    public String createTodoItem( @Valid TodoItems todoItems, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            return "add-todo-item";
        }
        todoItems.setModifiedDate(Instant.now());
        todoItems.setCreatedDate(Instant.now());
        todoItemRepository.save(todoItems);

        return "redirect:/";

    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItems todoItems, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){
            todoItems.setId(id);
            return "update-todo-item";
        }
        todoItems.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItems);

        return "redirect:/";
    }


}
