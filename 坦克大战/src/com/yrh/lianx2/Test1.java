package com.yrh.lianx2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Test1 extends JFrame {

	private JPanel contentPane;
	JPanel tap = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 frame = new Test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//初始话坦克
		TapManage2 tapman = new TapManage2();
		tap = tapman.setDirection('s', tap);
		
		
		this.addKeyListener(new KeyAdapter() {
			@Override
			
		 	public void keyPressed(KeyEvent e) {
				//坦克的方向切换
				if (e.getKeyCode() == KeyEvent.VK_W) {
					tap = tapman.setDirection('w', tap);
					contentPane.add(tap);
				} else if (e.getKeyCode() == KeyEvent.VK_S) {
					tap = tapman.setDirection('s', tap);
					contentPane.add(tap);
				} else if (e.getKeyCode() == KeyEvent.VK_A) {
					tap = tapman.setDirection('a', tap);
					contentPane.add(tap);
				} else if (e.getKeyCode() == KeyEvent.VK_D) {
					tap = tapman.setDirection('d', tap);
					contentPane.add(tap);
				}
				//坦克位移事件
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					Point point = tap.getLocation();
					int y = ((int)point.getY())-10;
					if(y>=0) {
						tapman.setXY(((int)point.getX()),((int)point.getY())-10,tap);
					}
				}else if(e.getKeyCode() == KeyEvent.VK_DOWN){
					Point point = tap.getLocation();
					tapman.setXY(((int)point.getX()),((int)point.getY())+10,tap);
				}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					Point point = tap.getLocation();
					int x=((int)point.getX())-10;
					if(x>=0) {
						tapman.setXY(x,((int)point.getY()),tap);
					}
					
				}
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Point point = tap.getLocation();
					tapman.setXY(((int)point.getX())+10,((int)point.getY()),tap);
				}
			}
			
			
		});
		

		contentPane.add(tap);
	}
}
