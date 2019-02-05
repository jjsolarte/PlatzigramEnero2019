package co.jeisonsolarte.aplicandomaterialdesign.login.repository;

import co.jeisonsolarte.aplicandomaterialdesign.login.presenter.LoginPresenterInterface;

public class LoginRepository implements LoginRepositoryInterface {

    LoginPresenterInterface presenterInterface;

    public LoginRepository(LoginPresenterInterface presenterInterface) {
        this.presenterInterface = presenterInterface;
    }

    @Override
    public void singIn(String username, String password) {
        if (true){
            presenterInterface.loginSucess();
        }else {
            presenterInterface.loginError("Algo salió mal");
        }
    }
}
