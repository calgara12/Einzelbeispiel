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
import java.util.ArrayList;
import java.util.Collections;

import com.example.einzelbeispiel.NetworkTask;

public class MainActivity extends AppCompatActivity {

    private Button _sendButton;
    private Button _calcCrossSumButton;
    private EditText _matNumberTextBox;
    private TextView _serverResponseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _serverResponseTextView = findViewById(R.id.lblResponse);
        _matNumberTextBox = findViewById(R.id.tbInputNum);
        _sendButton = findViewById(R.id.buSend);
        _calcCrossSumButton = findViewById(R.id.buCrossSum);





        _sendButton.setOnClickListener(
                v -> {
                    NetworkTask networkTask = new NetworkTask(_matNumberTextBox.getText().toString());
                    networkTask.start();
                    try{
                        networkTask.join();
                    }
                    catch (InterruptedException ex){
                        Log.e("Error",ex.getMessage());
                    }

                    _serverResponseTextView.setText(networkTask.getServerResponse());
                }
        );

        _calcCrossSumButton.setOnClickListener(
                v -> {
                    int crossSum = 0;
                    String matricularNumber = _matNumberTextBox.getText().toString();
                    for(char c: matricularNumber.toCharArray()){
                        crossSum += (int) c - '0';
                    }

                    String binaryValue = "";
                    ArrayList binaryAL = decimalToBinary(crossSum);
                    for(Object i: binaryAL){
                        binaryValue += i.toString();
                    }
                    _serverResponseTextView.setText(binaryValue);
                }
        );

    }

    ArrayList<Integer> decimalToBinary(int decimalNum){
        int temp = 0;
        ArrayList<Integer> result = new ArrayList<Integer>();

        for(int i = 0; decimalNum > 0;i++){
            result.add(decimalNum % 2);
            decimalNum = decimalNum/2;
        }
        Collections.reverse(result);
        return result;
    }




}