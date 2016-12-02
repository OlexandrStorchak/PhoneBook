package com.example.alex.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Alex on 02.12.2016.
 */
public class AddNewActivity extends AppCompatActivity{
    EditText name,number;
    Button ok;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        name = (EditText)findViewById(R.id.add_new_name);
        number = (EditText)findViewById(R.id.add_new_number);
        ok = (Button)findViewById(R.id.add_new_ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.length()>=1 && number.length()>=1){
                Intent intent = new Intent();
                intent.putExtra("name",name.getText().toString());
                intent.putExtra("number",number.getText().toString());
                setResult(RESULT_OK,intent);
                finish();}
                else {
                    Toast.makeText(getApplicationContext(),"name and number must be not null",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
