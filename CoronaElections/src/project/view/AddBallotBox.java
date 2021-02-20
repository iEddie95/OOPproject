package project.view;

import java.util.ArrayList;

import project.BallotBox;
import project.CitizenType;
import project.CoronaSick;
import project.RegularCitizen;
import project.Soldier;
import project.SoldierCoronaSick;
import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddBallotBox extends VBox {
	protected ArrayList<ViewListenable> allListeners;

	protected VBox hbBallotCity;
	protected Label lblNewBallot;
	protected Label lblCity;
	protected TextField tfCity;
	protected VBox hbBallotType;
	protected Label lblType;
	protected ChoiceBox<String> cbBallotBox;
	protected Button btnBallotBoxChoice;

	public AddBallotBox(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		setSpacing(20);
		setPadding(new Insets(10));
		setVisible(false);
		hbBallotCity = new VBox(10);
		lblNewBallot = new Label("Create new ballot box");
		lblNewBallot.setAlignment(Pos.TOP_CENTER);
		lblNewBallot.setFont(Font.font("arial", FontWeight.BOLD, 16));
		lblNewBallot.setTextFill(Color.BLUEVIOLET);
		lblCity = new Label("In which city you want to create ballot box?");
		tfCity = new TextField();
		hbBallotType = new VBox(10);
		lblType = new Label("Which kind of Ballot box you want to create?");
		cbBallotBox = new ChoiceBox<String>();
		btnBallotBoxChoice = new Button("Select");
		btnBallotBoxChoice.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addBallotBoxToUI();

			}
		});

		cbBallotBox.getItems().add("1- Regular");
		cbBallotBox.getItems().add("2- For Corona sick citizens");
		cbBallotBox.getItems().add("3- For soldiers");
		cbBallotBox.getItems().add("4- For Corona sick soldiers");
		hbBallotCity.getChildren().addAll(lblCity, tfCity);
		hbBallotType.getChildren().addAll(lblType, cbBallotBox);

		getChildren().addAll(lblNewBallot, hbBallotCity, hbBallotType, btnBallotBoxChoice);

		spMain.getChildren().add(this);
	}

	public void addBallotBoxToUI() {
		String value = (String) cbBallotBox.getValue();

		if (!(tfCity.getText().isEmpty()) && (value != null)) {
			if (value.equalsIgnoreCase("1- Regular")) {
				for (ViewListenable viewListenable : allListeners) {
					viewListenable.viewAddedRegularBallotBox(
							new BallotBox<RegularCitizen>(tfCity.getText(), CitizenType.Regular));
				}
			}

			else if (value.equalsIgnoreCase("2- For Corona sick citizens")) {
				for (ViewListenable viewListenable : allListeners) {
					viewListenable.viewAddedCoronaSickBallotBox(
							new BallotBox<CoronaSick>(tfCity.getText(), CitizenType.Corona_Sick));
				}
			}

			else if (value.equalsIgnoreCase("3- For soldiers")) {
				for (ViewListenable viewListenable : allListeners) {
					viewListenable
							.viewAddedSoldierBallotBox(new BallotBox<Soldier>(tfCity.getText(), CitizenType.Soldiers));
				}
			}

			else if (value.equalsIgnoreCase("4- For Corona sick soldiers")) {
				for (ViewListenable viewListenable : allListeners) {
					viewListenable.viewAddedSoldierCoronaSickBallotBox(
							new BallotBox<SoldierCoronaSick>(tfCity.getText(), CitizenType.Sick_Soldiers));
				}
			}

			setVisible(false);
			tfCity.setText("");
			cbBallotBox.getSelectionModel().clearSelection();

		} else {
			Alert alertError = new Alert(AlertType.ERROR, "All fields are required");
			alertError.showAndWait();

		}

	}

}
