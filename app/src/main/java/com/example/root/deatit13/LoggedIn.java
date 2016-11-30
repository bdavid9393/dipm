package com.example.root.deatit13;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by root on 30.11.16.
 */

public class LoggedIn extends AppCompatActivity {

    private Button btn_logout;
    private TextView tv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_logged_in);

        User user = (User) getIntent().getSerializableExtra("user");

        tv_welcome= (TextView)findViewById(R.id.tv_welcome);
        tv_welcome.setText("Udvozoljuk\n"+user.getEmail());


    }

}
