package co.jeisonsolarte.aplicandomaterialdesign.login.repository;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import co.jeisonsolarte.aplicandomaterialdesign.login.presenter.LoginPresenterInterface;

public class LoginRepository implements LoginRepositoryInterface {

    LoginPresenterInterface presenterInterface;

    public LoginRepository(LoginPresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
    }

    @Override
    public void singIn(String username, String password, Activity activity, FirebaseAuth mAuth) {
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.w("LoginRepository","Logeado");
                }else {
                    Log.w("LoginRepository","Error");
                }
            }
        });
        if (true){
            presenterInterface.loginSucess();
        }else {
            presenterInterface.loginError("Algo salió mal");
        }
    }
}
