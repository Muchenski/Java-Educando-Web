package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Alerts;
import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ViewController implements Initializable {

	@FXML
	private Button btnSum;
	@FXML
	private TextField txtFirstNumber;
	@FXML
	private TextField txtSecondNumber;
	@FXML
	private Label lblResult;

	public void onbtnSumAction() {

		try {
			double sum = Double.parseDouble(txtFirstNumber.getText()) + Double.parseDouble(txtSecondNumber.getText());
			lblResult.setText(String.format("%.2f", sum));
		} catch (NumberFormatException e) {
			Alerts.showAlert("Invalid value", null, e.getMessage(), AlertType.ERROR);
		}
	}

	// initialize é o método da Interface funcional Initializable
	// Este método faz com que quando um determinado Controller seja instânciado,
	// as instruções que contidas no método serão realizadas.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Constraints.setTextFieldDouble(txtFirstNumber);
		Constraints.setTextFieldDouble(txtSecondNumber);
		Constraints.setTextFieldMaxLength(txtFirstNumber, 8);
		Constraints.setTextFieldMaxLength(txtSecondNumber, 8);
	}
}
