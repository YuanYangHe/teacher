package com.example.root.teacher_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    connectthread abcc;
    public Socket soc;
    public BufferedReader brt;
    public BufferedWriter bwt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectuse connectserver = new connectuse();
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, grade.class);

                startActivity(intent);

            }
        });
        abcc = new connectthread();
        abcc.start();


    }

    class connectthread extends Thread
    {
        connectuse connect = (connectuse)MainActivity.this.getApplication();
        @Override
        public void run() {
            try {
                connect.init();
                soc = connect.getSocket();
                brt = connect.getread();
                bwt = connect.getwrite();
                bwt.write("test_teacher");
                bwt.write("test2");
                bwt.write("wxyz");
            }catch (IOException e){

            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK)
            return true;
        return super.dispatchKeyEvent(event);
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
