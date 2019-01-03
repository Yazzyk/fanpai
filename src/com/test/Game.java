package com.test;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Game {
	JFrame frame = new JFrame("翻牌游戏");// 实例化JFrame
	JPanel jPanelTop = new JPanel(), jPanelButtom = new JPanel();// 实例化两个JPanel
	JButton start_btn = new JButton("start");// 实例化一个开始按钮
	JButton[] jbt = new JButton[25];// 创建25个按钮数组
	ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/img/github.png")),
			imageIcon1 = new ImageIcon(this.getClass().getResource("/img/hexo.jpg"));// 实例化2个背景
	Toolkit tool=frame.getToolkit(); //得到一个Toolkit对象
	Image		logo = tool.getImage(this.getClass().getResource("/img/head.jpg"));//logo
	
	//直接通关按钮
	JButton win_btn = new JButton("win!");
	
	
	// 构造方法

	public Game() {
		frame.setIconImage(logo);
		jPanelButtom.setSize(800, 100);// 设置底部的plane大小
		jPanelButtom.setBackground(Color.CYAN);// 设置底部颜色
		frame.setBounds(500, 200, 800, 850);// 这是整体窗口大小和位置
		jPanelTop.setLayout(new GridLayout(5, 5));
		Start_click listener = new Start_click();// 实例化监听器
		// for循环实例化生成按钮
		for (int i = 0; i < 25; i++) {
			jbt[i] = new JButton(imageIcon);
			jbt[i].setPreferredSize(new Dimension(150, 150));
			jPanelTop.add(jbt[i]);
			jbt[i].addActionListener(listener);
		}
		// 为开始按钮添加监听器
		start_btn.addActionListener(listener);
		start_btn.setPreferredSize(new Dimension(100, 50));// 设置开始按钮大小
		jPanelButtom.add(start_btn);// 在jpanel中添加开始按钮
		
		jPanelButtom.add(win_btn);//添加作弊按钮
		win_btn.addActionListener(listener);//添加监听器
		
		frame.add(jPanelTop, BorderLayout.PAGE_START);
		frame.add(jPanelButtom, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		new Song().start();
	}

	// 事件监听器
	public class Start_click implements ActionListener {
		public void getImg(JButton jbt) {
			ImageIcon img;
			if (jbt.getIcon() == imageIcon) {
				img = imageIcon1;
			} else {
				img = imageIcon;
			}
			jbt.setIcon(img);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//直接胜利按钮
			if(e.getSource() == win_btn) {
				for(int i=0;i<25;i++) {
					jbt[i].setIcon(imageIcon1);
				}
			}
			
			if (e.getSource() == start_btn) {
				for (int i = 0; i < jbt.length; i++) {
					jbt[i].setIcon(imageIcon);
				}
			}
			for (int i = 0; i < jbt.length; i++) {
				if (e.getSource() == jbt[i]) {
					switch (i) {
					case 0:
						getImg(jbt[i]);
						getImg(jbt[i + 1]);
						getImg(jbt[i + 5]);
						break;
					case 4:
						getImg(jbt[i]);
						getImg(jbt[i - 1]);
						getImg(jbt[i + 5]);
						break;
					case 20:
						getImg(jbt[i]);
						getImg(jbt[i - 5]);
						getImg(jbt[i + 1]);
						break;
					case 24:
						getImg(jbt[i]);
						getImg(jbt[i - 1]);
						getImg(jbt[i - 5]);
						break;
					case 5:
					case 10:
					case 15:
						getImg(jbt[i]);
						getImg(jbt[i + 1]);
						getImg(jbt[i - 5]);
						getImg(jbt[i + 5]);
						break;
					case 1:
					case 2:
					case 3:
						getImg(jbt[i]);
						getImg(jbt[i - 1]);
						getImg(jbt[i + 1]);
						getImg(jbt[i + 5]);
						break;
					case 9:
					case 14:
					case 19:
						getImg(jbt[i]);
						getImg(jbt[i - 1]);
						getImg(jbt[i - 5]);
						getImg(jbt[i + 5]);
						break;
					case 21:
					case 22:
					case 23:
						getImg(jbt[i]);
						getImg(jbt[i - 1]);
						getImg(jbt[i + 1]);
						getImg(jbt[i - 5]);
						break;
					default:
						getImg(jbt[i]);
						getImg(jbt[i + 1]);
						getImg(jbt[i - 1]);
						getImg(jbt[i + 5]);
						getImg(jbt[i - 5]);
					}
				}
			}
			for (int i = 0; i < 25; i++) {
				if(jbt[i].getIcon() == imageIcon) {
					break;
				}else if(i == 24) {
					JOptionPane.showMessageDialog(null, "You Win!","You Win!",JOptionPane.CANCEL_OPTION);
				}
			}
		}
	}

	// 主方法
	public static void main(String[] args) {
		Game game = new Game();
	}
}

//BGM
class Song extends Thread {
	URL url = this.getClass().getResource("/music/bgm.mid");
	AudioClip audio = Applet.newAudioClip(url);

	@Override
	public void run() {
		audio.play();
		audio.loop();
	}
}
