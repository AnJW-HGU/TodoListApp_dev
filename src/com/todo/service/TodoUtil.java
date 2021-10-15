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
		
		System.out.println("[�� �� �߰�]");
		
		System.out.print("ī�װ� > ");
		category = sc.next().trim();
		
		System.out.print("���� > ");
		title = sc.next().trim();
		if (l.isDuplicate(title)) {
			System.out.println("- ������ ������ ���� �ֽ��ϴ� !");
			return;
		}
		
		System.out.print("���� > ");
		desc = sd.nextLine().trim();
		
		System.out.print("�������� > ");
		due_date = sc.next().trim();
		
		System.out.print("�ð� (��: 23:30) > ");
		time = sc.next().trim();
	
		System.out.print("��� (��: ���) > ");
		place = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date, time, place);
		
		if (l.addItem(t) > 0) {
			System.out.println("�ְ��Ǿ����ϴ�.");
		}
		else {
			System.out.println("�߰����� �ʾҽ��ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�� �� ����]");
		
		System.out.print("������ ���� ��ȣ > ");
		int index = sc.nextInt();

		if (l.deleteItem(index) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		else {
			System.out.println("�������� �ʾҽ��ϴ�.");
		}		
	}

	public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date, new_time, new_place;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[�� �� ����]");
		
		System.out.print("������ ���� ��ȣ > ");
		int index = sc.nextInt();
				
		System.out.print("���ο� ���� ī�װ� > ");
		new_category = sc.next().trim();

		System.out.print("���ο� ���� ���� > ");
		new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("- ������ ������ ���� �ֽ��ϴ� !");
			return;
		}
		
		System.out.print("���ο� ���� ���� > ");
		new_desc = sd.nextLine().trim();
		
		System.out.print("���ο� �������� > ");
		new_due_date = sc.next().trim();
				
		System.out.print("���ο� �ð� (��: 23:30) > ");
		new_time = sc.next().trim();
	
		System.out.print("���ο� ��� (��: ���) > ");
		new_place = sc.next().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date, new_time, new_place);
				
		t.setId(index);
		if (l.updateItem(t) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		else {
			System.out.println("�������� �ʾҽ��ϴ�.");
		}
	}
	
	public static void updateTime(TodoList l) {
		
		String new_time;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�ð� �߰� �� ����]");
		
		System.out.print("�ð� ������ ���� ��ȣ > ");
		int index = sc.nextInt();
		
		System.out.print("���ο� �ð� (��: 23:30) > ");
		new_time = sc.next().trim();
		
		TodoItem t = new TodoItem("", "", "", "", new_time, "");
		
		t.setId(index);
		if (l.updateTime(t) > 0) {
			System.out.println("�߰� �� �����Ǿ����ϴ�.");
		}
		else {
			System.out.println("�߰� �� �������� �ʾҽ��ϴ�.");
		}
	}
	
	public static void updatePlace(TodoList l) {
		
		String new_place;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�ð� �߰� �� ����]");
		
		System.out.print("�ð� ������ ���� ��ȣ > ");
		int index = sc.nextInt();
		
		System.out.print("���ο� ��� (��: ���) > ");
		new_place = sc.next().trim();
		
		TodoItem t = new TodoItem("", "", "", "", "", new_place);
		
		t.setId(index);
		if (l.updatePlace(t) > 0) {
			System.out.println("�߰� �� �����Ǿ����ϴ�.");
		}
		else {
			System.out.println("�߰� �� �������� �ʾҽ��ϴ�.");
		}
	}
	
	public static void deleteTime(TodoList l, int index) {		
		System.out.println("[�� �� �ð� ����]");

		if (l.deleteTime(index) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		else {
			System.out.println("�������� �ʾҽ��ϴ�.");
		}		
	}
	
	public static void deletePlace(TodoList l, int index) {		
		System.out.println("[�� �� ��� ����]");

		if (l.deletePlace(index) > 0) {
			System.out.println("�����Ǿ����ϴ�.");
		}
		else {
			System.out.println("�������� �ʾҽ��ϴ�.");
		}		
	}
	
	public static void completeItem(TodoList l, int index) {		
		System.out.println("[�� �� �Ϸ�üũ]");

		if (l.completeItem(index, 1) > 0) {
			System.out.println("üũ�Ǿ����ϴ�.");
		}
		else {
			System.out.println("üũ���� �ʾҽ��ϴ�.");
		}		
	}
	
	public static void completeDelItem(TodoList l, int index) {		
		System.out.println("[�� �� �Ϸ�üũ ���]");

		if (l.completeItem(index, 0) > 0) {
			System.out.println("üũ ��ҵǾ����ϴ�.");
		}
		else {
			System.out.println("��ҵ��� �ʾҽ��ϴ�.");
		}		
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[�� �� ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü �� ��, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int isCompleted) {
		for (TodoItem item : l.getList(isCompleted)) {
			System.out.println(item.toString());
		}
		System.out.printf("�� %d���� �ֽ��ϴ�.\n", l.getCount(isCompleted));
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� ���� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void findCateList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getListCategory(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� ���� ã�ҽ��ϴ�.\n", count);
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
//			System.out.println("��� �����Ͱ� ����Ǿ����ϴ�.");
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
//			System.out.println("�� �� ���� �ε� �Ϸ� !");
//		} catch (FileNotFoundException e) {
//			System.out.println(filename+" ������ �����ϴ�.");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
//	}
}
