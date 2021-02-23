package com.example.btth_2322021;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextID,editTextHoten,editTextNamsinh;
    Button buttonInsert,buttonUpgrade,buttonDelete,buttonLoadAll;
    MyDBHelper dbHelper;

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper.openDB();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbHelper.closeDB();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextID=(EditText)findViewById(R.id.editTextID);
        editTextHoten=(EditText)findViewById(R.id.editTextHoten);
        editTextNamsinh=(EditText)findViewById(R.id.editTextNamsinh);
        buttonInsert=(Button)findViewById(R.id.buttonInsert);
        buttonUpgrade=(Button)findViewById(R.id.buttonUpgrade);
        buttonDelete=(Button)findViewById(R.id.buttonDelete);
        buttonLoadAll=(Button)findViewById(R.id.buttonLoadAll);
        //goi dbHelper
        dbHelper=new MyDBHelper(MainActivity.this);
        //insert
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultAdd = dbHelper.Insert(Integer.parseInt(editTextID.getText().toString()),editTextHoten.getText().toString(),Integer.parseInt(editTextNamsinh.getText().toString()));
                if (resultAdd==-1){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"InSerted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonUpgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //goi ham update ben class helper
                long resultUpdate=dbHelper.Update(Integer.parseInt(editTextID.getText().toString()),editTextHoten.getText().toString(),Integer.parseInt(editTextNamsinh.getText().toString()));
                if (resultUpdate==0){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else if (resultUpdate==1){
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Error,multiple",Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultDelete= dbHelper.Delete(Integer.parseInt(editTextID.getText().toString()));
                if(resultDelete==0){
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        buttonLoadAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buffer de chua
                StringBuffer bufferData=new StringBuffer();
                Cursor cursor=dbHelper.LoadAllRecord();
                //duyet
                for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                    bufferData.append(cursor.getInt(cursor.getColumnIndex(MyDBHelper.getId())));
                    bufferData.append(cursor.getString(cursor.getColumnIndex(MyDBHelper.getName())));
                    bufferData.append(cursor.getInt(cursor.getColumnIndex(MyDBHelper.getNamsinh())));
                    Toast.makeText(MainActivity.this,bufferData,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}