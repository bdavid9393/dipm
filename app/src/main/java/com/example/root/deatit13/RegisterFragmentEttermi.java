package com.example.root.deatit13;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by root on 05.11.16.
 */

public class RegisterFragmentEttermi extends Fragment implements View.OnClickListener{

    private TextView tv_login;
    private TextView btn_fogy_reg;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ettermi_reg,container,false);

        stdFragment(view);
        return view;
    }


    public void stdFragment(View view){


        tv_login = (TextView)view.findViewById(R.id.tv_login);
        btn_fogy_reg=(Button)view.findViewById(R.id.btn_fogyReg);



        tv_login.setOnClickListener(this);
        btn_fogy_reg.setOnClickListener(this);

    }





    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_login:
                goToLogin();
                break;
            case R.id.btn_fogyReg:
                gotToFogyReg();
                break;



        }
    }

    private void goToLogin(){


        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,login);
        ft.commit();
    }
    private void gotToFogyReg(){
        Fragment fogyReg = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,fogyReg);
        ft.commit();


    }

}
