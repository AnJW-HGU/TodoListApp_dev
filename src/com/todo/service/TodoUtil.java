//package com.todo.service;
//
//import java.io.Writer;
//import java.io.FileWriter;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.*;
//
//import com.todo.dao.TodoItem;
//import com.todo.dao.TodoList;
//
//public class TodoUtil {
//	
//	public static void createItem(TodoList list) {
//		
//		String cate, title, desc, due;
//		Scanner sc = new Scanner(System.in);
//		Scanner sd = new Scanner(System.in);
//		
//		System.out.println("[할 일 추가]");
//		
//		System.out.print("카테고리 > ");
//		cate = sc.next().trim();
//		
//		System.out.print("제목 > ");
//		title = sc.next().trim();
//		if (list.isDuplicate(title)) {
//			System.out.println("- 동일한 제목의 일이 있습니다 !");
//			return;
//		}
//		
//		System.out.print("내용 > ");
//		desc = sd.nextLine().trim();
//		
//		System.out.print("마감일자 > ");
//		due = sc.next().trim();
//		
//		TodoItem t = new TodoItem(cate, title, desc, due);
//		list.addItem(t);
//	}
//
//	public static void deleteItem(TodoList l) {
//		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("[할 일 삭제]");
//		
//		System.out.print("삭제할 일의 번호 > ");
//		int num = sc.nextInt();
//		
//		if (num < 1 || num > l.getList().size()) {
//			System.out.println("해당 항목이 존재하지 않습니다 !");
//		}
//		else {
//			TodoItem item = l.getList().get(num-1);
//			System.out.printf(num+". [%s] %s - %s - %s - %s\n", 
//					item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
//			
//			System.out.println("위 일을 삭제하시겠습니까? (y/n)");
//			String answer = sc.next();
//			if (answer.equals("y")) {
//				l.deleteItem(item);
//				System.out.println("삭제되었습니다. ");
//			} else {
//				System.out.println("취소되었습니다. ");
//			}
//		}
//		
//	}
//
//
//	public static void updateItem(TodoList l) {
//		
//		Scanner sc = new Scanner(System.in);
//		Scanner sd = new Scanner(System.in);
//		
//		System.out.println("[할 일 수정]");
//		
//		System.out.print("수정할 일의 번호 > ");
//		int num = sc.nextInt();
//		
//		if (num < 1 || num > l.getList().size()) {
//			System.out.println("해당 항목이 존재하지 않습니다 !");
//		}
//		else {
//			TodoItem item = l.getList().get(num-1);
//			System.out.printf(num+". [%s] %s - %s - %s - %s\n", 
//					item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
//			l.deleteItem(item);
//			
//			
//			System.out.print("새로운 일의 카테고리 > ");
//			String new_cate = sc.next().trim();
//	
//			System.out.print("새로운 일의 제목 > ");
//			String new_title = sc.next().trim();
//			if (l.isDuplicate(new_title)) {
//				System.out.println("- 동일한 제목의 일이 있습니다 !");
//				return;
//			}
//			
//			System.out.print("새로운 일의 내용 > ");
//			String new_desc = sd.nextLine().trim();
//			
//			System.out.print("마감일자 > ");
//			String new_due = sc.next().trim();
//			
//			TodoItem t = new TodoItem(new_cate, new_title, new_desc, new_due);
//			l.addItem(t);
//			System.out.println("수정되었습니다.");
//		}
//		
//
//	}
//	
//	public static void find(TodoList l, String key) {
//		int num = 1;
//		int result_num = 0;
//		for (TodoItem item : l.getList()) {
//			if (item.getTitle().contains(key) || item.getDesc().contains(key)) {
//				System.out.printf(num+". [%s] %s - %s - %s - %s\n", 
//						item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
//				result_num++;
//			}
//			num++;
//		}
//		System.out.println("총 "+result_num+"개의 일을 찾았습니다.");
//	}
//	
//	public static void findCate(TodoList l, String key) {
//		int num = 1;
//		int result_num = 0;
//		for (TodoItem item : l.getList()) {
//			if (item.getCategory().contains(key)) {
//				System.out.printf(num+". [%s] %s - %s - %s - %s\n", 
//						item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
//				result_num++;
//			}
//			num++;
//		}
//		System.out.println("총 "+result_num+"개의 일을 찾았습니다.");
//	}
//
//	public static void listAll(TodoList l) {
//		System.out.println("[할 일 목록, 총 "+l.getList().size()+"개]");
//		int num = 1;
//		for (TodoItem item : l.getList()) {
//			System.out.printf(num+". [%s] %s - %s - %s - %s\n", 
//					item.getCategory(), item.getTitle(), item.getDesc(), item.getDue_date(), item.getCurrent_date());
//			num++;
//		}
//	}
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
//}
