package com.example.root.deatit13;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by root on 05.11.16.
 */

public class RegisterFragment extends Fragment implements View.OnClickListener {

    private TextView tv_login;
    private TextView btn_ett_reg;
    private TextView btn_reg;
    private EditText et_name;
    private EditText et_email;
    private EditText et_password;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        stdFragment(view);
        return view;
    }


    public void stdFragment(View view) {


        tv_login = (TextView) view.findViewById(R.id.tv_login);
        tv_login.setOnClickListener(this);
        btn_ett_reg = (Button) view.findViewById(R.id.btn_ettermiReg);
        btn_ett_reg.setOnClickListener(this);
        btn_reg = (Button) view.findViewById(R.id.btn_reg);
        btn_reg.setOnClickListener(this);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_password = (EditText) view.findViewById(R.id.et_password);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_login:
                goToLogin();
                break;
            case R.id.btn_ettermiReg:
                goToEttermiReg();
                break;
            case R.id.btn_reg:
                Register();
                break;


        }
    }

    private void goToLogin() {


        Fragment login = new LoginFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, login);
        ft.commit();
    }

    private void goToEttermiReg() {
        Fragment ettermiReg = new RegisterFragmentEttermi();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_frame, ettermiReg);
        ft.commit();


    }

    private void Register() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API requestInterface = retrofit.create(API.class);


        User user = new User();

        user.setName(et_name.getText().toString());
        user.setEmail(et_email.getText().toString());
        user.setPassword(et_password.getText().toString());
        if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            Toast.makeText(getActivity(), "Töltse ki az összes mezőt!", Toast.LENGTH_SHORT).show();

        } else {

            ServerRequest request = new ServerRequest();
            request.setOperation(Constants.REGISTER_OPERATION);
            request.setUser(user);

            Call<ServerResponse> response = requestInterface.operation(request);

            response.enqueue(new Callback<ServerResponse>() {
                                 @Override
                                 public void onResponse(Call<ServerResponse> call, retrofit2.Response<ServerResponse> response) {

                                     ServerResponse resp = response.body();
                                     Toast.makeText(getActivity(), resp.getMessage(), Toast.LENGTH_LONG).show();


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
