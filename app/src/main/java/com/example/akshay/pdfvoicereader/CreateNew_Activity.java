package com.example.akshay.pdfvoicereader;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

public class CreateNew_Activity extends AppCompatActivity {

    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new);

        onCancel();
    }

    public void createPDF(View view) throws FileNotFoundException {
        //reference to EditText
        EditText et=(EditText)findViewById(R.id.txt_input);
        //create document object
        Document doc=new Document();
        //output file path
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        String outpath= Environment.getExternalStorageDirectory()+"/"+currentDateTimeString+".pdf";

        try {

        //create pdf writer instance
            PdfWriter.getInstance(doc, new FileOutputStream(outpath));
        //open the document for writing
            doc.open();
        //add paragraph to the document
            doc.add(new Paragraph(et.getText().toString()));
        //close the document
            doc.close();

            Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DocumentException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void onCancel()
    {
        cancel =(Button)findViewById(R.id.cancel);

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        System.exit(0);
                    }
                }
        );


    }

    }
