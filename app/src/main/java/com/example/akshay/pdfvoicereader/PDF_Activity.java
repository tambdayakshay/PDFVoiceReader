package com.example.akshay.pdfvoicereader;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class PDF_Activity extends AppCompatActivity implements
        View.OnClickListener,
        TextToSpeech.OnInitListener{
    String text;
    int iItrToken;
    int speed,lang;
    String[] strText;
    Context c;
    TextToSpeech tts_;

    TextView text_shown;
    HashMap<String, String> map;
    Button play_button, pause_button;
    Handler seekHandler = new Handler();
    String language[]={" ","ENGLISH","RUS","UK","SPN"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_);



        /*Bundle bun=getIntent().getExtras();
        String path_=bun.getString("pdf name");*/
        Intent i=this.getIntent();
        String path_=i.getExtras().getString("PATH");

        getInit();
        read_pdf(path_);

    }




    public void getInit() {

        play_button = (Button) findViewById(R.id.start);
        text_shown=(TextView)findViewById(R.id.editText1);
        pause_button = (Button) findViewById(R.id.stop);
        play_button.setOnClickListener(this);
        pause_button.setOnClickListener(this);
        //seekbar.setMax(player.getDuration());
        map = new HashMap<String, String>();
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "com.org.file2audio");

        tts_=new TextToSpeech(this,this);
    }

    public void read_pdf(String path) {
        String readfilename = path;//fnameread.getText().toString();
        //EditText editText=(EditText)findViewById(R.id.editText1);
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
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS)
        {
            Locale loc = new Locale("spa", language[lang]);
            int result =tts_.setLanguage(loc);
			 /*tts_.setSpeechRate(speed_[speed]);*/
            //int result = tts_.setLanguage(Locale.ENGLISH);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
            {
                Log.e("TTS", "This Language is not supported");
            }
        }

        else
        {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.start:
                Log.e("started...","start_button");
                //tts_=new TextToSpeech(this,this);
                start(this.text);
                break;
            case R.id.stop:
                stop();
                Log.e("Paused...","stop_button");
        }

    }

    public void start(String text){

        String toSpeak =text;
        tts_.setPitch((float) 1.0);
       // tts_.setSpeechRate((float) speed_[speed]);
        //Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();

        strText = toSpeak.split("\\n.");

        for (iItrToken = 0; iItrToken < strText.length; iItrToken++)
        {
            tts_.speak(strText[iItrToken], TextToSpeech.QUEUE_ADD, map);
        }
    }

    public void stop(){

        if(tts_!=null)
        {
            tts_.stop();
            tts_.shutdown();
        }

        super.onDestroy();
        tts_=new TextToSpeech(this,this);
    }

    public void convertP2t(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "PDF Voice Reader");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pdf, menu);
        return true;
    }

    //Edit PDF VIEW
    private void editPDFView(String path)
    {
        Intent i=new Intent(getApplicationContext(),Edit_Activity.class);
        i.putExtra("PATH",path);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_pdf2text) {
            Intent i=this.getIntent();
            String path=i.getExtras().getString("PATH");
            String readfilename = path;

            String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

            FileOperations fop =new FileOperations();
            text = fop.read(readfilename);
            if(text !=null)
            {
                convertP2t(getApplicationContext(),currentDateTimeString+".txt",text);
            }else
            {
                Toast.makeText(getApplicationContext(),"File not Found",Toast.LENGTH_SHORT).show();
            }

            return true;
        }
       /* if(id == R.id.Edit){


            Intent i=this.getIntent();
            String path=i.getExtras().getString("PATH");
                editPDFView(path);
         *//*   Intent intent= new Intent(this,Edit_Activity.class);
            startActivity(intent);*//*
            return true;


        }*/
        if (id == R.id.action_exit )
        {
            finish();
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }

}
