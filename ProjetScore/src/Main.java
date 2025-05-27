package src;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.SwingUtilities;

import controller.ListeController;
import tout.ListeTT;
import view.ClassementView;
import view.ListeView;

public class Main {
    public static void main(String[] args) {
        try {
           Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/FMJ", "root", "");
           ListeTT dao = new ListeTT(conn);
           ListeController controller = new ListeController(dao);

        SwingUtilities.invokeLater(() -> {
            new ListeView(controller).setVisible(true); // Passez le contrôleur
        });
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
