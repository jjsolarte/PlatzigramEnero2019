package co.jeisonsolarte.aplicandomaterialdesign.login.presenter;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginPresenterInterface {

    void singIn(String username, String password, Activity activity, FirebaseAuth mAuth);
    void loginSucess();
    void loginError(String error);

}
