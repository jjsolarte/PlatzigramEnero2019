package co.jeisonsolarte.aplicandomaterialdesign.login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import co.jeisonsolarte.aplicandomaterialdesign.login.presenter.LoginPresenterInterface;

public class LoginRepository implements LoginRepositoryInterface {

    LoginPresenterInterface presenterInterface;

    public LoginRepository(LoginPresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
    }

    @Override
    public void singIn(final String username, String password, final Activity activity, FirebaseAuth mAuth) {
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                FirebaseUser user=task.getResult().getUser();

                if (task.isSuccessful()){
                    Log.w("LoginRepository","Logeado");
                    SharedPreferences preferences=activity.getSharedPreferences("USER", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("user",user.getEmail());
                    editor.commit();
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
