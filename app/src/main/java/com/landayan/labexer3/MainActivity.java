package com.landayan.labexer3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.id.message;

public class MainActivity extends AppCompatActivity {

    EditText et_data, et_filename;
    SharedPreferences preferences;
    Button btn_SharedPreferences, btn_InternalStorage, btn_InternalCache, btn_ExternalCache,
           btn_ExternalStorage, btn_ExtPublicStorage;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_SharedPreferences = (Button) findViewById(R.id.btn_SharedPreferences);
        btn_InternalStorage = (Button) findViewById(R.id.btn_InternalStorage);
        btn_InternalCache = (Button) findViewById(R.id.btn_InternalCache);
        btn_ExternalCache = (Button) findViewById(R.id.btn_ExternalCache);
        btn_ExternalStorage = (Button) findViewById(R.id.btn_ExternalStorage);
        btn_ExtPublicStorage = (Button) findViewById(R.id.btn_ExtPublicStorage);
        et_data = (EditText) findViewById(R.id.et_data);
        et_filename = (EditText) findViewById(R.id.et_filename);
        preferences = getSharedPreferences("pref",MODE_WORLD_READABLE);
    }
    //sharedpreferences
    public void SharedPreferences(View view){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(et_filename.getText().toString(), et_data.getText().toString());
        editor.commit();
        Toast.makeText(this, "Saved in Shared Preferences!", Toast.LENGTH_LONG).show();
    }

    //internalstorage
    public void InternalStorage(View view) {
        String data = et_data.getText().toString();
        String filename = et_filename.getText().toString();
        try{
            fos = openFileOutput(String.valueOf(et_data), Context.MODE_PRIVATE);
            fos = openFileOutput(String.valueOf(et_filename), Context.MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.write(filename.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved in Internal Storage", Toast.LENGTH_SHORT).show();
    }

    //internalcache
    public void InternalCache(View view){
        String data = et_data.getText().toString();
        File folder = getCacheDir();
        File file = new File(folder, String.valueOf(et_filename));
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved in Internal Cache.", Toast.LENGTH_LONG).show();
    }

    //externalcache
    public void ExternalCache(View view){
        File folder = getExternalCacheDir();
        File file = new File(folder, String.valueOf(et_filename));
        String data = et_data.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved in External Cache.", Toast.LENGTH_LONG).show();
    }

    //externalstorage
    public void ExternalStorage(View view){
        File folder = getExternalFilesDir("Temp");
        File file = new File(folder, String.valueOf(et_filename));
        String data = et_data.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved in External Storage.", Toast.LENGTH_LONG).show();
    }


    //externalstorage
    public void ExtPublicStorage(View view){
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(folder, String.valueOf(et_filename));
        String data = et_data.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(data.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(this, "Successfully saved in External Public Storage.", Toast.LENGTH_LONG).show();
    }

    //next
    public void Next(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
        finish();
    }

}
