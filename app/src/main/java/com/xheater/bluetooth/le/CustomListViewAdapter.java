package com.xheater.bluetooth.le;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CustomListViewAdapter extends ArrayAdapter {
    List<CustomBluetoothDeviceWrapper> unsortedList;
    List<CustomBluetoothDeviceWrapper> m_list;
    Set<CustomBluetoothDeviceWrapper> sort = new HashSet<CustomBluetoothDeviceWrapper>();

    public CustomListViewAdapter(@NonNull Context context, int layoutId,
                                 List<CustomBluetoothDeviceWrapper> underLyingList) {
        super(context, layoutId, underLyingList);
        m_list = underLyingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);

        CustomBluetoothDeviceWrapper device = getItem(position);
        TextView customText1 = customView.findViewById(R.id.text1);
        TextView customText2 = customView.findViewById(R.id.text2);
        TextView customText3 = customView.findViewById(R.id.text3);

        customText1.setText(device.getName());
        customText2.setText(device.getAddress());
        customText3.setText(Integer.toString(device.getRssi()));

        return customView;
    }



    public void add(@Nullable CustomBluetoothDeviceWrapper device) {
        // Need to wipe list on starting new scan


        // System.out.println("m_list.isEmpty: " + m_list.isEmpty() );
        // System.out.println( "!(m_list.isEmpty): " + !(m_list.isEmpty()));
        // System.out.println(device.getName());


        String device1 = "";
        String device2 = "";


        if(!(m_list.isEmpty()) && device.getName().contains("XHeater")){
            device1 = new String(m_list.get(0).getAddress());
            device2 = new String(device.getAddress());

            System.out.println("DEVICE: " + device1);
            System.out.println("DEVICE: " + device2);
        }

        if(device.getName().contains("XHeater") && m_list.isEmpty() ){
            m_list.add(0,device);
        }
        else if(device.getName().contains("XHeater") && !(device1.equals(device2)) && !(m_list.isEmpty())){
            // System.out.println("FLAG 2 : " + m_list.get(0).getAddress() != device.getAddress());
            System.out.println("ADDITIONAL UNIQUE XHEATERS ADDED.");
            m_list.add(0,device);
        }
        else{
            System.out.println("NO DEVICES ADDED THIS RUN.");
        }

        // Uncomment this out if you want other BLE devices to display in ScanActivity
        // m_list.add(device);

    }


    @Nullable
    @Override
    public CustomBluetoothDeviceWrapper getItem(int position) {
        return m_list.get(position);
    }
}
