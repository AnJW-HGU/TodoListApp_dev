package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
   public static void start()
   {
//	  Scanner sc = new Scanner(System.in);
//	  TodoList l = new TodoList();
//	  l.importData("todolist.txt");
	   
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean quit = false;
		String key;
		int index;
		
		Menu.displaymenu();
		do {
			Menu.prompt();
			String choice = sc.next();
			switch (choice) {
		
			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
			
			case "comp":
				index = sc.nextInt();
				TodoUtil.completeItem(l, index);
				break;
				
			case "time":
				TodoUtil.updateTime(l);
				break;
				
			case "place":
				TodoUtil.updatePlace(l);
				break;
				
			case "del_time":
				index = sc.nextInt();
				TodoUtil.deleteTime(l, index);
				break;
				
			case "del_place":
				index = sc.nextInt();
				TodoUtil.deletePlace(l, index);
				break;
				
			case "del_comp":
				index = sc.nextInt();
				TodoUtil.completeDelItem(l, index);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l, 1);
				break;
				
			case "ls_uncomp":
				TodoUtil.listAll(l, 0);
				break;
		
			case "ls_name":
				System.out.println("��������� ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "title", 1);
				break;
		
			case "ls_name_desc":
				System.out.println("���񿪼����� ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("��¥������ ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("��¥�������� ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
			
			case "find":
				key = sc.next().trim();
				TodoUtil.findList(l, key);
				break;
			
			case "find_cate":
				key = sc.next();
				TodoUtil.findCateList(l, key);
				break;
		
			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
		
			default:
				System.out.println("��Ȯ�� ��ɾ �Է����ּ���. (��ɾ� �ٽ� ���� - help)");
				break;
			}
		} while (!quit);
   }
}