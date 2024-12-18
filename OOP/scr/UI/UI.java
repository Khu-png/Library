package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class UI extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Bắt đầu với màn hình đăng nhập
            primaryStage.setTitle("Ứng dụng Quản Lý");
            primaryStage.setScene(new Login(primaryStage));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
