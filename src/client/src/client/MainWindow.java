package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import org.eclipse.jface.viewers.deferred.SetModel;
import java.awt.Font;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow(id);
					frame.setVisible(true);
					//令窗体在屏幕中间显示
					frame.setLocationRelativeTo(null);
					
					//设置关闭窗口时结束程序
					frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow(String id) {
		//固定窗口大小
		setResizable(false);

		setTitle(id+"您好，欢迎您使用路径查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 794, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu(" 用户 ");
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("查看/修改个人资料  ");
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						EditProfile edit = new EditProfile();
						edit.main(null);
					}
				}
				);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("注销");
		mntmNewMenuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("退出");
		mntmNewMenuItem_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e3) {
						System.exit(0);
					}
				}
				);
		
		JMenu mnNewMenu_1 = new JMenu(" 帮助 ");
		mnNewMenu_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("关于我们    ");
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu_1.add(mntmNewMenuItem_1);
	}
}
