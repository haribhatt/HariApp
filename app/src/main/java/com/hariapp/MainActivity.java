package com.hariapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_go_to_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        Button btnShowPopup = findViewById(R.id.btn_show_popup);
        btnShowPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(MainActivity.this);
                customDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_setting) {
//            Toast.makeText(getBaseContext(),"Setting Clicked",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), SettingActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_search) {
            Intent intent = new Intent(getBaseContext(), SearchActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_notification) {
            Toast.makeText(getBaseContext(), "This feature will coming soon.", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
