
package com.example.todo.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.todo.model.Todo;
import com.example.todo.service.TodoH2Service;
import org.springframework.beans.factory.annotation.Autowired;
@RestController
public class TodoController{
    @Autowired
    public TodoH2Service todoservice;
    @GetMapping("/todos")
    public ArrayList<Todo> getTodo(){
        return todoservice.getTodo();
    }
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable ("id") int id){
        return todoservice.getTodoById(id);
    }
    @PostMapping("/todos")
    public Todo addTodopost(@RequestBody Todo todo){
        return todoservice.addTodo(todo);
    }
    @PutMapping("/todos/{id}")
    public Todo updateTodo(@PathVariable ("id") int id , @RequestBody Todo todo){
        return todoservice.updateTodo(id , todo);
    }
    @DeleteMapping("/todos/{id}")
    public void deleteTodos(@PathVariable ("id") int id){
        todoservice.deleteTodo(id);
    }
}