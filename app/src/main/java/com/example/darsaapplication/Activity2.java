package com.example.darsaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSend = findViewById(R.id.btnSendToMain);
        EditText txtMsgToMain = findViewById(R.id.etSendSecondaryMsg);
        String msgToSend = txtMsgToMain.getText().toString();
        Activity current = this;

        TextView secondaryText = findViewById(R.id.txtMsgSecondaryRecieve);

        Intent recievedIntent = getIntent();
        String message = recievedIntent.getStringExtra("message");

        secondaryText.setText(message);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgToSend = txtMsgToMain.getText().toString();
                Intent replyIntent = new Intent(current, Activity2.class);
                replyIntent.putExtra("replyMessage",msgToSend);
                setResult(RESULT_OK,replyIntent);
                finish();



            }
        });
        
        



    }
}