package com.example.user.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.example.user.myapplication.Constants.ARENA_COLUMN_COUNT;
import static com.example.user.myapplication.Constants.ARENA_ROW_COUNT;


public class main_fragment extends Fragment {

    public Button btn_config;
    public Button btn_f1;
    private String StoredString1;
    private String StoredString2;
    private String value1;
    private String value2;
    private TextView txt_status;
    SharedPreferences preferences;

    //  Arena view
   // private ArenaView arenaView;
    private mySurface mySurfaceview;


    //  Arena Frame
    private RelativeLayout arenaFrame;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main_fragment,container,false);
       Button btn_con = (Button)v.findViewById(R.id.button6);
       btn_con.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               ft.replace(R.id.frag_place,new PlusOneFragment());
               ft.commit();
           }
       });


        preferences = getActivity().getSharedPreferences("MyCommandFile",Context.MODE_PRIVATE);
         value1 = preferences.getString("key_cmd1","");
         value2 = preferences.getString("key_cmd2","");
        txt_status = (TextView)v.findViewById(R.id.txt_status);
    Button btn_f1 = (Button)v.findViewById(R.id.btn_f1);
        Button btn_f2 = (Button)v.findViewById(R.id.button);
    btn_f1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                txt_status.setText(value1.toString());
        }
    });
        btn_f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_status.setText(value2.toString());
            }
        });



        //  Create Arena
        arenaFrame = v.findViewById(R.id.arena_frame);




       mySurfaceview = new mySurface(getContext());
       arenaFrame.addView(mySurfaceview);
        // Inflate the layout for this fragment
        return v;


    }






}
