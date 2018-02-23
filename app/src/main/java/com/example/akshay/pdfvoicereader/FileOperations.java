package com.example.akshay.pdfvoicereader;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by akshay on 7/4/17.
 */

class FileOperations {

    public FileOperations()
    {}
    public String read(String fname)
    {
        BufferedReader br =null;
        String response = null;
        try
        {
            StringBuffer output =new StringBuffer();
            String fpath =fname;//"sdcard/"+ fname +".pdf";
            PdfReader reader =new PdfReader(new FileInputStream(fpath));
            PdfReaderContentParser parser =new PdfReaderContentParser(reader);
            StringWriter strW =new StringWriter();
            TextExtractionStrategy strategy;
            for(int i =1; i <= reader.getNumberOfPages(); i++)
            {
                strategy = parser.processContent(i,new SimpleTextExtractionStrategy());
                strW.write(strategy.getResultantText());
            }
            response = strW.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }
        return response;
    }
    public String readText(String fname) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fname));
        String everything= null;
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }

        return everything;
    }
}
