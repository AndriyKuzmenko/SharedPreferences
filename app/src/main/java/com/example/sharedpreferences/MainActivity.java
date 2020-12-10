package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    int i;
    EditText nameET, numberET;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberET=(EditText)findViewById(R.id.numberET);
        nameET=(EditText)findViewById(R.id.nameET);
        readFile();
    }

    public void count(View view)
    {
        i++;
        numberET.setText(i+"");
    }

    public void reset(View view)
    {
        i=0;
        numberET.setText(i+"");
    }

    public void exit(View view)
    {
        SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("name",nameET.getText().toString());
        editor.putInt("i",i);
        editor.commit();
        finish();
    }

    public void readFile()
    {
        SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        nameET.setText(settings.getString("name",""));
        i=settings.getInt("i",0);
        numberET.setText(""+i);
    }
}
