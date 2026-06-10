import uts.edu.co.vista.LoginView;
import uts.edu.co.controlador.LoginController;

public class Main {
    public static void main(String[] args) {
        LoginView login = new LoginView();
        new LoginController(login);
    }
}