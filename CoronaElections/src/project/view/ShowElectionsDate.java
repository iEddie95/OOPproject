package project.view;

import java.time.LocalDate;
import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ShowElectionsDate {
	protected ArrayList<ViewListenable> allListeners;

	private VBox vbDate;
	

	public ShowElectionsDate(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		vbDate = new VBox(20);
		vbDate.setPadding(new Insets(10));
		HBox hbDateWelcome = new HBox(10);
		hbDateWelcome.setAlignment(Pos.TOP_CENTER);
		HBox hbElectionsDate = new HBox(10);
		DatePicker dpElectionsDate = new DatePicker();
		Label lblElectionsDatePick = new Label("Please pick elections date");
		Button btnDate = new Button("Start");
		hbElectionsDate.getChildren().addAll(lblElectionsDatePick, dpElectionsDate);
		btnDate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showUpdateElectionsDate(dpElectionsDate.getValue());
				vbDate.setVisible(false);
			}
		});

		vbDate.getChildren().addAll(hbDateWelcome, hbElectionsDate, btnDate);
		spMain.getChildren().add(vbDate);
	}

	public void showUpdateElectionsDate(LocalDate date) {
		allListeners.get(0).viewUpdateElectionsDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
		
	}

}
