package mp3Java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Rectangle;
import java.io.*;

import javazoom.jl.player.Player;
import javazoom.jl.decoder.JavaLayerException;

public class Display {

	private JTextField pathField;
	
	private JFrame player;
	
	private File songs;

	/**
	 * Launch the application.
	 */
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					
					Display window = new Display();
					window.player.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	/**
	 * Create the application.
	 */
	
	
	public Display() {
		initialize();
	}

	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		player = new JFrame();
		player.setTitle("Cr3 Player");
		player.setBounds(100, 100, 470, 237);
		player.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		player.setLocationRelativeTo(null);
		player.getContentPane().setLayout(null);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					Player p = new Player(new FileInputStream(songs));
					
					p.play();
					
				} catch(Exception e2) {
					JOptionPane.showMessageDialog(null, "Cannot Play without Audio File", "Error", JOptionPane.ERROR_MESSAGE);
				}
								
			}
		});
		
		startButton.setIcon(new ImageIcon("C:\\Users\\joshu\\Downloads\\Play-Blue-Button-icon.png"));
		startButton.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		startButton.setBounds(10, 109, 435, 55);
		player.getContentPane().add(startButton);
		
		JLabel pathField = new JLabel("Song Path");
		pathField.setBounds(new Rectangle(0, 0, 40, 40));
		pathField.setBackground(Color.LIGHT_GRAY);
		pathField.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 17));
		pathField.setBounds(10, 10, 299, 40);
		player.getContentPane().add(pathField);
		
		JButton folderButton = new JButton("Open");
        folderButton.addActionListener(new ActionListener() {
			
			
        	public void actionPerformed(ActionEvent e) {
        		open();	
			}
		});
		
		
		
		folderButton.setIcon(new ImageIcon("C:\\Users\\joshu\\Downloads\\Folder-icon.png"));
		folderButton.setFont(new Font("Segoe UI Semilight", Font.PLAIN, 17));
		folderButton.setBounds(309, 10, 137, 40);
		player.getContentPane().add(folderButton);
	}
	
	private void open() {
		try {
			
			JFileChooser choose = new JFileChooser(); 
			choose.setDialogTitle("Choosing song to load..");
			choose.showOpenDialog(null);
			songs = choose.getSelectedFile();
			pathField.setText(songs.getAbsolutePath());
			
			
			if(!songs.getName().endsWith(".mp3")) {
				JOptionPane.showMessageDialog(null, "Invalid File Type", "Error", JOptionPane.ERROR_MESSAGE);
				open();
			}
			
		} catch (Exception e1){
			
		
			
		}
	}
}


