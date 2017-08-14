package com.example.arsenko.test1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends Activity implements View.OnClickListener {

    public static TextView tv2;
    EditText newET;
    Button saveBtn, showBtn;
    SharedPreferences shPref;

    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String text = intent.getStringExtra("text");
        tv2= (TextView) findViewById(R.id.text);
        tv2.setText(text);

        newET = (EditText) findViewById(R.id.newET);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);

        showBtn  =(Button) findViewById(R.id.showBtn);
        showBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View view){
        if(view == findViewById(R.id.saveBtn)){
            shPref = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor e = shPref.edit();
            e.putString(SAVED_TEXT, newET.getText().toString());
            e.commit();
            Toast.makeText(this, "Text saved! Check it", Toast.LENGTH_SHORT).show();
        }

        if(view == findViewById(R.id.showBtn)){
            shPref = getPreferences(MODE_PRIVATE);
            String savedText = shPref.getString(SAVED_TEXT, "");
            newET.setText(savedText);
            Toast.makeText(this, "Text loaded", Toast.LENGTH_SHORT).show();
        }

    }

}
