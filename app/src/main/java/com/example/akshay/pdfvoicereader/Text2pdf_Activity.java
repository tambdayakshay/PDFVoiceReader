package com.example.akshay.pdfvoicereader;

import android.content.Context;
import android.content.Intent;
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
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class Text2pdf_Activity extends AppCompatActivity{
    private static final int REQUEST_PATH = 1;

    String data;
    String curFileName;
    Button Convetrp2t,back;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2pdf_);
            edittext = (EditText)findViewById(R.id.editText);
            Convetrp2t = (Button) findViewById(R.id.conv_p2t);
        onCancel();

    }

    public void getfile(View view){
        Intent intent1 = new Intent(this, FileChooser.class);
        startActivityForResult(intent1,REQUEST_PATH);
    }
    // Listen for results.
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        // See which child activity is calling us back.
        if (requestCode == REQUEST_PATH){
            if (resultCode == RESULT_OK) {
                curFileName = data.getStringExtra("GetFileName");
                edittext.setText(curFileName);
            }
        }
    }


    public void onClickConvert(View view) throws IOException {

        Intent i=this.getIntent();
        String path=i.getExtras().getString("GetPath");
        String readfilename = path;

        FileOperations fop =new FileOperations();
        data = fop.readText(readfilename);
        if(data !=null)
        {
           convt2p(getApplicationContext(),data);
        }else
        {
            Toast.makeText(getApplicationContext(),"File not Found",Toast.LENGTH_SHORT).show();
        }


    }

    private void convt2p(Context context, String sbody) {

        /*File root = new File(Environment.getExternalStorageDirectory(), "PDF Voice Reader");
        if (!root.exists()) {
            root.mkdirs();
        }
        File gpxfile = new File(root, sFileName);*/


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
            doc.add(new Paragraph(sbody));
            //close the document
            doc.close();

            Toast.makeText(context,"Saved",Toast.LENGTH_SHORT).show();

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
        back =(Button)findViewById(R.id.backt2p);

        back.setOnClickListener(
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

