package com.krugercorp.ec.micro1.Service;

import com.krugercorp.ec.micro1.bean.ToDoBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ToDoService {
    private static List<ToDoBean> todos = new ArrayList<ToDoBean>();
    private static int todoCount = 3;

    static {
        todos.add(new ToDoBean(1,"Paul", "Spring MVC", new Date(), false));
        todos.add(new ToDoBean(2,"Paul", "Struts", new Date(), false));
        todos.add(new ToDoBean(3,"Jill", "Hibernate", new Date(), false));
    }

    public List<ToDoBean> retrieveTodos(String user){
        List<ToDoBean>  filteredTodos = new ArrayList<ToDoBean>();
        for (ToDoBean todo: todos ) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
        }

    public ToDoBean addTodo(String name, String desc,
                        Date targetDate, boolean isDone) {
        ToDoBean todo = new ToDoBean(++todoCount, name, desc, targetDate,
                isDone);
        todos.add(todo);
        return todo;
    }

    public ToDoBean retrieveTodo(int id) {
        for (ToDoBean todo : todos) {
            if (todo.getId() == id)
                return todo;
        }
        return null;
    }

}
