package com.myapp.testapp.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.myapp.testapp.R;
import com.myapp.testapp.utils.ConnectionDetector;

import org.jivesoftware.smackx.iqregister.AccountManager;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edName,edEmail,edMobile,edPassword,edConfirmPassword;
    private Button btnSubmit;
    private String name,email,password,confirmPwd,mobile;
    private View parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
    }

    private void initViews() {
        edName = (EditText) findViewById(R.id.edName);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edMobile = (EditText) findViewById(R.id.edMobile);
        edPassword = (EditText) findViewById(R.id.edPassword);
        edConfirmPassword = (EditText) findViewById(R.id.edConfirmPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        parentView = findViewById(R.id.llParentLayout);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSubmit:
                boolean vaild = isFormValid();
                if(vaild){
                    if(new ConnectionDetector(this).isConnectedToInternet()){
                        createAccount();
                    }else Snackbar.make(parentView,getResources().getString(R.string.no_internet_msg),Snackbar.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void createAccount() {
//        AccountManager accountManager = AccountManager.getInstance()
    }

    private boolean isFormValid() {
        name = edName.getText().toString().trim();
        email = edEmail.getText().toString().trim();
        mobile = edMobile.getText().toString().trim();
        password = edPassword.getText().toString().trim();
        confirmPwd = edConfirmPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Snackbar.make(parentView,getResources().getString(R.string.enter_name_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(email)){
            Snackbar.make(parentView,getResources().getString(R.string.enter_email_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Snackbar.make(parentView,getResources().getString(R.string.enter_valid_email_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(mobile)){
            Snackbar.make(parentView,getResources().getString(R.string.enter_mobile_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if(mobile.length() !=10 ){
            Snackbar.make(parentView,getResources().getString(R.string.enter_valid_mobile_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            Snackbar.make(parentView,getResources().getString(R.string.enter_pwd_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }

        if(!password.equals(confirmPwd)){
            Snackbar.make(parentView,getResources().getString(R.string.pwd_valid_msg),Snackbar.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
