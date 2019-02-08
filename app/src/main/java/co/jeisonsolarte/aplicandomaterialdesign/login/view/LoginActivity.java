package co.jeisonsolarte.aplicandomaterialdesign.login.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.login.presenter.LoginPresenter;
import co.jeisonsolarte.aplicandomaterialdesign.login.presenter.LoginPresenterInterface;
import co.jeisonsolarte.aplicandomaterialdesign.views.ContainerActivity;
import co.jeisonsolarte.aplicandomaterialdesign.views.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginInterface{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private EditText edtUser;
    private EditText edtPassword;
    private Button btnLogin;
    private ProgressBar progressBar;

    LoginPresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser=findViewById(R.id.login_username);
        edtPassword=findViewById(R.id.login_pass);
        btnLogin=findViewById(R.id.login_login);
        progressBar=findViewById(R.id.login_progress);

        hideProgressBar();
        presenterInterface=new LoginPresenter(this);

        mAuth=FirebaseAuth.getInstance();

        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
                if (firebaseUser!=null){
                    Log.w("LoginActivity","User logeado "+firebaseUser.getEmail());
                }else {
                    Log.w("LoginActivity","User no logeado ");
                }
            }
        };


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUser.equals("")&&edtPassword.equals("")) {
                    Toast toast=Toast.makeText(getApplicationContext(),"Por favor llenar los campos",Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    singIn(edtUser,edtPassword);
                    //presenterInterface.singIn(edtUser.getText().toString(),edtPassword.getText().toString());
                }
            }
        });
    }

    public void singIn(EditText edtUser,EditText edtPassword){
        presenterInterface.singIn(edtUser.getText().toString(),edtPassword.getText().toString(),this,mAuth);
    }

    @Override
    public void goCreateAcount() {
        Intent intent=new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goPlatziPage() {
        String url = "http://www.platzi.com";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void goHome() {
        Intent intent=new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }

    @Override
    public void enableInputs() {
        edtUser.setEnabled(true);
        edtPassword.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    @Override
    public void disableInputs() {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, "Ocurrió algo "+error, Toast.LENGTH_SHORT).show();
    }

    public void onRegister(View view){
        Intent intent=new Intent(this,CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(authStateListener);
    }
}
