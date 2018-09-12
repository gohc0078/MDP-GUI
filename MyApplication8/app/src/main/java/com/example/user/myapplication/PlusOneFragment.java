package com.example.user.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PlusOneFragment extends Fragment {
  private Button btn_save;
    private Button btn_back;
   private EditText editText;
   private EditText editText2;
    private String StoredString1;
    private String StoredString2;
SharedPreferences preferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_plus_one,container,false);


       editText = (EditText)v.findViewById(R.id.editText);
       editText2 = (EditText)v.findViewById(R.id.editText2);

       btn_save = (Button)v.findViewById(R.id.button7);
        preferences = getActivity().getSharedPreferences("MyCommandFile", Context.MODE_PRIVATE);
        StoredString1 = preferences.getString("key_cmd1", "");
        StoredString2 = preferences.getString("key_cmd2", "");
        editText.setText(StoredString1);
        editText2.setText(StoredString2);

        btn_save.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //if edittext fields are empty, set a default value to prevent null exception
                if (editText.getText().toString().trim().length() <= 0){
                    editText.setText("Command 1 Text");
                }

                if (editText2.getText().toString().trim().length() <= 0){
                    editText2.setText("Command 2 Text");
                }

                saveStrings();
            }
        });



        btn_back = (Button)v.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Fragment myfm = new main_fragment();
             FragmentManager manager = getFragmentManager();
             FragmentTransaction transaction = manager.beginTransaction();
             transaction.replace(R.id.frag_place,myfm,"A");
             transaction.addToBackStack(null);
             transaction.commit();
            }
        });
        return v;

        // Inflate the layout for this fragment

    }
    public void saveStrings() {
        super.onPause();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("key_cmd1", editText.getText().toString());
        editor.putString("key_cmd2", editText2.getText().toString());
        editor.commit();

        Toast.makeText(getContext().getApplicationContext(), "Commands Saved Successfully", Toast.LENGTH_SHORT).show();
    }








}
