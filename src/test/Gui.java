package test;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Button;

public class Gui {

	private JFrame frame;
	static int clicks, i = 0;
	static String path = "";
	static String pathsave = "";
	List<PDDocument> pagine=null;
	static PDDocument p;
	static PDDocument newpdf;
	static JPanel grid = new JPanel();;
	

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
				 
					//mostra();		
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
		
		//JPanel grid = new JPanel();
		
		grid.setBounds(10, 49, 866, 604);
		frame.getContentPane().add(grid);
		
		Button button_1 = new Button("Start");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostra();
			}
		});
		button_1.setBounds(52, 0, 90, 38);
		frame.getContentPane().add(button_1);
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		menuBar.add(mntmNewMenuItem);
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
				JFileChooser jFileChooser = new JFileChooser();
		        jFileChooser.setCurrentDirectory(new File("./"));
		         
		        int result = jFileChooser.showOpenDialog(new JFrame());
		     
		     
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = jFileChooser.getSelectedFile();
		            path = selectedFile.getAbsolutePath();
		        }
		             
				
			}
			
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				JFileChooser f = new JFileChooser();
		        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		        f.showSaveDialog(null);

		        pathsave = String.valueOf(f.getCurrentDirectory());
		        pathsave = pathsave+"\\ris.pdf";
		        //System.out.println(f.getSelectedFile());
			}
		});
		menuBar.add(mntmNewMenuItem_1);
		
		
		
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				main.save(pathsave, newpdf);
				main.close(newpdf);
				main.close(p);
				System.out.println("pdf salvato in "+ pathsave);
			}
		});
		//main.save("./ris.pdf", newpdf);
		//main.close(newpdf);
		//main.close(p);
		
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
	
	public static void mostra()
	{
		//apro pdf
		main pdf = new main();
		
		//documento nuovo riordinato
		newpdf = new PDDocument();
		JButton button = null;
		//apro pdf
		
		p = test.main.openPDF(path);
		i = main.getPageNumber(p);
		main.getMiniature(p);
		List<PDDocument> pagine = (List<PDDocument>)main.splitPDF(p);
		
		int row= (i/3);
		grid.setLayout(new GridLayout(row, 3, 0, 0));
		
		
		
		for(int j=0; j<i; j++)
		{
			
			button = new JButton();
			button.setName(String.valueOf(j));
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					
					int numpage = Integer.valueOf(e.getComponent().getName());
					if(e.getComponent().isEnabled())
					{
						clicks += e.getClickCount();
					
						//label.setText(String.valueOf(clicks));
						//if(clicks <= i)
						newpdf.addPage((PDPage)pagine.get(numpage).getPage(0));
					}
					e.getComponent().setEnabled(false);
					
				}
			});
			
			JLabel lblNewLabel = 
			new JLabel(new ImageIcon(((new ImageIcon(
					"./miniature/"+String.valueOf(j)+".jpg").getImage()
		            .getScaledInstance(70, 70,
		                    java.awt.Image.SCALE_FAST)))));
			button.add(lblNewLabel);
			
			grid.add(button);
		}
	
		grid.setVisible(true);
		grid.revalidate();
		
	
		
	
	}
}
