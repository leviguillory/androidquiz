package com.example.android.thequibblersquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class title extends AppCompatActivity {

    // Button to load the quiz
    // Ensures user entered their name to load quiz.
    // player name, explicitly gotten from EditText

    public Button startQuiz;

    public void init() {
        startQuiz = (Button) findViewById(R.id.startQuiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startQuiz = new Intent(title.this, quiz.class);
                EditText userName = findViewById(R.id.userName);
                String uName;

                uName = userName.getText().toString();
                if ((uName.equals("")) || (uName.equals(null))) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter your name -->",
                            Toast.LENGTH_SHORT);
                    TextView toastMessage = toast.getView().findViewById(android.R.id.message);
                    toastMessage.setTextColor(Color.WHITE);
                    toast.show();
                    return;
                }

                startActivity(startQuiz);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        init();

    }
}
