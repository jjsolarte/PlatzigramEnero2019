package co.jeisonsolarte.aplicandomaterialdesign.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginInteractorInterface {

    void singIn(String username, String password, Activity activity, FirebaseAuth mAuth);

}
