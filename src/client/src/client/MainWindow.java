package client;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;
import javax.swing.JList;

public class MainWindow extends JFrame {

	public static String id;
	public static String pw;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					//令窗体在屏幕中间显示
					frame.setLocationRelativeTo(null);
					
					//设置关闭窗口时结束程序
					frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
					
					//创建窗口关闭监听器 在关闭之前自动进行登出操作
					frame.addWindowListener(new WindowAdapter() {   
					    public void windowClosing(WindowEvent e) {  
						    super.windowClosing(e);  
						    try{
							    Logout.main(id, pw);
						    }
						    catch(Exception event_logout){
								UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
	                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
	                    		JOptionPane.showMessageDialog(null, event_logout.getMessage(), "登出失败", JOptionPane.ERROR_MESSAGE);
						    }
				     }  
				      
				    });   
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		//固定窗口大小
		setResizable(false);

		setTitle(id+" 您好，欢迎您使用地图路径查询");
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
		
		JMenuItem mntmNewMenuItem = new JMenuItem(" 查看/修改个人资料   ");
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						EditProfile.id = id;
						EditProfile.pw = pw;
						EditProfile.main(null);
					}
				}
				);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem(" 切换账号 ");
		mntmNewMenuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e2) {
						try{
							//登出并回到登录界面
							Logout.main(id, pw);
							WelcomeWindow.main(null);
							dispose();	
						}
						catch(Exception event_logout){
							UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		JOptionPane.showMessageDialog(null, event_logout.getMessage(), "登出失败", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem(" 退出 ");
		mntmNewMenuItem_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e3) {
						try{
							//登出并结束程序
							Logout.main(id, pw);
							System.exit(0);
						}
						catch(Exception event_logout){
							UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		JOptionPane.showMessageDialog(null, event_logout.getMessage(), "登出失败", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				);
		
		JMenu mnNewMenu_1 = new JMenu(" 帮助 ");
		mnNewMenu_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem(" 关于我们  ");
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JPanel panel = new JPanel();
		panel.setBounds(26, 46, 500, 500);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		
		//设置 panelBorderTitle 并修改字体
		TitledBorder panelBorderTitle = 
				new TitledBorder(
						UIManager.getBorder("TitledBorder.border"),
						"\u7528\u6237\u8BC4\u8BBA", 
						TitledBorder.LEADING, 
						TitledBorder.TOP, 
						null, 
						new Color(0, 0, 0));
		panel_1.setBorder(panelBorderTitle);
		panelBorderTitle.setTitleFont(new Font("微软雅黑", Font.PLAIN, 12));
		
		panel_1.setBounds(534, 46, 232, 500);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton button = new JButton("发表");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		button.setBounds(161, 470, 61, 20);
		panel_1.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 388, 212, 75);
		panel_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		
		//激活自动换行功能
		textArea.setLineWrap(true);
		//激活断行不断字功能
		textArea.setWrapStyleWord(true);
		
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 25, 212, 354);
		panel_1.add(scrollPane_1);
		
		//实现ListCellRenderer接口 来达到绘制list中的cell的目的
		class FontCellRenderer extends JTextArea implements ListCellRenderer
		{
			private String text;
		    private Color background;
		    private Color foreground;
		    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
		       text = (String)value;
		       background = isSelected ? list.getSelectionBackground() : list.getBackground();
		       foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
		       setFont(list.getFont());
		       //激活自动换行功能
		       setLineWrap(true);
		       // 加个边框
		       setBorder(BorderFactory.createLineBorder(Color.lightGray));
		       setText(text);
		       //选中效果
		       if (isSelected) {
		            setBackground(list.getSelectionBackground());
		            setForeground(list.getSelectionForeground());
		        } else {
		            setBackground(list.getBackground());
		            setForeground(list.getForeground());
		        }
		       return this;
		    }
		}

		
		Vector<String> comment = new Vector<String>();
		JList<String> list = new JList<String>(comment);
		list.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		comment.add("远东大道：\r\n这道儿太jb堵了！\n");
		comment.add("金大路：\r\n前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈！\r\n");
		comment.add("金大路：\r\n前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！\n");
		list.setCellRenderer( new FontCellRenderer() );
		
		scrollPane_1.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 25, 212, 354);
		panel_1.add(panel_2);
		mntmNewMenuItem_1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event_aboutUs) {
						UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                		JOptionPane.showMessageDialog(null, 
                				"\n"
                				+ "   地图路径查询系统 v1.0"
                				+ "\n"
                				+ "\n"
                				+ "作者：\n"
                				+ "       朱维希 121220319\n"
                				+ "       丁文韬 12122xxxx\n"
                				+ "       李昊轩 121220044\n"
                				, "关于我们", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				);
		
		
	}
}
