package co.jeisonsolarte.aplicandomaterialdesign.login.interactor;

import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;

import co.jeisonsolarte.aplicandomaterialdesign.login.presenter.LoginPresenterInterface;
import co.jeisonsolarte.aplicandomaterialdesign.login.repository.LoginRepository;
import co.jeisonsolarte.aplicandomaterialdesign.login.repository.LoginRepositoryInterface;

public class LoginInteractor implements LoginInteractorInterface {

    private LoginPresenterInterface presenterInterface;
    private LoginRepositoryInterface repositoryInterface;

    public LoginInteractor(LoginPresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
        repositoryInterface=new LoginRepository(presenterInterface);
    }

    @Override
    public void singIn(String username, String password, Activity activity, FirebaseAuth mAuth) {
        repositoryInterface.singIn(username,password, activity, mAuth);
    }
}
