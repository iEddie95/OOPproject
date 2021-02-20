package project.view;

import java.time.LocalDate;
import java.util.ArrayList;

import project.Citizen;
import project.CoronaSick;
import project.RegularCitizen;
import project.Soldier;
import project.SoldierCoronaSick;
import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;

public class AddCitizenView extends AbstractAddView {
	private Citizen temp = null;

	public AddCitizenView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		super(spMain, allListeners);
		lblAddCitizen.setText("Add New Citizen");
		btnAddCitizen.setText("Add Citizen");
		btnAddCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addNewCitizenToUI();
			}
		});
		getChildren().add(hbAddCitizenBTN);
	}

	public void addNewCitizenToUI() {

		if ((tfEnterName.getText().isEmpty() || (dpBirthDate.getValue() == null) || tfCitizenCity.getText().isEmpty()
				|| tfID.getText().isEmpty()) || (rbYes.isSelected() && tfDaysSick.getText().isEmpty())) {
			Alert alertError = new Alert(AlertType.ERROR, "All fields are required");
			alertError.showAndWait();
		} else {
			try {
				LocalDate ldElectionsDate = allListeners.get(0).viewAskForElectionsLocalDate();
				if (rbNo.isSelected()) {
					if (ldElectionsDate.getYear() - dpBirthDate.getValue().getYear() <= 21) {

						temp = new Soldier(tfEnterName.getText(), dpBirthDate.getValue().getYear(),
								dpBirthDate.getValue().getMonthValue(), dpBirthDate.getValue().getDayOfMonth(),
								Integer.parseInt(tfID.getText()), tfCitizenCity.getText());

					} else {
						temp = new RegularCitizen(tfEnterName.getText(), dpBirthDate.getValue().getYear(),
								dpBirthDate.getValue().getMonthValue(), dpBirthDate.getValue().getDayOfMonth(),
								Integer.parseInt(tfID.getText()), tfCitizenCity.getText());
					}
				} else if (rbYes.isSelected()) {
					if (ldElectionsDate.getYear() - dpBirthDate.getValue().getYear() <= 21) {
						temp = new SoldierCoronaSick(tfEnterName.getText(), dpBirthDate.getValue().getYear(),
								dpBirthDate.getValue().getMonthValue(), dpBirthDate.getValue().getDayOfMonth(),
								Integer.parseInt(tfID.getText()), tfCitizenCity.getText(),
								Integer.parseInt(tfDaysSick.getText()));
					} else {
						temp = new CoronaSick(tfEnterName.getText(), dpBirthDate.getValue().getYear(),
								dpBirthDate.getValue().getMonthValue(), dpBirthDate.getValue().getDayOfMonth(),
								Integer.parseInt(tfID.getText()), tfCitizenCity.getText(),
								Integer.parseInt(tfDaysSick.getText()));
					}
				}
				for (ViewListenable viewListenable : allListeners) {
					viewListenable.viewAddedCitizen(temp);
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
		}

	}

}
