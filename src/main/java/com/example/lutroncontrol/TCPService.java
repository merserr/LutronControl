package com.example.lutroncontrol;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPService extends Service {

    final String LOG_TAG = "==TCPService==";
    String ipaddress = "0.0.0.0";
    int port = 1;

    public int onStartCommand(Intent intent, int flags, int startId) {

        if(intent!=null) {
            ipaddress = intent.getStringExtra("ipaddress");
            port = intent.getIntExtra("port", 2);
        }

        new TCPConnect(ipaddress, port).execute();

        return super.onStartCommand(intent, flags, startId);
    }

    @SuppressLint("StaticFieldLeak")
    class TCPConnect extends AsyncTask<Void, Integer, Void> {

        boolean connect=false;
        final int port;
        final String ipaddress;
        String sendstring;
        String inputMassage;
        Socket socket = null;
        DataOutputStream dataOutputStream = null;
        DataInputStream dataInputStream = null;
        BufferedReader mBufferIn = null;

        public TCPConnect(String ipaddress_in, int port_in) {
            // TODO Auto-generated constructor stub
            ipaddress = ipaddress_in;
            port=port_in;
        }

        @Override
        protected Void doInBackground(Void... params) {

            Log.d(LOG_TAG, "sending comand to address "+ipaddress+" port "+port);

            sendstring="\r\ninfo\r\n";

            try {
                socket = new Socket();
                socket.setSoTimeout(10000);
                socket.connect(new InetSocketAddress(ipaddress, port), 1000);
                connect=socket.isConnected();

                dataInputStream = new DataInputStream(socket.getInputStream()); //read from client through inputstream

                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                //    dataOutputStream.writeBytes(" ");
                dataOutputStream.writeBytes(sendstring);

                mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //read input massage from buffer
                inputMassage = mBufferIn.readLine();

//  delay 5000ms
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException ex) {
//                    ex.printStackTrace();
//                }

                dataInputStream.close();
                dataOutputStream.close();
                socket.close();
 //               Log.d(LOG_TAG, "--socket.close()--");

            }catch (UnknownHostException e) {
                e.printStackTrace();
                Log.d(LOG_TAG, "--UnknownHostException--");
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(LOG_TAG, "--IOException--");
            }

            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!connect) {
                Log.d(LOG_TAG, "NOT CONNECT");

                // send massage "No connect" to MainActivity
                Intent intent2 = new Intent(MainActivity.BROADCAST_ACTION);
                intent2.putExtra(MainActivity.MASSAGE, ipaddress+":"+port);
                sendBroadcast(intent2);
            } else {
   //              Log.d(LOG_TAG, "inputMassage: "+ inputMassage);

                Intent intent1 = new Intent(MainActivity.BROADCAST_ACTION);
                intent1.putExtra("inputMassage", inputMassage);
                sendBroadcast(intent1);
            }
            connect=false;
        }
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
