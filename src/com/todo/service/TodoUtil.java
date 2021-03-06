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
		
		String category, title, desc, due_date, time, place;
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
		
		System.out.print("시간 (예: 23:30) > ");
		time = sc.next().trim();
	
		System.out.print("장소 (예: 상상랩) > ");
		place = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date, time, place);
		
		if (l.addItem(t) > 0) {
			System.out.println("주가되었습니다.");
		}
		else {
			System.out.println("추가되지 않았습니다.");
		}
	}
	
	public static void createBoxItem(TodoList l) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[할 일 보관함에 추가]");
		
		System.out.print("제목 > ");
		title = sc.next().trim();
		if (l.isDuplicate(title)) {
			System.out.println("- 동일한 제목의 일이 있습니다 !");
			return;
		}
		
		System.out.print("내용 > ");
		desc = sd.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		
		if (l.addBoxItem(t) > 0) {
			System.out.println("추가되었습니다.");
		}
		else {
			System.out.println("추가되지 않았습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 삭제]");
		
		System.out.print("삭제할 일의 개수 > ");
		int count = sc.nextInt();
		System.out.print("삭제할 일의 번호 (예: 1 7 11) > ");
		for (int i=0; i<count; i++) {
			int index = sc.nextInt();
			l.addDelItem(index);
			if (l.deleteItem(index) > 0) {
				System.out.println(index + "번이 삭제되었습니다.");
			}
			else {
				System.out.println(index + "번이 삭제되지 않았습니다.");
			}	
		}	
	}
	
	public static void recreateBoxItem(TodoList l) {
		
		String category, due_date, time, place;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[보관함에 있는 일 다시 추가]");
		
		System.out.print("다시 추가할 일의 번호 > ");
		int index = sc.nextInt();
		TodoItem boxItem = l.readBoxItem(index);
		
		System.out.println("[할 일 추가]");
		
		System.out.print("카테고리 > ");
		category = sc.next().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc.next().trim();
		
		System.out.print("시간 (예: 23:30) > ");
		time = sc.next().trim();
	
		System.out.print("장소 (예: 상상랩) > ");
		place = sc.next().trim();
		
		TodoItem t = new TodoItem(category, "", "", due_date, time, place);
		t.setTitle(boxItem.getTitle());
		t.setDesc(boxItem.getDesc());
		
		if (l.addItem(t) > 0) {
			System.out.println("다시 추가되었습니다.");
			l.redeleteBoxItem(index);
		}
		else {
			System.out.println("추가되지 않았습니다.");
		}		
	}
	
	public static void recreateDelItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[삭제한 일 다시 추가]");
		
		System.out.print("다시 추가할 일의 번호 > ");
		int index = sc.nextInt();
		
		if (l.readdDelItem(index) > 0) {
			System.out.println("다시 추가되었습니다.");
			l.redeleteDelItem(index);
		}
		else {
			System.out.println("추가되지 않았습니다.");
		}		
	}

	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date, new_time, new_place;
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
		
		System.out.print("새로운 마감일자 > ");
		new_due_date = sc.next().trim();
				
		System.out.print("새로운 시간 (예: 23:30) > ");
		new_time = sc.next().trim();
	
		System.out.print("새로운 장소 (예: 상상랩) > ");
		new_place = sc.next().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date, new_time, new_place);
				
		t.setId(index);
		if (l.updateItem(t) > 0) {
			System.out.println("수정되었습니다.");
		}
		else {
			System.out.println("수정되지 않았습니다.");
		}
	}
	
	public static void updateTime(TodoList l) {
		
		String new_time;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[시간 추가 및 수정]");
		
		System.out.print("시간 수정할 일의 번호 > ");
		int index = sc.nextInt();
		
		System.out.print("새로운 시간 (예: 23:30) > ");
		new_time = sc.next().trim();
		
		TodoItem t = new TodoItem("", "", "", "", new_time, "");
		
		t.setId(index);
		if (l.updateTime(t) > 0) {
			System.out.println("추가 및 수정되었습니다.");
		}
		else {
			System.out.println("추가 및 수정되지 않았습니다.");
		}
	}
	
	public static void updatePlace(TodoList l) {
		
		String new_place;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[시간 추가 및 수정]");
		
		System.out.print("시간 수정할 일의 번호 > ");
		int index = sc.nextInt();
		
		System.out.print("새로운 장소 (예: 상상랩) > ");
		new_place = sc.next().trim();
		
		TodoItem t = new TodoItem("", "", "", "", "", new_place);
		
		t.setId(index);
		if (l.updatePlace(t) > 0) {
			System.out.println("추가 및 수정되었습니다.");
		}
		else {
			System.out.println("추가 및 수정되지 않았습니다.");
		}
	}
	
	public static void deleteTime(TodoList l) {	
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 시간 삭제]");

		System.out.print("시간 삭제할 일의 개수 > ");
		int count = sc.nextInt();
		System.out.print("시간 삭제할 일의 번호 (예: 1 7 11) > ");
		for (int i=0; i<count; i++) {
			int index = sc.nextInt();
			if (l.deleteTime(index) > 0) {
				System.out.println(index + "번이 삭제되었습니다.");
			}
			else {
				System.out.println(index + "번이 삭제되지 않았습니다.");
			}
		}	
	}
	
	public static void deletePlace(TodoList l) {		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 장소 삭제]");

		System.out.print("장소 삭제할 일의 개수 > ");
		int count = sc.nextInt();
		System.out.print("장소 삭제할 일의 번호 (예: 1 7 11) > ");
		for (int i=0; i<count; i++) {
			int index = sc.nextInt();
			if (l.deletePlace(index) > 0) {
				System.out.println(index + "번이 삭제되었습니다.");
			}
			else {
				System.out.println(index + "번이 삭제되지 않았습니다.");
			}
		}	
	}
	
	public static void completeItem(TodoList l) {		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[할 일 완료체크]");
		
		System.out.print("완료체크할 일의 개수 > ");
		int count = sc.nextInt();
		System.out.print("완료체크할 일의 번호 (예: 1 7 11) > ");
		for (int i=0; i<count; i++) {
			int index = sc.nextInt();
			if (l.completeItem(index, 1) > 0) {
				System.out.println(index + "번이 체크되었습니다.");
			}
			else {
				System.out.println(index + "번이 체크되지 않았습니다.");
			}	
		}	
	}
	
	public static void completeDelItem(TodoList l) {	
		Scanner sc = new Scanner(System.in);

		System.out.println("[할 일 완료체크 취소]");

		System.out.print("완료체크 취소할 일의 개수 > ");
		int count = sc.nextInt();
		System.out.print("완료체크 취소할 일의 번호 (예: 1 7 11) > ");
		for (int i=0; i<count; i++) {
			int index = sc.nextInt();
			if (l.completeItem(index, 0) > 0) {
				System.out.println(index + "번이 체크 취소되었습니다.");
			}
			else {
				System.out.println(index + "번이 취소되지 않았습니다.");
			}	
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
		for (TodoItem item : l.getList(isCompleted)) {
			System.out.println(item.toString());
		}
		System.out.printf("총 %d개가 있습니다.\n", l.getCount(isCompleted));
	}
	
	public static void listBoxAll(TodoList l) {
		System.out.printf("[보관함 할 일, 총 %d개]\n", l.getBoxCount());
		for (TodoItem item : l.getListBox()) {
			System.out.println(item.toBoxString());
		}
	}

	public static void listDelAll(TodoList l) {
		System.out.printf("[삭제한 할 일, 총 %d개]\n", l.getDelCount());
		for (TodoItem item : l.getListDel()) {
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
	
//	public static void importGson(TodoList l) {
//		System.out.println("[전체 목록 파일 저장]");
//		if (l.importGson() > 0) {
//			System.out.println("저장되었습니다.");
//		}
//		else {
//			System.out.println("저장되지 않았습니다.");
//		}
//	}
//	
//	public static void exportGson(TodoList l) {
//		System.out.println("[전체 목록 파일 불러오기]");
//		if (l.exportGson() > 0) {
//			System.out.println("완료되었습니다.");
//		}
//		else {
//			System.out.println("실패했습니다.");
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
}
