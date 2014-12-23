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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.JPopupMenu;
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
import javax.swing.border.LineBorder;

public class MainWindow extends JFrame {

	public static String id;
	public static String pw;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
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
							    User.Logout(id, pw);
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
						EditProfileDialog.id = id;
						EditProfileDialog.pw = pw;
						EditProfileDialog.main(null);
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
							User.Logout(id, pw);
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
							User.Logout(id, pw);
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
		
		panel_1.setBounds(419, 31, 365, 531);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton button = new JButton("发表");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 10));
		button.setBounds(161, 501, 61, 20);
		panel_1.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 403, 345, 93);
		panel_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		
		//激活自动换行功能
		textArea.setLineWrap(true);
		//激活断行不断字功能
		textArea.setWrapStyleWord(true);
		
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 24, 345, 355);
		panel_1.add(scrollPane_1);
		
		Vector<Comments> comment = new Vector<Comments>();
		JList<Comments> list = new JList<Comments>(comment);
		list.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		list.setCellRenderer( new CommentCellRenderer() );
		try{
			Comments.getComments(comment);
		}
		catch(Exception commentException){
			UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
    		JOptionPane.showMessageDialog(null, commentException.getMessage(), "获取评论失败", JOptionPane.ERROR_MESSAGE);
		}
		
		JPopupMenu commentPopupMenu = new JPopupMenu();
		list.add(commentPopupMenu);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem(" 点赞(后续版本会加入) ");
		mntmNewMenuItem_4.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		commentPopupMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem(" 撤销点赞(后续版本会加入) ");
		mntmNewMenuItem_6.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		commentPopupMenu.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem(" 删除评论 ");
		mntmNewMenuItem_5.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		commentPopupMenu.add(mntmNewMenuItem_5);
		
		//实现一个鼠标右键点击弹出下拉菜单的监听器
		class myJListListener extends MouseAdapter {
			public void mouseClicked(MouseEvent e) {
				//获得鼠标点击的列位置
				int index = list.locationToIndex(e.getPoint());
				//如果在列表内 并且 按键为右键的话
				if(index >= 0 && e.getButton() == 3)
				{
					//该项被选中 且 弹出下拉菜单
					list.setSelectedIndex(index);
					commentPopupMenu.show(list,e.getX(),e.getY());
				}       
		    }
		}
		
		list.addMouseListener(new myJListListener());
		
		scrollPane_1.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 20, 399, 542);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 73, 399, 469);
		panel_2.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 399, 469);
		panel.add(scrollPane_2);
		
		Vector<MapNode> path = new Vector<MapNode>();
		JList<MapNode> list_1 = new JList<MapNode>(path);
		list_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		scrollPane_2.setViewportView(list_1);
		
		JLabel lblNewLabel = new JLabel("起点：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 17, 54, 15);
		panel_2.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(74, 11, 212, 21);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("终点：");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(9, 48, 54, 15);
		panel_2.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(74, 42, 212, 21);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBounds(296, 40, 93, 23);
		panel_2.add(btnNewButton);
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

//实现ListCellRenderer接口 来达到绘制list中的cell的目的
class CommentCellRenderer extends JTextArea implements ListCellRenderer{
	private String text;
	private String uid;
	private boolean isSelected;
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
       this.text = ((Comments)value).text;
       this.uid = ((Comments)value).uID;
       this.isSelected = isSelected;
       setFont(list.getFont());
       setLineWrap(true);
       //加个边框
       setBorder(BorderFactory.createLineBorder(Color.lightGray));
       
       //解决自动换行文本无法生长的问题 28为一行的汉字个数
       int textWrapNumber = text.length() / 28;
       for(int i=1;i<=textWrapNumber;i++)
    	   text = text + "\n";
       
       setText("["+uid+"] 说：\n"+text);
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
    
    public boolean getSelected(){
    	return isSelected;
    }
}
class MapCellRenderer extends JTextArea implements ListCellRenderer{
	private String text;
	private String uid;
	private boolean isSelected;
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
       this.text = ((Comments)value).text;
       this.uid = ((Comments)value).uID;
       this.isSelected = isSelected;
       setFont(list.getFont());
       setLineWrap(true);
       //加个边框
       setBorder(BorderFactory.createLineBorder(Color.lightGray));
       
       //解决自动换行文本无法生长的问题 28为一行的汉字个数
       int textWrapNumber = text.length() / 28;
       for(int i=1;i<=textWrapNumber;i++)
    	   text = text + "\n";
       
       setText("["+uid+"] 说：\n"+text);
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
    
    public boolean getSelected(){
    	return isSelected;
    }
}
