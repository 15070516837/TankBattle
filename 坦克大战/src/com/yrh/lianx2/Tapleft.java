package com.yrh.lianx2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tapleft extends JPanel {

	/**
	 * Create the panel.
	 */
	public Tapleft() {
		this.setBackground(Color.BLACK);
	}
	public void paint(Graphics g) {
		super.paint(g);
		int x=0,y=0,width=50,height=10;
		x=x+width-width/2-1;
		//上边矩形
		g.setColor(Color.red);
		g.drawRect(x, y, width, height);
		//下边矩形
		g.drawRect(x, y+width, width, height);
		//中间矩形
		g.drawRect( x+width/4, y+height,width/2, width-height);
		//中间椭圆
		g.drawOval( x+width/4, y+height,width/2, width-height);
		//坦克枪口
		g.drawRect(0, y+height+(y+width-height)/2-5, width, height);
		
	}

}
