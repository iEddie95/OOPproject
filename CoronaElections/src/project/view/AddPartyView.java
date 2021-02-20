package project.view;

import java.util.ArrayList;

import project.Party;
import project.PoliticalViews;
import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddPartyView extends VBox {
	protected ArrayList<ViewListenable> allListeners;

	private HBox hbPartyName;
	private Label lblPartyName;
	private TextField tfPartyName;

	private HBox hbPartyWing;
	private Label lblPartyWing;
	private ComboBox<String> cbPartyWing;

	private HBox hbPartyDate;
	private Label lblPartyDate;
	private DatePicker dpPartyDate;

	private Button btnAddParty;

	public AddPartyView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		setSpacing(20);
		Label lblAddParty = new Label("Add new party");
		lblAddParty.setFont(Font.font("arial", FontWeight.BOLD, 16));
		lblAddParty.setTextFill(Color.BLUEVIOLET);
		hbPartyName = new HBox(10);
		lblPartyName = new Label("Enter party name:");
		tfPartyName = new TextField();
		hbPartyName.getChildren().addAll(lblPartyName, tfPartyName);

		hbPartyWing = new HBox(10);
		lblPartyWing = new Label("Choose political View:");
		cbPartyWing = new ComboBox<String>();
		cbPartyWing.getItems().add("Left");
		cbPartyWing.getItems().add("Middle");
		cbPartyWing.getItems().add("Right");
		hbPartyWing.getChildren().addAll(lblPartyWing, cbPartyWing);

		hbPartyDate = new HBox(10);
		lblPartyDate = new Label("Choose party creation date:");
		dpPartyDate = new DatePicker();
		hbPartyDate.getChildren().addAll(lblPartyDate, dpPartyDate);

		btnAddParty = new Button("Add Party");

		btnAddParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addPartyToUI();

			}

		});

		setVisible(false);
		getChildren().addAll(lblAddParty, hbPartyName, hbPartyWing, hbPartyDate, btnAddParty);

		spMain.getChildren().add(this);
	}

	private void addPartyToUI() {
		Party temp = null;
		if (tfPartyName.getText() != null && cbPartyWing.getValue() != null && dpPartyDate.getValue() != null) {
			String name = tfPartyName.getText();
			int partyWing = cbPartyWing.getSelectionModel().getSelectedIndex();
			int day = dpPartyDate.getValue().getDayOfMonth();
			int month = dpPartyDate.getValue().getMonthValue();
			int year = dpPartyDate.getValue().getYear();
			switch (partyWing) {
			case 0: {
				temp = new Party(name, PoliticalViews.Left.toString(), year, month, day);
				break;
			}
			case 1: {
				temp = new Party(name, PoliticalViews.Middle.toString(), year, month, day);
				break;
			}
			case 2: {
				temp = new Party(name, PoliticalViews.Right.toString(), year, month, day);
				break;
			}
			default:
				break;
			}
			for (ViewListenable viewListenable : allListeners) {
				viewListenable.viewAddedParty(temp);
			}
			tfPartyName.clear();
			cbPartyWing.getSelectionModel().clearSelection();
			dpPartyDate.getEditor().clear();
			setVisible(false);
		}
	}
}
