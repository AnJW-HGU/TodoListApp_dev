package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.StringTokenizer;

import com.todo.service.DbConnect;

public class TodoList {
   Connection conn;
   
   public TodoList() {
      this.conn = DbConnect.getConnection();
   }
   
   public void importData(String filename) {
      try {
         BufferedReader br = new BufferedReader(new FileReader(filename));
         String line;
         String sql = "insert into list (title, memo, category, current_date, due_date)"
               + " values (?,?,?,?,?);";
         int records = 0;
         while((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, "##");
            String category = st.nextToken();
            String title = st.nextToken();
            String description = st.nextToken();
            String due_date = st.nextToken();
            String current_date = st.nextToken();
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, category);
            pstmt.setString(4, current_date);
            pstmt.setString(5, due_date);
            int count = pstmt.executeUpdate();
            if (count > 0) records++;
            pstmt.close();
         }
         System.out.println(records + "records read!!");
         br.close();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }
}