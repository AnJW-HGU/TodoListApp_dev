package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}
	
	public void sortByCategoty() {
		Set<String> set = new HashSet<String>();
		for (TodoItem item : list) {
			set.add(item.getCategory());
		}
		Iterator it = set.iterator();
		while(it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext()) System.out.print(" / ");
		}
		System.out.println("\n총 "+set.size()+"개의 카테고리가 등록되어있습니다.");
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("[할 일 목록, 총 "+list.size()+"개]");
		int num = 1;
		for (TodoItem item : list) {
			System.out.printf(num+". [%s] %s - %s - %s - %s\n", item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
			num++;
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
