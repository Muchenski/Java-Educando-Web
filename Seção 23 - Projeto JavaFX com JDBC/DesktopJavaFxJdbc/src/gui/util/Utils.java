package gui.util;

import javafx.event.ActionEvent;

import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

	// Método que retorna o palco de onde o evento foi disparado.
	public static Stage currentStage(ActionEvent event) {
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

	public static Integer tryParseToInt(String string) {
		try {
			return Integer.parseInt(string);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
