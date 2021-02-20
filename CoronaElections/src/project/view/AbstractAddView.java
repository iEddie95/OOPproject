package project.view;

import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class AbstractAddView extends VBox { // Abstract view for adding citizen/candidate
	protected ArrayList<ViewListenable> allListeners;

	protected Label lblAddCitizen;
	protected HBox hbEnterName;
	protected Label lblEnterName;
	protected TextField tfEnterName;

	protected HBox hbBirthDate;
	protected Label lblBirthDate;
	protected DatePicker dpBirthDate;

	protected HBox hbID;
	protected Label lblID;
	protected TextField tfID;

	protected HBox hbCitizenCity;
	protected Label lblCitizenCity;
	protected TextField tfCitizenCity;

	protected HBox hbIsQuarantine;
	protected Label lblIsQuarantine;
	protected ToggleGroup tglIsQuarantine;
	protected RadioButton rbYes;
	protected RadioButton rbNo;

	protected HBox hbDaysSick;
	protected Label lblDaysSick;
	protected TextField tfDaysSick;
	protected Button btnClear;
	protected Button btnAddCitizen;
	protected HBox hbAddCitizenBTN;

	public AbstractAddView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		btnClear = new Button("Clear");
		btnClear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				tfCitizenCity.clear();
				tfDaysSick.clear();
				tfEnterName.clear();
				tfID.clear();
				dpBirthDate.getEditor().clear();
				if (rbYes.isSelected() || rbNo.isSelected())
					tglIsQuarantine.getSelectedToggle().setSelected(false);

			}
		});
		setSpacing(15);
		setVisible(false);

		lblAddCitizen = new Label();
		lblAddCitizen.setFont(Font.font("arial", FontWeight.BOLD, 16));
		lblAddCitizen.setTextFill(Color.BLUEVIOLET);
		hbEnterName = new HBox(10);
		lblEnterName = new Label("Enter full name:");
		tfEnterName = new TextField();
		hbEnterName.getChildren().addAll(lblEnterName, tfEnterName);

		hbBirthDate = new HBox(10);
		dpBirthDate = new DatePicker();
		lblBirthDate = new Label("Birth date:");
		hbBirthDate.getChildren().addAll(lblBirthDate, dpBirthDate);

		hbID = new HBox(10);
		lblID = new Label("ID:");
		tfID = new TextField();
		hbID.getChildren().addAll(lblID, tfID);

		hbCitizenCity = new HBox(10);
		tfCitizenCity = new TextField();
		lblCitizenCity = new Label("Home city:");
		hbCitizenCity.getChildren().addAll(lblCitizenCity, tfCitizenCity);

		hbIsQuarantine = new HBox(10);
		lblIsQuarantine = new Label("Is in Quarantine?");
		tglIsQuarantine = new ToggleGroup();
		rbYes = new RadioButton("Yes");
		rbNo = new RadioButton("No");
		rbYes.setToggleGroup(tglIsQuarantine);
		rbNo.setToggleGroup(tglIsQuarantine);
		hbIsQuarantine.getChildren().addAll(lblIsQuarantine, rbYes, rbNo);

		hbDaysSick = new HBox(10);
		lblDaysSick = new Label("Days of being sick: ");
		tfDaysSick = new TextField();
		tfDaysSick.setDisable(true);
		hbDaysSick.getChildren().addAll(lblDaysSick, tfDaysSick);

		rbYes.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tfDaysSick.setDisable(!(rbYes.isSelected()));

			}
		});
		rbNo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				tfDaysSick.setDisable((rbNo.isSelected()));

			}
		});

		hbAddCitizenBTN = new HBox(20);
		btnAddCitizen = new Button();
		hbAddCitizenBTN.getChildren().addAll(btnAddCitizen, btnClear);
		getChildren().addAll(lblAddCitizen, hbEnterName, hbBirthDate, hbID, hbCitizenCity, hbIsQuarantine, hbDaysSick);
		spMain.getChildren().add(this);

	}

	public void showException(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(msg);
		alert.showAndWait();

	}
}
