package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    /**
     * This method runs when the count button is pressed. it increases i
     * by 1.
     * @param view
     */

    public void count(View view)
    {
        i++;
        numberET.setText(i+"");
    }

    /**
     * This method runs when the reset button is pressed. It resets the
     * value of i.
     * @param view
     */

    public void reset(View view)
    {
        i=0;
        numberET.setText(i+"");
    }

    /**
     * This method runs when the exit button is pressed. It saves the data and exits
     * from the app.
     * @param view
     */

    public void exit(View view)
    {
        SharedPreferences settings=getSharedPreferences("PREFS_NAME",MODE_PRIVATE);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("name",nameET.getText().toString());
        editor.putInt("i",i);
        editor.commit();
        finish();
    }

    /**
     * This method runs when the program starts. It reads the data from the file.
     *
     * @Param
     */

    public void readFile()
    {
        try
        {
            SharedPreferences settings = getSharedPreferences("PREFS_NAME", MODE_PRIVATE);
            nameET.setText(settings.getString("name", ""));
            i = settings.getInt("i", 0);
        }
        catch(Exception IO)
        {
            i=0;
        }
        numberET.setText(""+i);
    }

    /**
     * @param menu  - the menu
     * @return      creates a menu with one option - cresits
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add("Credits");

        return true;
    }

    /**
     *
     * @param item - the item that was selected
     * @return     After the user pressed on the credits option, this method
     *             starts CreditsActivity
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent si=new Intent(this, CreditsActivity.class);
        startActivity(si);

        return true;
    }
}
