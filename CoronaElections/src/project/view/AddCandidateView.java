package project.view;

import java.util.ArrayList;

import project.Candidate;
import project.CandidateSick;
import project.Citizen;
import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class AddCandidateView extends AbstractAddView {

	private HBox hbCandidatePartySelect;
	private Label lblCandidateParty;
	private ComboBox<String> cbCandidateParty;

	public AddCandidateView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		super(spMain, allListeners);
		lblAddCitizen.setText("Add New Candidate");
		btnAddCitizen.setText("Add Candidate");
		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addNewCitizenToUI();
			}
		});
		hbCandidatePartySelect = new HBox(10);
		lblCandidateParty = new Label("Choose party");
		cbCandidateParty = new ComboBox<String>();
		hbCandidatePartySelect.getChildren().addAll(lblCandidateParty, cbCandidateParty);
		getChildren().add(hbCandidatePartySelect);
		getChildren().add(hbAddCitizenBTN);

	}

	public void addNewCitizenToUI() {
		Citizen temp = null;
		if ((tfEnterName.getText().isEmpty() || (dpBirthDate.getValue() == null) || tfCitizenCity.getText().isEmpty()
				|| tfID.getText().isEmpty()) || (rbYes.isSelected() && tfDaysSick.getText().isEmpty())) {
			Alert alertError = new Alert(AlertType.ERROR, "All fields are required");
			alertError.showAndWait();
		} else {
			try {
				if (rbNo.isSelected()) {
					temp = new Candidate(tfEnterName.getText(), dpBirthDate.getValue().getYear(),
							dpBirthDate.getValue().getMonthValue(), dpBirthDate.getValue().getDayOfMonth(),
							Integer.parseInt(tfID.getText()), tfCitizenCity.getText());

				} else if (rbYes.isSelected()) {

					temp = new CandidateSick(tfEnterName.getText(), dpBirthDate.getValue().getYear(),
							dpBirthDate.getValue().getMonthValue(), dpBirthDate.getValue().getDayOfMonth(),
							Integer.parseInt(tfID.getText()), tfCitizenCity.getText(),
							Integer.parseInt(tfDaysSick.getText()));
				}

				for (ViewListenable viewListenable : allListeners) {
					viewListenable.viewAddedCandidate(temp, cbCandidateParty.getSelectionModel().getSelectedIndex());
				}

			} catch (Exception e) {
				showException(e.getMessage());
			}
			setVisible(false);
			tfCitizenCity.clear();
			tfDaysSick.clear();
			tfEnterName.clear();
			tfID.clear();
			dpBirthDate.getEditor().clear();
			tglIsQuarantine.getSelectedToggle().setSelected(false);
			cbCandidateParty.getSelectionModel().clearSelection();
		}

	}

	public void addParty(String partyName) {
		cbCandidateParty.getItems().add(partyName);

	}

}
