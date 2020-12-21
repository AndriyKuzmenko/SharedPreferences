package com.example.internalfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity
{
    EditText eT;
    TextView tV;
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tV=(TextView)findViewById(R.id.tV);
        eT=(EditText)findViewById(R.id.eT);

        text="";

        try
        {

            FileInputStream fis= openFileInput("test.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            text = br.readLine();
            tV.setText(text);
            isr.close();
        }
        catch(Exception IO)
        {

        }
    }

    /**
     * This method runs when either the save button or exit button is pressed.
     * It saves the text in the save file.
     * @param view - the button that was pressed
     */

    public void save(View view)
    {
        text+=eT.getText();
        tV.setText(text);
        try
        {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(text);
            bw.close();
        }
        catch(Exception IO)
        {

        }

    }

    /**
     * This method runs when the reset button is pressed. It deletes all the text in both the
     * edit text and the text view, but for some reason not in the file. Don't ask me, I didn't
     * create this task.
     * @param view - the button that was pressed
     */

    public void reset(View view)
    {
        eT.setText("");
        tV.setText("");
    }

    /**
     * This method runs when the exit button is pressed. It runs the save method and then
     * finishes the activity.
     * @param view - the button that was pressed
     */

    public void exit(View view)
    {
        save(view);
        finish();
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
