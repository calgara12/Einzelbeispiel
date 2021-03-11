package com.example.einzelbeispiel;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetworkTask extends Thread {
    private String _serverResponse;
    private String _matNum;

    public NetworkTask(String matNum){
        this._matNum=matNum;
    }

    @Override
    public void run() {
        try{
            Socket clientSocket = new Socket("se2-isys.aau.at",53212);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer= new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToServer.writeBytes(_matNum + "\n");
            _serverResponse = inFromServer.readLine();
            clientSocket.close();
        }
        catch (Exception ex){
            Log.e("Error",ex.getMessage());
        }
    }
    public String getServerResponse(){
        return _serverResponse;
    }

}
