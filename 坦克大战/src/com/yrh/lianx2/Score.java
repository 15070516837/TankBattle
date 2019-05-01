package com.yrh.lianx2;

import javax.swing.JPanel;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Score extends JPanel {
	public  JTextField textField;
	public JTextField textField_1;
	Object file ;

	/**
	 * Create the panel.
	 */
	public Score() {
		setLayout(null);
		this.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("\u6700\u9AD8\u5206");
		label.setBounds(0, 45, 54, 15);
		add(label);
		
		JLabel label_1 = new JLabel("\u5F53\u524D\u5206");
		label_1.setBounds(0, 125, 54, 15);
		
		add(label_1);
		//最高分
		byte[] scoreB = new byte[10];
		try {
			
			File fw = new  File("C:\\score.txt");//创建c盘文件
			 boolean newFile = fw.createNewFile();
			 if(newFile) {
				file = new  FileOutputStream(fw.getPath());
				OutputStreamWriter osw= new OutputStreamWriter((OutputStream)file,"utf-8");
				osw.write(String.valueOf("0"));
				osw.flush();
				((OutputStream)file).flush();
				 
			 }
			
			//判断C:\\score.txt是否可以读取
			if(!fw.canRead()) {
				//启用网络流
				URL url = new URL("http://localhost:8080/tap/score");
				file = url.openConnection();
				InputStream inputStream = ((URLConnection)file).getInputStream();
				while(inputStream.read(scoreB)>=0) {
					
				}
				inputStream.close();
			}else {
				
				InputStream inputStream = new FileInputStream(fw);
				while(inputStream.read(scoreB)>=0) {
					
				}
				inputStream.close();
			}
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String string = new String(scoreB);
		String string2 = string.trim();
		System.out.println(string2);
		textField = new JTextField();
		textField.setText(string2);
		textField.setEditable(false);
		textField.setBounds(0, 79, 66, 21);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("0");
		textField_1.setEditable(false);
		textField_1.setBounds(0, 150, 66, 21);
		add(textField_1);
		textField_1.setColumns(10);

	}
}
