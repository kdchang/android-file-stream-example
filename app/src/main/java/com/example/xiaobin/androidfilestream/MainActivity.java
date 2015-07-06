package com.example.xiaobin.androidfilestream;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {
    private String name = "file.txt";
    private Button saveBtn, readBtn;
    private EditText inputFile;
    private TextView outputFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputFile = (EditText)findViewById(R.id.inputFile);
                String str = inputFile.getText().toString();
                try {
                    // open file
                    FileOutputStream out = openFileOutput(name, MODE_PRIVATE);
                    out.write(str.getBytes());
                    out.close();
                    Toast.makeText(MainActivity.this, "寫入成功！", Toast.LENGTH_LONG).show();
                    inputFile.setText("");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        readBtn = (Button)findViewById(R.id.readBtn);
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream in = openFileInput(name);
                    byte[] data = new byte[128];
                    in.read(data);
                    in.close();
                    String str = new String(data);
                    Toast.makeText(MainActivity.this, "成功讀取檔案", Toast.LENGTH_LONG).show();
                    TextView output = (TextView)findViewById(R.id.outputFile);
                    output.setText("讀取檔案內容：\n" + str);
                } catch (IOException ex){
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
