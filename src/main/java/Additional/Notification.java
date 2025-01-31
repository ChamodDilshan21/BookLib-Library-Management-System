package Additional;

import javafx.scene.control.Alert;

public class Notification {
    public static void showNotification(String msg) {
        //Showing the notification after adding the customer
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null); // No header
        alert.setContentText(msg);

        // Show the alert and wait for user response
        alert.showAndWait();
    }

    public static void showNotification(Exception ex) {
        //Showing the notification after adding the customer
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null); // No header
        alert.setContentText(ex.getLocalizedMessage());

        // Show the alert and wait for user response
        alert.showAndWait();
    }
}
