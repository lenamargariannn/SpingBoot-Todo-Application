package com.springboot.todo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
@Table(name = "todo_item")
public class TodoItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Description is required!")
    private String description;

    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    private Instant createdDate;

    @Getter
    @Setter
    private Instant modifiedDate;

    public TodoItems() {
    }

    public TodoItems(String description) {

        this.description = description;
        this.complete = false;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();

    }

    @Override
    public String toString() {
        return String.format("TodoItem{ id='%d', description='%s', complete='%s', createdDate='%s', modifiedDate='%s' }", id,
                description, complete, createdDate, modifiedDate);
    }

    public void setModifiedDate(Instant now) {
        this.modifiedDate = now;
    }

    public void setCreatedDate(Instant now) {
        this.createdDate = now;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
