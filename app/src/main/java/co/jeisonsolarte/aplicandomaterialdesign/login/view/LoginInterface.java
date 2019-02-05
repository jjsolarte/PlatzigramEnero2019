package co.jeisonsolarte.aplicandomaterialdesign.login.view;

public interface LoginInterface {

    void goCreateAcount();
    void goPlatziPage();
    void goHome();

    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);

}
