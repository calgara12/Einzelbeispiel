package com.example.einzelbeispiel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import com.example.einzelbeispiel.NetworkTask;

public class MainActivity extends AppCompatActivity {

    Button sendButton;
    EditText matNumberTextBox;
    TextView serverResponseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverResponseTextView = findViewById(R.id.lblResponse);
        matNumberTextBox = findViewById(R.id.tbInputNum);
        sendButton = findViewById(R.id.buSend);





        sendButton.setOnClickListener(
                v -> {
                    NetworkTask networkTask = new NetworkTask(matNumberTextBox.getText().toString());
                    networkTask.start();
                    try{
                        networkTask.join();
                    }
                    catch (Exception ex){
                        Log.e("Error",ex.getMessage());
                    }

                    serverResponseTextView.setText(networkTask.getServerResponse());
                }
        );

    }




}