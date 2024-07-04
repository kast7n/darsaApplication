package com.example.darsaapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        Button btnSend = findViewById(R.id.btnSendToRec);
        EditText txtMsgToReceive = findViewById(R.id.etSendMainMsg);

        TextView mainText = findViewById(R.id.txtMsgMainRecive);

        Intent recievedIntent = getIntent();
        String message2 = recievedIntent.getStringExtra("message2");

        mainText.setText(message2);

        Activity current = this;

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msgToSend = txtMsgToReceive.getText().toString();
                Intent intent = new Intent(current, Activity2.class);
                intent.putExtra("message",msgToSend);

                startActivityForResult(intent,1);

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            TextView mainText = findViewById(R.id.txtMsgMainRecive);
            String repMsg = data.getStringExtra("replyMessage");

            mainText.setText(repMsg);
        }
    }
}
