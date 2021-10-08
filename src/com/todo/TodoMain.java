package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;

public class TodoMain {
   public static void start()
   {
      Scanner sc = new Scanner(System.in);
      TodoList l = new TodoList();
      l.importData("todolist.txt");
   }
}