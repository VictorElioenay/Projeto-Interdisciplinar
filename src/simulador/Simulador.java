package simulador;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JToolTip;

import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.Document;

import cpu.Cpu;
import main_memory.Main_Memory;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Simulador extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane contentPane;
	private Configurar config;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private Main_Memory main_memory;
	private Cpu cpu;
	int i  = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Simulador frame = new Simulador();
					frame.setTitle("Simulador Cache");
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
	public Simulador() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Simulador.class.getResource("/icons/icons8_processor_30px_1.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 392);
		contentPane = new JDesktopPane() {
			private static final long serialVersionUID = 1L;
			Image im = (new ImageIcon(Simulador.class.getResource("/icons/Imagem de fundo.jpg"))).getImage(); 
	          public void paintComponent(Graphics g){        
	           g.drawImage(im,0,0,this);  
          
	          }     
		};
	
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 591, 25);
		contentPane.add(menuBar);
		
		
		JEditorPane jeditorPane = new JEditorPane();
		
		JScrollPane scrollPane = new JScrollPane(jeditorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 36, 422, 316);
		contentPane.add(scrollPane);
	
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Show Main Memory");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Document doc = jeditorPane.getDocument();
					doc.putProperty(Document.StreamDescriptionProperty, null);
					jeditorPane.setPage(  main_memory.getFile().toURL() );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(SystemColor.controlShadow);
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Show CPU");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Document doc = jeditorPane.getDocument();
					doc.putProperty(Document.StreamDescriptionProperty, null);
					jeditorPane.setPage( cpu.getFile().toURL() );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(SystemColor.controlShadow);
		mnNewMenu.add(separator_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Show Cache DirectMap");
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(SystemColor.controlShadow);
		mnNewMenu.add(separator_3);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Show Cache_Two");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(SystemColor.controlShadow);
		mnNewMenu.add(separator_4);
		
		JMenuItem mntmShowCachefour = new JMenuItem("Show Cache_Four");
		mnNewMenu.add(mntmShowCachefour);
		
		JMenu mnNewMenu_1 = new JMenu("Set Up");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmLoadConfig = new JMenuItem("Load Config");
		mntmLoadConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				if( chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION )
					config = new Configurar( chooser.getSelectedFile().toString() );
			}
		});
		mnNewMenu_1.add(mntmLoadConfig);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setForeground(SystemColor.controlShadow);
		mnNewMenu_1.add(separator_5);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Load Main Memory");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if( chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
					main_memory = new Main_Memory( chooser.getSelectedFile().toURI() );
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setForeground(SystemColor.controlShadow);
		mnNewMenu_1.add(separator_6);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Load CPU");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				if( chooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
					cpu = new Cpu( chooser.getSelectedFile().toURI() );
				}
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JButton btnNewButton = new JButton("");
		contentPane.setLayer(btnNewButton, 0);
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setToolTipText("Next Step");
	
		btnNewButton.setIcon(new ImageIcon(Simulador.class.getResource("/icons/icons8_circled_chevron_right_30px_2.png")));
		btnNewButton.setBounds(452, 263, 56, 39);
		contentPane.add(btnNewButton);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Timer time = new Timer();
				time.scheduleAtFixedRate(new TimerTask() {
					public void run() {

					}
				}, 1000, 2000);
			}
		});
		button_2.setBackground(UIManager.getColor("Button.background"));
		button_2.setIcon(new ImageIcon(Simulador.class.getResource("/icons/icons8_circled_chevron_right_30px_1.png")));
		button_2.setToolTipText("Execute All");
		button_2.setBounds(452, 313, 56, 39);
		contentPane.add(button_2);
		
		
		jeditorPane.setEditable(false);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jeditorPane.setEditable(true);
			}
		});
		btnNewButton_1.setToolTipText("Edit File");
		btnNewButton_1.setIcon(new ImageIcon(Simulador.class.getResource("/icons/icons8_edit_file_30px_4.png")));
		btnNewButton_1.setBounds(519, 263, 62, 39);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeditorPane.setEditable(false);
				try {
					BufferedWriter writer = new BufferedWriter(  new FileWriter( new File(main_memory.getFile()) ));
					writer.append(jeditorPane.getText());
					writer.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} 
			}
		});
		btnNewButton_2.setToolTipText("Save Edit");
		btnNewButton_2.setIcon(new ImageIcon(Simulador.class.getResource("/icons/icons8_save_30px_2.png")));
		btnNewButton_2.setBounds(519, 313, 62, 39);
		contentPane.add(btnNewButton_2);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("activeCaption"));
		panel.setBorder(new LineBorder(Color.WHITE, 2, true));
		panel.setBounds(452, 38, 129, 181);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEnabled(true);
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(30, 136, 66, 20);
		panel.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(30, 84, 66, 20);
		panel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(30, 31, 66, 20);
		panel.add(textField_2);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setForeground(Color.WHITE);
		label.setBounds(97, 34, 22, 14);
		panel.add(label);
		
		JLabel label_1 = new JLabel("%");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(97, 87, 22, 14);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("%");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(97, 139, 22, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("DirectMap Mis");
		label_3.setFont(new Font("Impact", Font.PLAIN, 10));
		label_3.setForeground(Color.DARK_GRAY);
		label_3.setBounds(30, 11, 66, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Two_Way Mis");
		label_4.setFont(new Font("Impact", Font.PLAIN, 10));
		label_4.setForeground(Color.DARK_GRAY);
		label_4.setBounds(30, 62, 66, 14);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("Four_Way Mis");
		label_5.setFont(new Font("Impact", Font.PLAIN, 10));
		label_5.setForeground(Color.DARK_GRAY);
		label_5.setBounds(30, 115, 68, 14);
		panel.add(label_5);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(SystemColor.text);
		separator.setBounds(452, 250, 129, 2);
		contentPane.add(separator);
		
		
	}
}
