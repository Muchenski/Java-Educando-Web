package gui;

import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.entities.Person;

public class ViewController implements Initializable {

	@FXML
	private ComboBox<Person> comboPerson;

	// ObservableList<T> é um tipo especial de lista
	// que fica associado ao elemento visual.
	private ObservableList<Person> obsPerson;

	@FXML
	private Button btnAll;

	// initialize é o método da Interface funcional Initializable
	// Este método faz com que quando um determinado Controller seja instânciado,
	// as instruções que contidas no método serão realizadas.
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Person> persons = new ArrayList<Person>();
		persons.addAll(Arrays.asList(new Person(1, "Maria", "maria@gmail.com"), new Person(2, "Alex", "alex@gmail.com"),
				new Person(3, "Bob", "bob@gmail.com"), new Person(4, "Henrique", "henrique@gmail.com")));

		obsPerson = FXCollections.observableArrayList(persons);
		comboPerson.setItems(obsPerson);

		// Alterando a forma de visualização dos dados nas células do ComboBox.
		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "Sem valor" : item.getName());
			}
		};
		comboPerson.setCellFactory(factory);
		comboPerson.setButtonCell(factory.call(null));
	}

	public void onComboPersonAction() {
		// Capturando o item selecionado no ComboBox.
		Person person = comboPerson.getSelectionModel().getSelectedItem();
		System.out.println(person);
	}

	public void onBtnAllAction() {
		for (Person person : comboPerson.getItems()) {
			System.out.println(person);
		}
	}
}
