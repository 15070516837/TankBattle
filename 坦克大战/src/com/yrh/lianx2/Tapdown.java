package com.yrh.lianx2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tapdown extends JPanel {

	/**
	 * Create the panel.
	 */
	public Tapdown() {
		this.setBackground(Color.BLACK);
	}
	public void paint(Graphics g) {
		super.paint(g);
		int x=0,y=0,width=10,height=50;
		//左边矩形
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
		//右边矩形
		g.drawRect(x+height, y, width, height);
		//中间矩形
		g.drawRect(x+width, y+height/4, height-width, height/2);
		//中间椭圆
		g.drawOval(x+width, y+height/4, height-width, height/2);
		//坦克枪口
		g.drawRect(x+width+(height-width)/2-5, y+height/4+height/4, 10, height);
		
	}
}
