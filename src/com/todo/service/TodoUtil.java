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
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.print("[할 일 추가]"
				+ "\n제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.println("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sd.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[할 일 삭제]"
				+ "\n삭제할 일의 제목 > ");
		
		String title = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.print("[할 일 수정]"
				+ "\n수정할 일의 제목 > ");
		
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("- 해당 제목의 일이 존재하지 않습니다 !");
			return;
		}

		System.out.print("새로운 일의 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.print("새로운 일의 내용 > ");
		String new_description = sd.nextLine().trim();
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
		System.out.println("[할 일 목록]");
		for (TodoItem item : l.getList()) {
			System.out.printf("[%s] %s - %s\n", item.getTitle(), item.getDesc(), item.getCurrent_date());
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			
			System.out.println("모든 데이터가 저장되었습니다.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String todoLine;
			while((todoLine = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(todoLine, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				
				TodoItem t = new TodoItem(title, desc, date);
				l.addItem(t);
			}
			br.close();
			System.out.println("할 일 정보 로딩 완료 !");
		} catch (FileNotFoundException e) {
			System.out.println(filename+" 파일이 없습니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
