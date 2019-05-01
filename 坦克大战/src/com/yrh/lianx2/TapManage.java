package com.yrh.lianx2;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public class TapManage {
	private static final Graphics Graphics = null;
	int x=0;
	int y=0;
	int width=63;
	int height=75;
	char direction = 's';//w:上,d:右,s:下,a:左
	private int health=100;//生命值
	//提供位移方法
	public void setXY(int x,int y,JPanel tap) {
		this.x=x;
		this.y=y;
		tap.setLocation(x, y);
	}
	JPanel tapup=new Tapup();
	JPanel tapdown=new Tapdown();
	JPanel tapleft = new Tapleft();
	JPanel tapright= new Tapright();
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
		this.direction = direction;
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
		this.health = health;
		
	}
	
}
