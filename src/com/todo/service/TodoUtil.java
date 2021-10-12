package com.todo.service;

import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList l) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[할 일 추가]");
		
		System.out.print("카테고리 > ");
		category = sc.next().trim();
		
		System.out.print("제목 > ");
		title = sc.next().trim();
		if (l.isDuplicate(title)) {
			System.out.println("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sd.nextLine().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date, 0);
		if (l.addItem(t) > 0) {
			System.out.println("주가되었습니다.");
		}
		else {
			System.out.println("추가되지 않았습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 삭제]");
		
		System.out.print("삭제할 일의 번호 > ");
		int index = sc.nextInt();

		if (l.deleteItem(index) > 0) {
			System.out.println("삭제되었습니다.");
		}
		else {
			System.out.println("삭제되지 않았습니다.");
		}		
	}
	
	public static void completeItem(TodoList l, int index) {		
		System.out.println("[할 일 완료체크]");

		if (l.completeItem(index) > 0) {
			System.out.println("체크되었습니다.");
		}
		else {
			System.out.println("체크되지 않았습니다.");
		}		
	}

	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[할 일 수정]");
		
		System.out.print("수정할 일의 번호 > ");
		int index = sc.nextInt();
				
		System.out.print("새로운 일의 카테고리 > ");
		new_category = sc.next().trim();

		System.out.print("새로운 일의 제목 > ");
		new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.print("새로운 일의 내용 > ");
		new_desc = sd.nextLine().trim();
		
		System.out.print("마감일자 > ");
		new_due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date, 0);
		t.setId(index);
		if (l.updateItem(t) > 0) {
			System.out.println("수정되었습니다.");
		}
		else {
			System.out.println("수정되지 않았습니다.");
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[할 일 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 할 일, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int isCompleted) {
		System.out.printf("[전체 완료한 일, 총 %d개]\n", l.getCount(isCompleted));
		for (TodoItem item : l.getList(isCompleted)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 일을 찾았습니다.\n", count);
	}
	
	public static void findCateList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getListCategory(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 일을 찾았습니다.\n", count);
	}
	
//	
//	public static void saveList(TodoList l, String filename) {
//		try {
//			Writer w = new FileWriter(filename);
//			
//			for (TodoItem item : l.getList()) {
//				w.write(item.toSaveString());
//			}
//			w.close();
//			
//			System.out.println("모든 데이터가 저장되었습니다.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			
//			String todoLine;
//			while((todoLine = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(todoLine, "##");
//				String cate = st.nextToken();
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				String due = st.nextToken();
//				String current = st.nextToken();
//				
//				TodoItem t = new TodoItem(cate, title, desc, due, current);
//				l.addItem(t);
//			}
//			br.close();
//			System.out.println("할 일 정보 로딩 완료 !");
//		} catch (FileNotFoundException e) {
//			System.out.println(filename+" 파일이 없습니다.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//	}
}
