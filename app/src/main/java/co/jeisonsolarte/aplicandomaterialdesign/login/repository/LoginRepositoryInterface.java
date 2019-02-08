package co.jeisonsolarte.aplicandomaterialdesign.login.repository;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginRepositoryInterface {

    void singIn(String username, String password, Activity activity, FirebaseAuth mAuth);

}
