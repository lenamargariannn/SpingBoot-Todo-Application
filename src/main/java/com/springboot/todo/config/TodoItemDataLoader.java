package com.springboot.todo.config;

import com.springboot.todo.models.TodoItems;
import com.springboot.todo.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(TodoItemDataLoader.class);

    @Autowired
    TodoItemRepository todoItemRepository;

    @Override
    public void run(String... args) throws Exception{
        loadSeedData();
    }

    private void loadSeedData() {

        if( todoItemRepository.count() == 0 ){
            TodoItems todoItem1 = new TodoItems("get the milk");
            todoItemRepository.save(todoItem1);
        }
        logger.info("Number of todoItems: "+todoItemRepository.count());
    }

}
