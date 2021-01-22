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
        PDDocument doc = apriPDF("./asd.pdf");
        
        getMiniature(doc);
        close(doc);

        
        /*PDPage blankPage = new PDPage();  
        
        //Adding the blank page to the document  
        doc.addPage( blankPage );  
        
        //Saving the document  
        save("./blank.pdf", doc);  
        
        System.out.println("PDF created");    
    
        //Closing the document    
        close(doc); */ 
	}
	public static void getMiniature(PDDocument doc) throws IOException
	{
		PDFRenderer renderer = new PDFRenderer(doc);
		int i = getPageNumber(doc);
		for(int j = 0; j < i; j++)
		{
        BufferedImage image = renderer.renderImage(j);
        ImageIO.write(image, "JPEG", new File("./miniature/"+String.valueOf(j)+".jpg"));
		}
	}
	
	public static PDDocument apriPDF(String path) throws IOException
	{
		//Loading an existing document   
        File file = new File(path);   
        PDDocument doc = PDDocument.load(file); 
        return doc;
	}
	
	public static void save(String path, PDDocument doc) throws IOException
	{
		doc.save(path);
	}
	
	public static void close(PDDocument doc) throws IOException
	{
        doc.close();  
	}
	
	public static int getPageNumber(PDDocument doc)
	{
		return doc.getNumberOfPages();
	}
	
	public static void removePage(PDDocument doc, int n)
	{
		doc.removePage(n);  

	}
	
	public static List<PDDocument> splitPDF(PDDocument doc) throws IOException
	{
		Splitter splitter = new Splitter();
		List<PDDocument> Pages = splitter.split(doc);
		return Pages;
	}
	
	
	public static void mergePDF(String pathDestination, PDDocument doc, File[] files) throws IOException
	{
		PDFMergerUtility PDFmerger = new PDFMergerUtility();
		PDFmerger.setDestinationFileName(pathDestination);
		for(int i=0; i < files.length; i++)
		{
			PDFmerger.addSource(files[i]);
		}
	    PDFmerger.mergeDocuments();


	}
	
	
}
