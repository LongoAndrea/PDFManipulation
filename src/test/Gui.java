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
import java.nio.file.Paths;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class Gui {

	static JFrame frmPdfreorder;
	static int clicks, i = 0;
	static String path = "";
	static String pathsave, pathfolderwork = "";
	List<PDDocument> pagine=null;
	static PDDocument p;
	static PDDocument newpdf;
	static JPanel grid = new JPanel();;
	static JScrollPane panelPane = new JScrollPane();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frmPdfreorder.setVisible(true);
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
		frmPdfreorder = new JFrame();
		frmPdfreorder.setResizable(false);
		frmPdfreorder.setTitle("PDFReorder");
		frmPdfreorder.setIconImage(new ImageIcon("./download.jfif").getImage());
		
		frmPdfreorder.setBounds(100, 100, 1079, 750);
		frmPdfreorder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPdfreorder.getContentPane().setLayout(null);
		
		//JPanel grid = new JPanel();
		
		
		
		grid.setBounds(10, 49, 1000, 604);
		JScrollPane panelPane = new JScrollPane(grid);
		grid.setLayout(new GridLayout(1, 0, 0, 0));
				panelPane.setBounds(10, 64, 1045, 604);
		frmPdfreorder.getContentPane().add(panelPane);
		
		JButton button_1 = new JButton("Start");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostra();
			}
		});
		button_1.setBounds(10, 10, 150, 38);
		frmPdfreorder.getContentPane().add(button_1);
		
		JButton btnNewButton = new JButton("Export");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.save(pathsave, newpdf);
				System.out.println("pdf salvato in "+ pathsave);
				main.close(newpdf);
				main.close(p);
				JOptionPane.showMessageDialog(frmPdfreorder, "Il PDF è stato salvato!");


			}
		});
		btnNewButton.setBounds(186, 10, 150, 38);
		frmPdfreorder.getContentPane().add(btnNewButton);
		
		
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frmPdfreorder.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		menuBar.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Save");
		menuBar.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				JFileChooser fileChooser = new JFileChooser();
	            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	            int option = fileChooser.showOpenDialog(frmPdfreorder);
	            if(option == JFileChooser.APPROVE_OPTION){
	               File file = fileChooser.getSelectedFile();
	               pathsave = file.getPath();
	               pathfolderwork = pathsave+"\\miniature";
	               System.out.println(pathsave);
	            }
	            pathsave = pathsave+"\\Export.pdf";
				
				/*
				JFileChooser f = new JFileChooser();
		        f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		        f.showSaveDialog(null);
		        

		        pathsave = String.valueOf(f.getCurrentDirectory());
		        //pathsave = pathsave+"\\Desktop\\Export.pdf";
		        String userDirectory = Paths.get("")
		                .toAbsolutePath()
		                .toString();
		        System.out.println(userDirectory);
		        */
			}
		});
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
		
		
		
		
		
		
		frmPdfreorder.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				try
				{
				deleteFolder(new File(pathfolderwork));
				}
				catch(Exception a) {}
				finally {}
				
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
	
	public static void deleteFolder(File file){
	      for (File subFile : file.listFiles()) {
	         if(subFile.isDirectory()) {
	            deleteFolder(subFile);
	         } else {
	            subFile.delete();
	         }
	      }
	      file.delete();
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
		main.getMiniature(p,pathfolderwork);
		List<PDDocument> pagine = (List<PDDocument>)main.splitPDF(p);
		
		int row;
		if((i/2)%2==0)
			row= (i/2);
		else
			row = (i/2)+1;
		grid.setLayout(new GridLayout(0, 3, 5, 5));
		//grid.setSize(500, 500);
		
		
		
		
		
		for(int j=0; j<i; j++)
		{
			
			button = new JButton();
			button.setName(String.valueOf(j));
			//button.setPreferredSize(new Dimension(70, 70));
			button.setMinimumSize(new Dimension(Integer.MAX_VALUE,Integer.MAX_VALUE));
			button.setBackground(null);
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
					pathfolderwork+"\\"+String.valueOf(j)+".jpg").getImage()
		            .getScaledInstance(300, 600,
		                    java.awt.Image.SCALE_FAST)))));
			button.add(lblNewLabel);
			
			
			grid.add(button);
		}
	
		grid.setVisible(true);
		//grid.revalidate();
		frmPdfreorder.revalidate();
	
		
	
	}
}
