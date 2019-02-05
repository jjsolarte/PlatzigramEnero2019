package co.jeisonsolarte.aplicandomaterialdesign.login.presenter;

import co.jeisonsolarte.aplicandomaterialdesign.login.interactor.LoginInteractor;
import co.jeisonsolarte.aplicandomaterialdesign.login.interactor.LoginInteractorInterface;
import co.jeisonsolarte.aplicandomaterialdesign.login.view.LoginInterface;

public class LoginPresenter implements LoginPresenterInterface {

    private LoginInterface loginInterface;
    private LoginInteractorInterface loginInteractorInterface;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        loginInteractorInterface=new LoginInteractor(this);
    }



    @Override
    public void singIn(String username, String password) {
        loginInterface.disableInputs();
        loginInterface.showProgressBar();
        loginInteractorInterface.singIn(username,password);
    }

    @Override
    public void loginSucess() {
        loginInterface.goHome();
    }

    @Override
    public void loginError(String error) {
        loginInterface.enableInputs();
        loginInterface.hideProgressBar();
        loginInterface.loginError(error);
    }

}
