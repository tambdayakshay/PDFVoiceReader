package com.example.akshay.pdfvoicereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static Button button_new,button_open,button_t2p,button_exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        onButtonOpenListener();
        onExitListener();
        onNewListener();
        onText2PdfListener();

    }

    public void onNewListener(){
        button_new = (Button) findViewById(R.id.new_id);
        button_new.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),CreateNew_Activity.class);
                        startActivity(i);
                    }
                }
        );
    }
    public void  onExitListener()
    {
            button_exit = (Button) findViewById(R.id.exit_id);
            button_exit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                    System.exit(0);
                }
            });

    }
    public  void onButtonOpenListener()
    {
        button_open = (Button) findViewById(R.id.open_id);
        button_open.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent("com.example.akshay.pdfvoicereader.Open");
                        startActivity(intent);
                    }
                }
        );

    }


    public void onText2PdfListener(){
        button_t2p = (Button) findViewById(R.id.text2pdf_id);
        button_t2p.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getApplicationContext(),Text2pdf_Activity.class);
                        startActivity(i);
                    }
                }
        );
    }

}
