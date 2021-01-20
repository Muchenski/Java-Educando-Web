package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}

	@FXML
	public void onMenuItemDepartment() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemAbout() {
		loadView("/gui/About.fxml", x -> {
		});
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

	}

	// Método para carregar outras Views, reutilizando o <Menubar> da View
	// Principal:
	// synchronized -> garante que o método será executado sem ser interrompido
	// levando em conta que a aplicação desktop é multithread.
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			// Guardando a <Vbox> da cena atual.
			VBox newVbox = loader.load();

			// Capturando a cena principal:
			Scene mainScene = Main.getMainScene();

			// Primeiro pegamos o elemento raiz da cena principal.
			// Agora devemos fazer um casting para o nosso elemento raiz que estamos
			// utilizando(<ScrollPane>).
			// pois o getRoot() retorna um ObjectProperty<Parent>, que é a superclasse de
			// diversos nodos.
			// Depois do primeiro casting, pegamos o conteúdo todo da nossa raiz, e
			// filtramos o elemento(nodo) que desejamos através de um novo casting.
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			// Capturando o primeiro filho da mainVbox (<MenuBar>) que desejamos
			// reaproveitar.
			Node mainMenu = mainVbox.getChildren().get(0);

			// limpando o conteúdo que não desejamos, da <Vbox> da cena principal.
			mainVbox.getChildren().clear();
			// Readicionando o menu que desejamos reaproveitar.
			mainVbox.getChildren().add(mainMenu);
			// Adicionando o conteúdo da <Vbox> da cena atual.
			mainVbox.getChildren().addAll(newVbox.getChildren());

			// Ativando o consumer
			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

}
