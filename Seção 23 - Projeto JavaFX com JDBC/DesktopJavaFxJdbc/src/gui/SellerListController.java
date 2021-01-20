package gui;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import db.DbException;
import db.DbIntegrityException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.entities.Seller;
import model.services.DepartmentService;
import model.services.SellerService;

public class SellerListController implements Initializable, DataChangeListener {

	private SellerService service;

	@FXML
	private TableColumn<Seller, Seller> tableColumnEdit;

	@FXML
	private TableColumn<Seller, Seller> tableColumnRemove;

	@FXML
	private TableView<Seller> tableViewSeller;

	// TableColumn<Entidade, TipoDoAtributo>
	@FXML
	private TableColumn<Seller, Integer> tableColumId;

	@FXML
	private TableColumn<Seller, String> tableColumName;

	@FXML
	private TableColumn<Seller, String> tableColumEmail;

	@FXML
	private TableColumn<Seller, Date> tableColumBirthDate;

	@FXML
	private TableColumn<Seller, Double> tableColumBaseSalary;

	@FXML
	private TableColumn<Seller, Department> tableColumDepartment;

	@FXML
	private Button buttonNew;

	@FXML
	private ObservableList<Seller> obsList;

	@FXML
	public void onBtnNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Seller seller = new Seller();
		createDialogForm(seller, "/gui/SellerForm.fxml", parentStage);
	}

	// Injeção de dependência manual.
	public void setSellerService(SellerService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {

		// Setando o que cada coluna representará, da entidade.
		// .setCellValueFactory(new PropertyValueFactory<Entidade, TipoDoAtributo>("nome
		// do atributo"));
		tableColumId.setCellValueFactory(new PropertyValueFactory<Seller, Integer>("id"));
		tableColumName.setCellValueFactory(new PropertyValueFactory<Seller, String>("name"));
		tableColumEmail.setCellValueFactory(new PropertyValueFactory<Seller, String>("email"));
		tableColumBirthDate.setCellValueFactory(new PropertyValueFactory<Seller, Date>("birthDate"));
		Utils.formatTableColumnDate(tableColumBirthDate, "dd/MM/yyyy");
		tableColumBaseSalary.setCellValueFactory(new PropertyValueFactory<Seller, Double>("baseSalary"));
		Utils.formatTableColumnDouble(tableColumBaseSalary, 2);

		tableColumDepartment.setCellValueFactory(new PropertyValueFactory<Seller, Department>("department"));

		// Obtendo o palco da cena principal.
		Stage stage = (Stage) Main.getMainScene().getWindow();

		// Setando a altura da table para acompanhar a altura do palco.
		tableViewSeller.prefHeightProperty().bind(stage.heightProperty());
	}

	private <T> void createDialogForm(Seller seller, String absoluteName, Stage parentStage) {

		try {
			// Carregando o novo palco para cadastro
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			SellerFormController controller = loader.getController();
			controller.setSeller(seller);
			controller.setServices(new SellerService(), new DepartmentService());
			controller.loadAssociatedObjects();
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			// Fixando a largura/altura da janela
			dialogStage.setResizable(false);
			// Setando o palco que estava aberto antes do novo.
			dialogStage.initOwner(parentStage);
			// Fazendo com que nós não consigamos ir para outras janelas enquanto
			// esta atual não ser finalizada.
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}

	}

	public void updateTableView() {
		// Caso não tenha ocorrido a injeção de dependência manual.
		if (service == null) {
			throw new IllegalStateException("Service was null!");
		}

		List<Seller> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewSeller.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEdit.setCellFactory(param -> new TableCell<Seller, Seller>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Seller obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> createDialogForm(obj, "/gui/SellerForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemove.setCellFactory(param -> new TableCell<Seller, Seller>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Seller obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(Seller seller) {
		Optional<ButtonType> btn = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");
		if (btn.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null!");
			}
			try {
				service.deleteById(seller.getId());
				updateTableView();
			} catch (DbIntegrityException e) {
				Alerts.showAlert("Error removeing object", null, e.getMessage(), AlertType.ERROR);
			} catch (DbException e) {
				Alerts.showAlert("Error removeing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}

}
