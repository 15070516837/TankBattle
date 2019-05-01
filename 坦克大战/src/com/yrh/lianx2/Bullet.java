package com.yrh.lianx2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Bullet extends JPanel {

	/**
	 * Create the panel.
	 */
	boolean ifDiji;
	public Bullet(boolean ifDiji) {
		this.ifDiji=ifDiji;
	}
	public void paint(Graphics g) {
		super.paint(g);
		//ÉÏ±ß¾ØÐÎ
		if(ifDiji) {
			g.setColor(Color.red);
		}else {
			g.setColor(Color.green);
		}
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
}
