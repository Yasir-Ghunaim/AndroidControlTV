package com.tvremote;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.bluetooth.*;
import android.content.Intent;


public class Main extends Activity implements OnClickListener{
	  ProgressDialog loadingDialog;
	  private char remoteCode[]= {'i', 'p', 'm', 'R', 'u', 'l', 'o', 'r', 'd', 'U', 'M', 'D'};
	  private static final int numberOfButtons = 12;
	  private static final int REQUEST_ENABLE_BT = 1;
	  private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	  private static String address = "00:06:66:09:8B:23";
	  private BluetoothAdapter btAdapter = null;
	  private BluetoothSocket btSocket = null;
	  private OutputStream outStream = null;
	
	  private Button connectButton;
	  private Button remote[] = new Button[numberOfButtons];
	  
	  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectButton = (Button) findViewById(R.id.connect);    
        connectButton.setOnClickListener(this);
        remote[0] = (Button) findViewById(R.id.input);
        remote[1] = (Button) findViewById(R.id.power);   
        remote[2] = (Button) findViewById(R.id.menu);   
        remote[3] = (Button) findViewById(R.id.return1);   
        remote[4] = (Button) findViewById(R.id.up);   
        remote[5] = (Button) findViewById(R.id.left);   
        remote[6] = (Button) findViewById(R.id.ok);   
        remote[7] = (Button) findViewById(R.id.right);   
        remote[8] = (Button) findViewById(R.id.down);   
        remote[9] = (Button) findViewById(R.id.volumeUp);   
        remote[10] = (Button) findViewById(R.id.mute);   
        remote[11] = (Button) findViewById(R.id.volumeDown); 
        
        for(int i=0; i<numberOfButtons; i++)
        	remote[i].setOnClickListener(this);
        

        bluetoothIniti();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void bluetoothIniti(){
    	btAdapter = BluetoothAdapter.getDefaultAdapter();
    	if(btAdapter != null)
    		checkBluetoothState();            
    }
    
    public void checkBluetoothState(){
    	
    	if (!btAdapter.isEnabled()) {  // check if bluetooth is enabled
        	Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    	
    	//define the targeted device(client) with the given MAC address
    	BluetoothDevice device = btAdapter.getRemoteDevice(address);
    	 try {
         	btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
         } catch (IOException e) {Log.e("error",e.getMessage());}
         btAdapter.cancelDiscovery();
    	
    }
    
    public void createConnection(){
   
    	connectButton.setVisibility(View.GONE);
    	for(int i=0; i<numberOfButtons; i++)
        	remote[i].setVisibility(View.VISIBLE);
        
    }

    public void sendData(char msg){
    	try {
    	      outStream = btSocket.getOutputStream();
    	    } catch (IOException e) {Log.e("error",  e.getMessage());}

    	    char msgBuffer = msg;
    	    try {
    	    	outStream.write(msgBuffer);
    	    	//Toast.makeText(this,"Sending...:  " + msgBuffer, Toast.LENGTH_SHORT).show();  
    	    } catch (IOException e) {
    	    	Toast.makeText(this,"Can't send message", Toast.LENGTH_SHORT).show();  
    	    }
    }

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
        case R.id.connect: 
        	
        	new connection().execute();
        	
            
         break;
        
        case R.id.input:
         sendData(remoteCode[0]);
         break;
         
        case R.id.power:
       	 sendData(remoteCode[1]);
        break;
        
        case R.id.menu:
       	 sendData(remoteCode[2]);
        break;
        
        case R.id.return1:
       	 sendData(remoteCode[3]);
        break;
        
        case R.id.up:
       	 sendData(remoteCode[4]);
        break;
        
        case R.id.left:
       	 sendData(remoteCode[5]);
        break;
        
        case R.id.ok:
       	 sendData(remoteCode[6]);
        break;
        
        case R.id.right:
       	 sendData(remoteCode[7]);
        break;
        
        case R.id.down:
       	 sendData(remoteCode[8]);
        break;
        
        case R.id.volumeUp:
       	 sendData(remoteCode[9]);
        break;
        
        case R.id.mute:
       	 sendData(remoteCode[10]);
        break;
        
        case R.id.volumeDown:
       	 sendData(remoteCode[11]);
        break;
     }
		
	}
	
	 public void onPause() {
		    super.onPause();
	 }

	

	public void onStop() {
	  super.onStop();
	  	try {
		    btSocket.close();
		  } catch (IOException e) {Log.e("error", e.getMessage());}
	}
	
	public void onDestroy() {
	  super.onDestroy();
	  try {
		    btSocket.close();
		  } catch (IOException e) {Log.e("error", e.getMessage());}
	}

	private class connection extends AsyncTask<String, Integer, String> {
    	boolean state = true;
        @Override
        protected void onPreExecute() {
        	loadingDialog = new ProgressDialog(Main.this);
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadingDialog.setMessage("جاري الإتصال...");
            loadingDialog.show();
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(String... urls) {
        	
        	  try {
        		  btSocket.connect();
                  state = true;
                  
                } catch (IOException e) { // can't establish connection
              	  
              	  state = false;
      	            try {
      	              btSocket.close();
      	            } catch (IOException e2) {Log.e("BT","cannot close connection");}
                }
        	return null;
        }
        @Override
        protected void onPostExecute(String result) {
        	loadingDialog.dismiss();
        	if(state){
                Log.d("BT","Connection established ");
        		Toast.makeText(Main.this,"Connection established", Toast.LENGTH_SHORT).show();  
        		createConnection();
        	}else  Toast.makeText(Main.this,"Can't establish connection", Toast.LENGTH_SHORT).show(); 
        	
        	
        	
        	}
        }
}

