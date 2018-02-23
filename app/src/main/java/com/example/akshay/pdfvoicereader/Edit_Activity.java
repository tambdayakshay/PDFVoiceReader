package com.example.akshay.pdfvoicereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit_Activity extends AppCompatActivity implements View.OnClickListener {



    Button save_button, cancel_button;
    String text;
    EditText text_shown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);


        Intent i=this.getIntent();
        String path=i.getExtras().getString("PATH");

        editView_pdf(path);
        getInit();


    }

    private void getInit() {

        save_button = (Button) findViewById(R.id.save_id);
        text_shown=(EditText)findViewById(R.id.editText_id);
        cancel_button= (Button) findViewById(R.id.back);
        save_button.setOnClickListener(this);
        cancel_button.setOnClickListener(this);
    }

    private void editView_pdf(String path) {
        String readfilename = path;
        FileOperations fop =new FileOperations();
        text = fop.read(readfilename);
        if(text !=null)
        {
            text_shown.setText(text);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"File not Found",Toast.LENGTH_SHORT).show();
            text_shown.setText(" ");
        }

    }


    @Override
    public void onClick(View v) {

    }

}

