package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 추가]"
				+ "\n제목 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.println("내용 > ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 삭제]"
				+ "\n삭제할 일의 제목 >");
		
		String title = sc.next();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 수정]"
				+ "\n수정할 일의 제목 >");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("- 해당 제목의 일이 존재하지 않습니다 !");
			return;
		}

		System.out.println("새로운 일의 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.println("새로운 일의 내용 > ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println("Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc());
		}
	}
}
