package com.yrh.lianx2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Tapleft2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Tapleft2() {
		this.setBackground(Color.BLACK);
	}
	public void paint(Graphics g) {
		super.paint(g);
		int x=0,y=0,width=50,height=10;
		x=x+width-width/2-1;
		//�ϱ߾���
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		//�±߾���
		g.fillRect(x, y+width, width, height);
		//�м����
		g.fillRect( x+width/4, y+height,width/2, width-height);
//		//�м���Բ
//		g.drawOval( x+width/4, y+height,width/2, width-height);
		//̹��ǹ��
		g.fillRect(0, y+height+(y+width-height)/2-5, width, height);
		
	}

}
