package com.krugercorp.ec.micro1.controller;

import com.krugercorp.ec.micro1.Service.ToDoService;
import com.krugercorp.ec.micro1.bean.ToDoBean;
import com.krugercorp.ec.micro1.util.TodoNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @ApiOperation(
            value = "Retrieve all todos for a user by passing in his name",
            notes = "A list of matching todos is returned. Current pagination is not supported.",
            response = ToDoBean.class,
            responseContainer = "List",
            produces = "application/json")
    @GetMapping("/users/{name}/todos")
    public List<ToDoBean> retrieveTodos(@PathVariable String name){
        return toDoService.retrieveTodos(name);
    }

    @GetMapping("/user/dummy")
    public ToDoBean errorService() {
        throw new RuntimeException("Ocurrio un error");
    }


    @GetMapping(path = "/users/{name}/todos/{id}")
    public Resource<ToDoBean> retrieveTodo(@PathVariable String name,
                                           @PathVariable int id) {
        ToDoBean todo = toDoService.retrieveTodo(id);
        if (todo == null) {
            throw new TodoNotFoundException("Todo Not Found");
        }

        Resource<ToDoBean> todoResource = new Resource<ToDoBean>(todo);
        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveTodos(name));
        todoResource.add(linkTo.withRel("parent"));

        return todoResource;
    }

    @PostMapping("/users/{name}/todos")
    public ResponseEntity<?> createTodoRequest(@PathVariable String name , @Valid @RequestBody ToDoBean todo){
        ToDoBean todoNew = toDoService.addTodo(name, todo.getDesc(), todo.getTargetDate(),false);
        if(todoNew == null ){
            return ResponseEntity.noContent().build();
        }
        URI location= ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(todoNew.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
