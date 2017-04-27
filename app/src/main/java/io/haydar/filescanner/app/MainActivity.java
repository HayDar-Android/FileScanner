package io.haydar.filescanner.app;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import io.haydar.filescanner.FileInfo;
import io.haydar.filescanner.FileScanner;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileScanner();
            }
        });

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
        System.out.println(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    private void fileScanner() {
        FileScanner.getInstance(this).clear();
        FileScanner.getInstance(this).setType(".png").start(new FileScanner.ScannerListener() {
            @Override
            public void onScanBegin() {
                Log.d(TAG, "onScanBegin: ");
            }

            @Override
            public void onScanEnd() {
                Log.d(TAG, "onScanEnd: ");
                ArrayList<FileInfo> fileInfoArrayList= FileScanner.getInstance(MainActivity.this).getAllFiles();
                for (FileInfo fileInfo : fileInfoArrayList) {
                    Log.d(TAG, "fileScanner: "+fileInfo.getFilePath());
                }
            }

            @Override
            public void onScanning(String paramString, int progress) {
                Log.d(TAG, "onScanning: " + progress);
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
