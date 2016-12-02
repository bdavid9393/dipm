package com.example.root.deatit13;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 05.11.16.
 */

public class LoginFragment extends Fragment implements View.OnClickListener {

    private View tv_register;
    private View btn_login;

    private EditText et_email;
    private EditText et_password;

    private ProgressBar progress;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login,container,false);

        stdFragment(view);
        return view;
    }


    public void stdFragment(View view){


        tv_register = (TextView)view.findViewById(R.id.tv_register);
        btn_login =(Button)view.findViewById((R.id.btn_login));

        tv_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);

        et_email = (EditText) view.findViewById(R.id.et_email);
        et_password = (EditText) view.findViewById(R.id.et_password);

        progress=(ProgressBar) view.findViewById(R.id.progress);

    }





    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_register:
                goToRegister();
                break;
            case R.id.btn_login:
                Login();
                break;



        }
    }

    private void goToRegister(){


        Fragment register = new RegisterFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame,register);
        ft.commit();
    }
    private void Login() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API requestInterface = retrofit.create(API.class);


        final User user = new User();
        user.setEmail(et_email.getText().toString());
        user.setPassword(et_password.getText().toString());

        if (user.getPassword().isEmpty() || user.getEmail().isEmpty() ) {
            Toast.makeText(getActivity(), "Töltse ki az összes mezőt!", Toast.LENGTH_SHORT).show();

        } else {

            progress.setVisibility(View.VISIBLE);

            ServerRequest request = new ServerRequest();
            request.setOperation(Constants.LOGIN_OPERATION);
            request.setUser(user);

            Call<ServerResponse> response = requestInterface.operation(request);

            response.enqueue(new Callback<ServerResponse>() {
                                 @Override
                                 public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {
                                     progress.setVisibility(View.INVISIBLE);
                                     ServerResponse resp = response.body();
                                     Toast.makeText(getActivity(), resp.getMessage(), Toast.LENGTH_LONG).show();


                                     if(resp.getResult().equalsIgnoreCase(Constants.SUCCESS)) {
                                         Intent intent = new Intent(LoginFragment.this.getActivity(), LoggedIn.class);
                                         intent.putExtra("user", resp.getUser());

                                         startActivity(intent);
                                     }


                                 }

                                 @Override
                                 public void onFailure(Call<ServerResponse> call, Throwable t) {


                                     Toast.makeText(getActivity(), "HIBA", Toast.LENGTH_LONG).show();


                                 }

                             }
            );

        }
    }


}
