package chris;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class AnalyzerWindow {

	private JFrame frame;
	public static ArrayList<Keys> listOfWords = new ArrayList<Keys>();
	public static int lineCount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyzerWindow window = new AnalyzerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		int o= 0;
		Scanner scan = null;
	    try {
	        scan = new Scanner(new File("keywords.txt"));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	    while (scan.hasNextLine()) {
	        Scanner sc = new Scanner(scan.nextLine());
	        while (sc.hasNext()) {
	            String currentKey = sc.next();
	            //Keys key = new Keys(currentKey, 0);
	            //System.out.println(key.getKeyword());
	            listOfWords.add(new Keys(currentKey,o));
	        }
	    }
	    for(int i = 0; i < listOfWords.size(); i++) {
			System.out.println(listOfWords.get(i).getKeyword());
	    }
	}

	/**
	 * Create the application.
	 */
	public AnalyzerWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Press to read a Java source file to count listOfWords and count the lines of code");
		lblNewLabel.setBounds(28, 11, 396, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 89, 375, 132);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Press me!!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Timer.startTimer();
				JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int result = fc.showOpenDialog(null);
				if(result == fc.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						lineCount = ReadCodeTools.countLines(file);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					readSrc(file);
					
				}
				Timer.endTimer();
				textArea.setText(ReadCodeTools.displayKeywords(listOfWords, lineCount));
			}
		});
		btnNewButton.setBounds(162, 41, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	public static void readSrc(File file) {
		Scanner scan = null;
	    try {
	        scan = new Scanner(file);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();  
	    }
	    while (scan.hasNextLine()) {
            Scanner sc = new Scanner(scan.nextLine());
            while (sc.hasNext()) {
            	String current = sc.next();
            	ReadCodeTools.checkKeys(current, listOfWords);
            }
        }
	}
}
