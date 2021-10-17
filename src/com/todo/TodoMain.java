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
				
			case "add_box":
				TodoUtil.createBoxItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
			
			case "comp":
				TodoUtil.completeItem(l);
				break;
				
			case "time":
				TodoUtil.updateTime(l);
				break;
				
			case "place":
				TodoUtil.updatePlace(l);
				break;
				
			case "del_time":
				TodoUtil.deleteTime(l);
				break;
				
			case "del_place":
				TodoUtil.deletePlace(l);
				break;
				
			case "del_comp":
				TodoUtil.completeDelItem(l);
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
		
			case "ls_name_asc":
				System.out.println("제목순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "title", 1);
				break;
		
			case "ls_name_desc":
				System.out.println("제목역순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "title", 0);
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬되었습니다.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "ls_box":
				TodoUtil.listBoxAll(l);
				break;
				
			case "setting":
				TodoUtil.recreateBoxItem(l);
				break;
				
			case "ls_del":
				TodoUtil.listDelAll(l);
				break;
				
			case "return":
				TodoUtil.recreateDelItem(l);
				break;
			
			case "find":
				key = sc.next().trim();
				TodoUtil.findList(l, key);
				break;
			
			case "find_cate":
				key = sc.next();
				TodoUtil.findCateList(l, key);
				break;
				
//			case "import_json":
//				TodoUtil.importGson(l);
//				break;
//				
//			case "export_json":
//				TodoUtil.exportGson(l);
//				break;
		
			case "exit":
				quit = true;
				break;
			
			case "help":
				Menu.displaymenu();
				break;
		
			default:
				System.out.println("정확한 명령어를 입력해주세요. (명령어 다시 보기 - help)");
				break;
			}
		} while (!quit);
   }
}