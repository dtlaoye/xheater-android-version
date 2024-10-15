package com.xheater.bluetooth.le;

import android.bluetooth.BluetoothDevice;

public class CustomBluetoothDeviceWrapper {

    BluetoothDevice m_device;
    int m_rssi;

    CustomBluetoothDeviceWrapper(BluetoothDevice device, int rssi)
    {
        m_device = device;
        m_rssi = rssi;
    }

    // Wrapper ensures name will have default string value of unknown.
    public String getName(){
        String friendlyName = "Unknown";
        if(m_device.getName() != null)
        {
            friendlyName = m_device.getName();
        }
        return friendlyName;
    }

    public String getAddress(){
        return m_device.getAddress();
    }

    // Wrapper ensures rssi value can be taken with the bluetooth device
    public int getRssi(){
        return m_rssi;
    }


    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        System.out.println("RUN!");
        if(obj instanceof CustomBluetoothDeviceWrapper)
        {
            CustomBluetoothDeviceWrapper temp = (CustomBluetoothDeviceWrapper) obj;
            if(this.getAddress() == temp.getAddress())
                System.out.println("DUPLICATE WAS FOUND!");
                return true;
        }
        else{
            System.out.println("NO DUPLICATE WAS FOUND");
            return false;
        }
    }

    /*
    @Override
    public int hashCode (){
        return (this.m_device.hashCode());
    }
     */


}
