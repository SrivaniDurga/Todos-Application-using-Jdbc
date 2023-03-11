
// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.todo.repository.TodoRepository;
import com.example.todo.model.Todo;
import com.example.todo.model.TodoRowMapper;
@Service 
public class TodoH2Service implements TodoRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Todo> getTodo(){
        List<Todo> todosall = db.query("select * from TodoList" , new TodoRowMapper());
        ArrayList<Todo> todo = new ArrayList<>(todosall);
        return todo;
    }
    @Override
    public Todo getTodoById(int id){
        try{
            Todo todo = db.queryForObject("select * from TodoList where id = ?", new TodoRowMapper(),id);
            return todo;
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public Todo addTodo(Todo todo){
        String sql = "INSERT INTO TodoList (todo, priority , status) VALUES (?, ? , ?)";  
        db.update(sql, todo.getTodo(), todo.getPriority() , todo.getStatus());
        Todo person = db.queryForObject("select * from TodoList where todo = ? and priority = ? and status = ?",new TodoRowMapper(),todo.getTodo(),todo.getPriority(),todo.getStatus());
        return person;
        
    }
    @Override
    public Todo updateTodo(int id , Todo todo){
        if(todo.getTodo()!=null){
            db.update("update TodoList set todo = ? where id = ?",todo.getTodo() , id);
        }
        if(todo.getPriority()!=null){
            db.update("update TodoList set priority = ? where id = ?",todo.getPriority() , id);
        }
        if(todo.getStatus()!=null){
            db.update("update TodoList set status = ? where id = ?",todo.getStatus() , id);
        }
        return getTodoById(id);
    }
    @Override
    public void deleteTodo(int id){
        db.update("delete from TodoList where id = ?",id);
    }

}