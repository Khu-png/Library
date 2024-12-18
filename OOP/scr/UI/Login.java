package UI;

import DB.DBconnect;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends Scene {

    private DBconnect dbConnect;

    public Login(Stage primaryStage) {
        super(new VBox(20), 800, 800);
        dbConnect = new DBconnect();

        Label lblUsername = new Label("Tài khoản:");
        TextField txtUsername = new TextField();
        Label lblPassword = new Label("Mật khẩu:");
        PasswordField txtPassword = new PasswordField();
        Button btnLogin = new Button("Đăng nhập");

        VBox root = (VBox) getRoot();
        root.getChildren().addAll(lblUsername, txtUsername, lblPassword, txtPassword, btnLogin);
        root.setAlignment(Pos.CENTER);

        setBtnStyle(btnLogin);

        btnLogin.setOnAction(e -> {
            if (authenticate(txtUsername.getText(), txtPassword.getText())) {
                primaryStage.setScene(new Menu(primaryStage));
            } else {
                Label lblError = new Label("Sai tài khoản hoặc mật khẩu. Vui lòng thử lại.");
                root.getChildren().add(lblError);
            }
        });
    }

    private boolean authenticate(String username, String password) {
        String query = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        try (Connection conn = DBconnect.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void setBtnStyle(Button btn) {
        btn.setPrefSize(300, 80);
        btn.setStyle(
                "-fx-background-color: linear-gradient(#87CEEB, white);" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.6), 10, 0, 2, 2);" +
                        "-fx-text-fill: black;" +
                        "-fx-font-size: 18px;" +
                        "-fx-padding: 10 20;"
        );
    }
}
