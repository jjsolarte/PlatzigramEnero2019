package co.jeisonsolarte.aplicandomaterialdesign.login.presenter;

public interface LoginPresenterInterface {

    void singIn(String username, String password);
    void loginSucess();
    void loginError(String error);

}
