package test;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel grid = new JPanel();
		grid.setBounds(10, 49, 866, 604);
		frame.getContentPane().add(grid);
		
		
		//apro pdf
		main pdf = new main();
		PDDocument p = pdf.openPDF("./asd.pdf");
		int i = main.getPageNumber(p);
		main.getMiniature(p);
		
		int row= (i/3);
		grid.setLayout(new GridLayout(row, 3, 0, 0));
		
		
		
		
		JButton button = null;
		
		for(int j=0; j<i; j++)
		{
			
			button = new JButton();
			button.setName(String.valueOf(j));
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					String id = e.getComponent().getName();
					JOptionPane.showMessageDialog(frame, String.valueOf(id));
				}
			});
			JLabel lblNewLabel = 
			new JLabel(new ImageIcon(((new ImageIcon(
					"./miniature/"+String.valueOf(j)+".jpg").getImage()
		            .getScaledInstance(70, 70,
		                    java.awt.Image.SCALE_SMOOTH)))));
			button.add(lblNewLabel);
			
			grid.add(button);
		}
		
		
		main.close(p);
		
	}
}
