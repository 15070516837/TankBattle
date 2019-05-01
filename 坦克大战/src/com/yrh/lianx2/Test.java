package com.yrh.lianx2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Canvas;
import java.awt.Button;
import java.awt.Color;

public class Test extends JFrame {

	private JPanel contentPane;
	JPanel tap ;
	JPanel tap1 ;
	TapManage2 tapman1 = null;
	TapManage tapman;
	Score score;
	boolean ZJthread=true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 1200, 700);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		//锟皆边的凤拷锟斤拷锟斤拷
		score = new Score();
		score.setBounds(1100, 0, 1000, 700);
		contentPane.add(score);
		// 锟斤拷始锟斤拷坦锟斤拷
		tapman = new TapManage();
		tap = tapman.initTap();
		
		
		

		score.textField.addKeyListener(new KeyAdapter() {
			@Override

			public void keyPressed(KeyEvent e) {
				// 坦锟剿的凤拷锟斤拷锟叫伙拷
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
				// 坦锟斤拷位锟斤拷锟铰硷拷
				if (e.getKeyCode() == KeyEvent.VK_UP) {
					Point point = tap.getLocation();
					int y = ((int) point.getY()) - 10;
					if (y >= 0) {
						tapman.setXY(((int) point.getX()), ((int) point.getY()) - 10, tap);
					}

				} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					Point point = tap.getLocation();
					int y = ((int) point.getY()) + 10;
					int height2 = tap.getHeight();
					if (y + height2 + height2 / 2 < 700) {
						tapman.setXY(((int) point.getX()), y, tap);
					}

				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Point point = tap.getLocation();
					int x = ((int) point.getX()) - 10;
					if (x >= 0) {
						tapman.setXY(x, ((int) point.getY()), tap);
					}

				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Point point = tap.getLocation();
					int x = ((int) point.getX()) + 10;
					int width2 = tap.getWidth();
					if (x + width2 + width2 / 4 <= 1100) {
						tapman.setXY(x, ((int) point.getY()), tap);
					}

				}
			}

		});

		// 锟斤拷始锟斤拷锟叫伙拷坦锟斤拷
		tapman1 = new TapManage2();
		tap1 = tapman1.initTap();
		new Thread(new DiJi()).start();
		new Thread(new Launch(true)).start();//锟斤拷锟皆硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟接碉拷锟竭筹拷

		new Thread(new Launch(false)).start();
		new Thread(new DiJidir()).start();
		contentPane.add(tap);
		contentPane.add(tap1);
	}
	
	public void initTap(boolean ifDiJi) {
		if(ifDiJi) {
			if(tap1==null||tapman1==null) {
				
				tapman1 = new TapManage2();
				tap1 = tapman1.initTap();
				System.gc();
			}
		}else {
			if(tap==null||tapman==null) {
				
				// 锟斤拷始锟斤拷锟叫伙拷坦锟斤拷
				tapman = new TapManage();
				tap = tapman.initTap();
				System.gc();
			}
		}
		
	}
	// 锟斤拷锟斤拷锟接碉拷锟竭筹拷
	
	class Launch implements Runnable {
		boolean ifDiJi;//锟叫讹拷锟角凤拷锟斤拷锟皆硷拷锟斤拷锟斤拷锟接碉拷
		public Launch(boolean ifDiJi) {
			this.ifDiJi=ifDiJi;
		}
		@Override
		public void run() {
			
			while (ZJthread) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initTap(ifDiJi);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
				Bullet bull = new Bullet(ifDiJi);
				
				char direction ;// 锟斤拷取枪锟节凤拷锟斤拷
				if(ifDiJi) {
					
					direction = tapman.direction;// 锟斤拷取枪锟节凤拷锟斤拷
					Point location = tap.getLocation();
					int x = (int) location.getX();
					int y = (int) location.getY();
					if (direction == 'w') {
						bull.setBounds(x + tap.getWidth() / 2 - 5, y, 10, 3);
					} else if (direction == 's') {
						bull.setBounds(x + tap.getWidth() / 2 - 5, y + tap.getHeight(), 10, 3);
					} else if (direction == 'a') {
						bull.setBounds(x, y + tap.getHeight() / 2 - 5, 3, 10);
					} else if (direction == 'd') {
						bull.setBounds(x + tap.getWidth(), y + tap.getHeight() / 2 - 5, 3, 10);
					}
				}else {
					initTap(ifDiJi);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
					synchronized (tapman1) {
						direction = tapman1.direction;// 锟斤拷取枪锟节凤拷锟斤拷
						Point location = tap1.getLocation();
						int x = (int) location.getX();
						int y = (int) location.getY();
						if (direction == 'w') {
							bull.setBounds(x + tap1.getWidth() / 2 - 5, y, 10, 3);
						} else if (direction == 's') {
							bull.setBounds(x + tap1.getWidth() / 2 - 5, y + tap1.getHeight(), 10, 3);
						} else if (direction == 'a') {
							bull.setBounds(x, y + tap1.getHeight() / 2 - 5, 3, 10);
						} else if (direction == 'd') {
							bull.setBounds(x + tap1.getWidth(), y + tap1.getHeight() / 2 - 5, 3, 10);
						}
					}
					
				}
			

				contentPane.add(bull);
				new Thread(new LaunchMove(bull, direction,ifDiJi)).start();

			}

		}

	}
	int num=0;
	//锟接碉拷锟狡讹拷
	class LaunchMove implements Runnable {
		JPanel panel;
		char direction;
		boolean ifDiJi = true;
		public LaunchMove(JPanel panel, char direction,boolean ifDiJi) {
			this.panel = panel;
			this.direction = direction;
			this.ifDiJi=ifDiJi;
		
		}
		public void close() {
			panel.setVisible(false);

			panel = null;
			System.gc();
		}
		
		public void ZJhit(boolean shoot,boolean ifDiJi) {
			//锟叫讹拷锟角凤拷锟斤拷械谢锟�
			if(shoot) {
				
				if(ifDiJi) {
					tapman1.setHealth(20);
					int health= tapman1.getHealth();
					if(health<=0) {
						tap1.setVisible(false);
						tap1=null;
						tapman1 = null;
						int scoreG1 = Integer.parseInt(score.textField.getText());
						int score1 = Integer.parseInt(score.textField_1.getText());
						score1+=50;
						//锟斤拷锟斤拷前锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷叻锟斤拷锟绞�
						if(score1>scoreG1) {
							try {
								OutputStream fw;
								if(score.file instanceof HttpURLConnection) {
									URL url = new URL("http://localhost:8080/tap/score");
									URLConnection file = url.openConnection();
									file.setDoOutput(true);  //默锟斤拷为false 锟斤拷锟斤拷post锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟絪etDoOutput(true)
									file.setUseCaches(false); //锟角凤拷锟斤拷锟绞癸拷没锟斤拷锟� 锟斤拷使锟矫伙拷锟斤拷
									file.setDoOutput(true);
									file.setConnectTimeout(5000);//锟斤拷锟斤拷时时锟斤拷
									fw = file.getOutputStream();
										
								
								}else {
									fw=new FileOutputStream("C:\\score.txt");
								}	
								OutputStreamWriter osw= new OutputStreamWriter(fw,"utf-8");
								osw.write(String.valueOf(score1));
								osw.flush();
								osw.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							score.textField.setText(String.valueOf(score1));
							
						}
						score.textField_1.setText(String.valueOf(score1));
						
					}
				}else {
					tapman.setHealth(tapman.getHealth()-20);
					int health= tapman.getHealth();
					
					if(health<=0&&num==0) {
						num++;
						ZJthread = false;
						int i = JOptionPane.showConfirmDialog(null, "锟斤拷锟窖撅拷锟斤拷锟斤拷锟斤拷锟角凤拷锟斤拷锟斤拷锟斤拷戏");
						if(i==1) {
							System.exit(0);
						}else if(i==0){
							tapman.setXY(0,0, tap);
							tapman.setHealth(100);
							num=0;
							score.textField_1.setText("0");
							ZJthread = true;
							new Thread(new Launch(true)).start();;//锟斤拷锟皆硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟接碉拷锟竭筹拷

							new Thread(new Launch(false)).start();
						}else {
							System.exit(0);
						}
						
					}
				}
				
				
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			while (true) {
				if(!ZJthread) {
					close();
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initTap(ifDiJi);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
				
				if (panel != null) {
					
						if(ifDiJi) {
							synchronized (tap1) {
								Point point = panel.getLocation();
								initTap(ifDiJi);
								Point DijiPoin = tap1.getLocation();
								Point pointZJ = tap.getLocation();
								// 锟斤拷取锟接碉拷锟斤拷位锟矫ｏ拷小锟斤拷0锟斤拷锟秸讹拷锟斤拷
								if (direction == 'w') {
									panel.setLocation((int) point.getX(), (int) point.getY() - 10);
									//锟斤拷一锟斤拷锟叫讹拷锟斤拷锟接碉拷锟角否超癸拷锟叫伙拷锟斤拷y锟结，锟节讹拷锟斤拷锟斤拷锟叫讹拷锟皆硷拷锟节敌伙拷锟斤拷锟斤拷锟芥还锟杰凤拷锟斤拷锟接碉拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷锟接碉拷锟斤拷锟叫伙拷锟角凤拷锟斤拷同一直锟斤拷锟斤拷
									boolean shoot=point.getY() <(DijiPoin.getY()+tap1.getHeight())&&(pointZJ.getY()>DijiPoin.getY())&&(point.getX()>DijiPoin.getX()-10&&point.getX()<DijiPoin.getX()+tap1.getWidth());
									if (point.getY() < 0 ||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								} else if (direction == 's') {
									panel.setLocation((int) point.getX(), (int) point.getY() + 10);
									boolean shoot=point.getY() >DijiPoin.getY()&&(pointZJ.getY()<DijiPoin.getY())&&(point.getX()>DijiPoin.getX()-10&&point.getX()<DijiPoin.getX()+tap1.getWidth());
									if (point.getY() > 700||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								} else if (direction == 'a') {
									panel.setLocation((int) point.getX() - 10, (int) point.getY());
									boolean shoot=point.getX() < (DijiPoin.getX()+tap1.getWidth())&&(pointZJ.getX()>DijiPoin.getX())&&(point.getY()>DijiPoin.getY()-10&&point.getY()<DijiPoin.getY()+tap1.getHeight());
									if (point.getX() < 0||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								} else if (direction == 'd') {
									panel.setLocation((int) point.getX() + 10, (int) point.getY());
									boolean shoot=point.getX() >DijiPoin.getX()&&(pointZJ.getX()<DijiPoin.getX())&&(point.getY()>DijiPoin.getY()-10&&point.getY()<DijiPoin.getY()+tap1.getHeight());
									if (point.getY() > 1100||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								}
							}
							
						}else {
							initTap(true);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
							synchronized (panel) {
								Point point = panel.getLocation();
								Point DijiPoin = tap.getLocation();
								Point pointZJ = tap1.getLocation();
								// 锟斤拷取锟接碉拷锟斤拷位锟矫ｏ拷小锟斤拷0锟斤拷锟秸讹拷锟斤拷
								if (direction == 'w') {
									panel.setLocation((int) point.getX(), (int) point.getY() - 10);
									boolean shoot=point.getY() >DijiPoin.getY()&&point.getY() <(DijiPoin.getY()+tap.getHeight())&&(pointZJ.getY()>DijiPoin.getY())&&(point.getX()>DijiPoin.getX()-10&&point.getX()<DijiPoin.getX()+tap.getWidth());
									if (point.getY() < 0 ||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								} else if (direction == 's') {
									panel.setLocation((int) point.getX(), (int) point.getY() + 10);
									boolean shoot=point.getY() <DijiPoin.getY()+tap.getHeight()&&point.getY() >DijiPoin.getY()&&(pointZJ.getY()<DijiPoin.getY())&&(point.getX()>DijiPoin.getX()-10&&point.getX()<DijiPoin.getX()+tap.getWidth());
									if (point.getY() > 700||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								} else if (direction == 'a') {
									panel.setLocation((int) point.getX() - 10, (int) point.getY());
									boolean shoot=point.getX() >DijiPoin.getX()&&point.getX() < (DijiPoin.getX()+tap.getWidth())&&(pointZJ.getX()>DijiPoin.getX())&&(point.getY()>DijiPoin.getY()-10&&point.getY()<DijiPoin.getY()+tap.getHeight());
									if (point.getX() < 0||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								} else if (direction == 'd') {
									panel.setLocation((int) point.getX() + 10, (int) point.getY());
									boolean shoot=point.getX() <DijiPoin.getX()+tap.getWidth()&&point.getX() >DijiPoin.getX()&&(pointZJ.getX()<DijiPoin.getX())&&(point.getY()>DijiPoin.getY()-10&&point.getY()<DijiPoin.getY()+tap.getHeight());
									if (point.getY() > 1100||shoot) {
										//锟叫讹拷锟角凤拷锟斤拷械谢锟�
										ZJhit(shoot,ifDiJi);
										close();
									}
								}
							}
							
						}
					
					
					
				} else {
					Thread thread = Thread.currentThread();
					try {
						thread.join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}

	}

	class DiJi implements Runnable {
		Random ran = new Random();

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initTap(true);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
				initTap(false);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
				int int1 = 1 + ran.nextInt(40);
//				System.out.println(int1);
				// 锟斤拷1234模锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
				if (int1 <= 10) {
					Point point = tap1.getLocation();
					int y = ((int) point.getY()) - 10;
					if (y >= 0) {
						tapman1.setXY(((int) point.getX()), ((int) point.getY()) - 10, tap1);
					}
				} else if (int1 > 10 && int1 <= 20) {
					Point point = tap1.getLocation();
					int y = ((int) point.getY()) + 10;
					int height2 = tap1.getHeight();
					if (y + height2 + height2 / 2 < 700) {
						tapman1.setXY(((int) point.getX()), y, tap1);
					}
				} else if (int1 > 20 && int1 <= 30) {
					Point point = tap1.getLocation();
					int x = ((int) point.getX()) - 10;
					if (x >= 0) {
						tapman1.setXY(x, ((int) point.getY()), tap1);
					}
				} else if (int1 > 30 && int1 <= 40) {
					Point point = tap1.getLocation();
					int x = ((int) point.getX()) + 10;
					int width2 = tap1.getWidth();
					if (x + width2 + width2 / 4 <= 1100) {
						tapman1.setXY(x, ((int) point.getY()), tap1);
					}
				}
			}
		}

	}

	class DiJidir implements Runnable {
		Random ran = new Random();

		@Override
		public void run() {
			while (true) {
//				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				initTap(true);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
				initTap(false);//锟叫讹拷坦锟斤拷锟斤拷锟斤拷值锟斤拷始锟斤拷锟叫伙拷
				int int1 = 1 + ran.nextInt(40);
//				System.out.println(int1);
				// 锟斤拷1234模锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
				if (int1 <= 10) {
					tap1 = tapman1.setDirection('w', tap1);
					contentPane.add(tap1);
				} else if (int1 > 10 && int1 <= 20) {
					tap1 = tapman1.setDirection('s', tap1);
					contentPane.add(tap1);
				} else if (int1 > 20 && int1 <= 30) {
					tap1 = tapman1.setDirection('a', tap1);
					contentPane.add(tap1);
				} else if (int1 > 30 && int1 <= 40) {
					tap1 = tapman1.setDirection('d', tap1);
					contentPane.add(tap1);
				}
			}
		}

	}
}
