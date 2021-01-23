package test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.multipdf.*;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.rendering.*;

public class main {
	public static void main(String[] args) throws IOException
	{
		//Creating PDF document object   
        //PDDocument doc = new PDDocument();      
        //PDDocument doc = apriPDF("./asd.pdf");
        
        //getMiniature(doc);
        //close(doc);

        
        /*PDPage blankPage = new PDPage();  
        
        //Adding the blank page to the document  
        doc.addPage( blankPage );  
        
        //Saving the document  
        save("./blank.pdf", doc);  
        
        System.out.println("PDF created");    
    
        //Closing the document    
        close(doc); */ 
	}
	public main()
	{
		
	}
	public static void getMiniature(PDDocument doc) 
	{
		PDFRenderer renderer = new PDFRenderer(doc);
		int i = getPageNumber(doc);
		for(int j = 0; j < i; j++)
		{
        BufferedImage image = null;
		try {
			image = renderer.renderImage(j);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			ImageIO.write(image, "JPEG", new File("./miniature/"+String.valueOf(j)+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static PDDocument openPDF(String path) 
	{
		//Loading an existing document   
        File file = new File(path);   
        PDDocument doc = null;
		try {
			doc = PDDocument.load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return doc;
	}
	
	public static void save(String path, PDDocument doc) 
	{
		try {
			doc.save(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(PDDocument doc)
	{
        try {
			doc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	
	public static int getPageNumber(PDDocument doc)
	{
		return doc.getNumberOfPages();
	}
	
	public static void removePage(PDDocument doc, int n)
	{
		doc.removePage(n);  

	}
	
	public static List<PDDocument> splitPDF(PDDocument doc) 
	{
		Splitter splitter = new Splitter();
		List<PDDocument> Pages = null;
		try {
			Pages = splitter.split(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Pages;
	}
	
	
	public static void mergePDF(String pathDestination, PDDocument doc, File[] files)
	{
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		PDFmerger.setDestinationFileName(pathDestination);
		for(int i=0; i < files.length; i++)
		{
			try {
				PDFmerger.addSource(files[i]);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    try {
			PDFmerger.mergeDocuments();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
}
