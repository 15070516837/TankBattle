package com.yrh.lianx2;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JPanel;

public class TapManage2 {
	private static final Graphics Graphics = null;
	char direction = 'w';//w:上,d:右,s:下,a:左
	Random ran = new Random();
	
	int xran = ran.nextInt(1100-63);
	int x=xran;
	int yran=ran.nextInt(700-75);
	int y=yran;
	int width=63;
	int height=75;
	private int health=100;//生命值
	
	
	//提供位移方法
	public void setXY(int x,int y,JPanel tap) {
		this.x=x;
		this.y=y;
		tap.setLocation(x, y);
	}
	
	JPanel tapup=new Tapup2();
	JPanel tapdown=new Tapdown2();
	JPanel tapleft = new Tapleft2();
	JPanel tapright= new Tapright2();
	public JPanel initTap() {
		JPanel panel = null;
		if(direction=='w') {
			panel = tapup;
		}else {
			panel = tapdown;
		}
		panel.setBounds(x, y, width, height);
		panel.setVisible(true);
		return panel;
	}
	
	//修改JPanel并返回
	public JPanel setDirection(char direction,JPanel tap ) {
		this.direction=direction;
		if(direction=='w'||direction=='s') {
			tap.setSize(width, height);
			tap.setVisible(false);
			JPanel panel = null;
			if(direction=='w') {
				panel = tapup;
			}else {
				panel = tapdown;
			}
			panel.setBounds(x, y, width, height);
			panel.setVisible(true);
			return panel;
		}else if(direction=='a'||direction=='d') {
			tap.setSize(height, width);
			tap.setVisible(false);
			JPanel panel = null;
			if(direction=='a') {
				panel = tapleft;
			}else {
				panel = tapright;
			}
			panel.setBounds(x, y, height, width);
			panel.setVisible(true);
			return panel;
		}
		return null;
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health -= health;
		
	}
	
	
	
	
}
