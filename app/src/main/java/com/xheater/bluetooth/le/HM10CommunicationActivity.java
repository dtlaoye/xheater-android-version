package com.xheater.bluetooth.le;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class HM10CommunicationActivity extends AppCompatActivity {

    private TextView mHasSerial;
    private TextView textSerialConnection;
    private String mDeviceName;
    private String m_deviceAddress;
    private BleConnectionService m_bleConnectionService;
    private boolean m_hasSerial;
    private boolean mConnected = false;
    private BluetoothGattCharacteristic characteristicTX;
    private BluetoothGattCharacteristic characteristicRX;
    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";
    private SeekBar simpleSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm10_communication);
        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(StaticResources.EXTRAS_DEVICE_NAME);
        m_deviceAddress = intent.getStringExtra(StaticResources.EXTRAS_DEVICE_ADDRESS);
        m_bleConnectionService = new BleConnectionService(this, m_deviceAddress);

        Toolbar toolbar = findViewById(R.id.app_bar_hm10_communication); // declare the toolbar.
        TextView textDeviceName = toolbar.findViewById(R.id.toolbar_device_name);
        TextView textDeviceAddress = toolbar.findViewById(R.id.toolbar_device_address);

        textDeviceName.setText(mDeviceName);
        textDeviceAddress.setText(m_deviceAddress);

        setSupportActionBar(toolbar); // Make toolbar visible on activity. Xml is very minimalistic
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        textSerialConnection = findViewById(R.id.bluetooth_serial_cxn_value);
        textSerialConnection.setText(StaticResources.CONNECTION_STATE_CONNECTING);
        m_hasSerial = false;

        IntentFilter filterMaster =new IntentFilter();
        filterMaster.addAction(StaticResources.BROADCAST_NAME_CONNECTION_UPDATE);
        filterMaster.addAction(StaticResources.BROADCAST_NAME_SERVICES_DISCOVERED);
        filterMaster.addAction(StaticResources.BROADCAST_NAME_TX_CHARATERISTIC_CHANGED);
        registerReceiver(m_bleBroadcastReceiver, filterMaster);

        simpleSeekBar = (SeekBar) findViewById(R.id.simpleSeekBar);
        // @SuppressLint("WrongViewCast") Button disconnectButton = findViewById(R.id.disconnectB);
        final TextView energy = findViewById(R.id.energyUsed);

        /*
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reset = String.valueOf(0);
                m_bleConnectionService.writeToBluetoothSerial(reset);

        });
        */


        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                String androidCmd = String.valueOf(progress);
                energy.setText(androidCmd + "%");
                m_bleConnectionService.writeToBluetoothSerial(androidCmd);
                System.out.println("Value sent to Arduino");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



    }

    /*
    public void communication_Button_Click(View v)
    {
        m_bleConnectionService.writeToBluetoothSerial(StaticResources.COMMUNICATION_ANDROID_TO_HM10);

    }
     */


    public void connect_Button_Click(View v)
    {
        textSerialConnection.setText(StaticResources.CONNECTION_STATE_CONNECTING);
        m_bleConnectionService.connect(m_deviceAddress);

    }

    public void disconnect_Button_Click(View v){
        simpleSeekBar.setProgress(0);
        String reset = String.valueOf(0);
        m_bleConnectionService.writeToBluetoothSerial(reset);
        textSerialConnection.setText(StaticResources.CONNECTION_STATE_DISCONNECTED);
        m_bleConnectionService.disconnect();
    }

    private final BroadcastReceiver m_bleBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            final String broadcastName = intent.getAction();
            Log.i("Broadcast Receiver",
                    "Recieved Broadcast name = " + broadcastName
            );
            switch(broadcastName)
            {
                case StaticResources.BROADCAST_NAME_CONNECTION_UPDATE:
                    final String connection = intent.getStringExtra(StaticResources.EXTRAS_CONNECTION_STATE);
//                    textSerialConnection.setText(connection);
                    break;
                case StaticResources.BROADCAST_NAME_SERVICES_DISCOVERED:
                    final String serial = intent.getStringExtra(StaticResources.EXTRAS_SERVICES_DISCOVERED);
                    textSerialConnection.setText(serial);
                    if (serial == StaticResources.SERVICES_DISCOVERY_CHARACTERISTIC_SUCCESS)
                    {
                        m_hasSerial = true;
                    }
                    break;
                case StaticResources.BROADCAST_NAME_TX_CHARATERISTIC_CHANGED:
                    final String txData = intent.getStringExtra(StaticResources.EXTRAS_TX_DATA);
//                    final String txData = "Tx data received from HM10";
                    Toast.makeText(context, txData, Toast.LENGTH_SHORT).show();
                    Log.i("Broadcast Received",
                            "TxData = " + txData + ";");
                    break;
            }
        }
    };








}
