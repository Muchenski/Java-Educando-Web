package gui;

import java.io.IOException;
import java.net.URL;
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
import model.services.DepartmentService;

public class DepartmentListController implements Initializable, DataChangeListener {

	private DepartmentService service;

	@FXML
	private TableColumn<Department, Department> tableColumnEdit;

	@FXML
	private TableColumn<Department, Department> tableColumnRemove;

	@FXML
	private TableView<Department> tableViewDepartment;

	// TableColumn<Entidade, TipoDoAtributo>
	@FXML
	private TableColumn<Department, Integer> tableColumId;

	@FXML
	private TableColumn<Department, String> tableColumName;

	@FXML
	private Button buttonNew;

	@FXML
	private ObservableList<Department> obsList;

	@FXML
	public void onBtnNewAction(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		Department department = new Department();
		createDialogForm(department, "/gui/DepartmentForm.fxml", parentStage);
	}

	// Injeção de dependência manual.
	public void setDepartmentService(DepartmentService service) {
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
		tableColumId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		tableColumName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));

		// Obtendo o palco da cena principal.
		Stage stage = (Stage) Main.getMainScene().getWindow();

		// Setando a altura da table para acompanhar a altura do palco.
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
	}

	private <T> void createDialogForm(Department department, String absoluteName, Stage parentStage) {
		try {
			// Carregando o novo palco para cadastro
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();

			DepartmentFormController controller = loader.getController();
			controller.setDepartment(department);
			controller.setDepartmentService(new DepartmentService());
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
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	public void updateTableView() {
		// Caso não tenha ocorrido a injeção de dependência manual.
		if (service == null) {
			throw new IllegalStateException("Service was null!");
		}

		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tableViewDepartment.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	private void initEditButtons() {
		tableColumnEdit.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEdit.setCellFactory(param -> new TableCell<Department, Department>() {
			private final Button button = new Button("edit");

			@Override
			protected void updateItem(Department obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogForm(obj, "/gui/DepartmentForm.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initRemoveButtons() {
		tableColumnRemove.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnRemove.setCellFactory(param -> new TableCell<Department, Department>() {
			private final Button button = new Button("remove");

			@Override
			protected void updateItem(Department obj, boolean empty) {
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

	private void removeEntity(Department department) {
		Optional<ButtonType> btn = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");
		if (btn.get() == ButtonType.OK) {
			if (service == null) {
				throw new IllegalStateException("Service was null!");
			}
			try {
				service.deleteById(department.getId());
				updateTableView();
			} catch (DbIntegrityException e) {
				Alerts.showAlert("Error removeing object", null, e.getMessage(), AlertType.ERROR);
			} catch (DbException e) {
				Alerts.showAlert("Error removeing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}

}
