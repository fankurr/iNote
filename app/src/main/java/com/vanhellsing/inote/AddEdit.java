package com.vanhellsing.inote;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.vanhellsing.inote.helper.DbHelper;

public class AddEdit extends AppCompatActivity {
    EditText txt_id, txt_title, txt_desc;
    Button btn_submit, btn_cancel;
    DbHelper SQLite = new DbHelper(this);
    String id, titleTEXT, descTEXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_twotone_arrow_back_ios_new_24);

        txt_id = (EditText) findViewById(R.id.txt_id);
        txt_title = (EditText) findViewById(R.id.txt_title);
        txt_desc = (EditText) findViewById(R.id.txt_desc);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        titleTEXT = getIntent().getStringExtra(MainActivity.TAG_TITLE);
        descTEXT = getIntent().getStringExtra(MainActivity.TAG_DESC);

        if (id == null || id == "") {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            txt_id.setText(id);
            txt_title .setText(titleTEXT);
            txt_desc.setText(descTEXT);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (txt_id.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e){
                    Log.e("Submit", e.toString());
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blank();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Make blank all Edit Text
    private void blank() {
        txt_title.requestFocus();
        txt_id.setText(null);
        txt_title.setText(null);
        txt_desc.setText(null);
    }

    // Save data to SQLite database
    private void save() {
        if (String.valueOf(txt_title.getText()).equals(null) || String.valueOf(txt_title.getText()).equals("") ||
                String.valueOf(txt_desc.getText()).equals(null) || String.valueOf(txt_desc.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please write something ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(txt_title.getText().toString().trim(), txt_desc.getText().toString().trim());
            blank();
            finish();
        }
    }

    // Update data in SQLite database
    private void edit() {
        if (String.valueOf(txt_title.getText()).equals(null) || String.valueOf(txt_title.getText()).equals("") ||
                String.valueOf(txt_desc.getText()).equals(null) || String.valueOf(txt_desc.getText()).equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Please write something ...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(txt_id.getText().toString().trim()), txt_title.getText().toString().trim(),
                    txt_desc.getText().toString().trim());
            blank();
            finish();
        }
    }
}
