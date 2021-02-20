package project.view;

import java.util.ArrayList;

import project.Citizen;
import project.CoronaSickable;
import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ElectionRoundView extends VBox {
	protected ArrayList<ViewListenable> allListeners;

	private VBox vbVote;
	private HBox hbAllVotes;
	private Label lblVoterName;
	private HBox hbVotePartySelect;
	private Label lblVoteParty;
	private ComboBox<String> cbVoteParty;
	private Button btnNextVote;
	private HBox hbYesNoVote;
	private Label lblVoteYesNo;
	private ToggleGroup tglYesNoVote;
	private RadioButton rbYesVote;
	private RadioButton rbNoVote;
	private VBox vbVotePartyList;
	private Text txtVotePartyList;
	private int index = 0;
	private int partyIndex = 0;

	public ElectionRoundView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		setSpacing(10);
		vbVote = new VBox(10);
		hbAllVotes = new HBox(10);
		Label lblVoting = new Label("Elections has started... please make your vote");
		lblVoting.setAlignment(Pos.TOP_CENTER);
		lblVoting.setFont(Font.font(null, FontWeight.BOLD, 16));
		lblVoterName = new Label("Voter Name:");
		cbVoteParty = new ComboBox<String>();
		hbVotePartySelect = new HBox(10);
		lblVoteParty = new Label("Choose party to vote");
		hbVotePartySelect.getChildren().addAll(lblVoteParty, cbVoteParty);
		btnNextVote = new Button("Next Voter");
		hbYesNoVote = new HBox(10);
		lblVoteYesNo = new Label("Would you like to vote?");
		tglYesNoVote = new ToggleGroup();
		rbYesVote = new RadioButton("Yes");
		rbNoVote = new RadioButton("No");
		rbYesVote.setToggleGroup(tglYesNoVote);
		rbYesVote.setTextFill(Color.GREEN);
		rbYesVote.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		rbNoVote.setToggleGroup(tglYesNoVote);
		rbNoVote.setTextFill(Color.RED);
		rbNoVote.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		hbYesNoVote.getChildren().addAll(lblVoteYesNo, rbYesVote, rbNoVote);
		cbVoteParty.setDisable(true);
		vbVote.getChildren().addAll(lblVoterName, hbYesNoVote, hbVotePartySelect, btnNextVote);
		vbVotePartyList = new VBox(10);
		txtVotePartyList = new Text();
		vbVotePartyList.getChildren().addAll(txtVotePartyList);
		hbAllVotes.getChildren().addAll(vbVote);
		getChildren().addAll(lblVoting, hbAllVotes, vbVotePartyList);
		setVisible(false);

		rbYesVote.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) { //When clicked-if citizen is corona sick and not protected->next voter
				cbVoteParty.setDisable(false);
				if (allListeners.get(0).viewAskForCitizen(index) instanceof CoronaSickable) {
					if (!((CoronaSickable) allListeners.get(0).viewAskForCitizen(index)).isWearingProtection()) {
						Alert alertProtection = new Alert(AlertType.WARNING);
						alertProtection.setContentText(allListeners.get(0).viewAskForCitizen(index).getName()
								+ " Voter is Corona sick and not wearing protection, can't vote");
						alertProtection.showAndWait();
						showNextVoter();
						tglYesNoVote.getSelectedToggle().setSelected(false);
					}
				}
			}
		});

		rbNoVote.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				cbVoteParty.setDisable(true);
			}
		});

		spMain.getChildren().add(this);
	}

	public void showStartElctions() {
		Alert alertStartElections = new Alert(AlertType.INFORMATION,
				"The time has come, to vote for your life! Don't fail it up!\nMy citizens, start your engines and may the best candidate win");
		alertStartElections.showAndWait();
		txtVotePartyList.setText(allListeners.get(0).viewAskForPartiesForElections());

		partyListComboBox();

		Citizen temp = allListeners.get(0).viewAskForCitizen(index);
		lblVoterName.setText("Voter Name: " + temp.getName());

		allListeners.get(0).viewAskPreapareForElections();

		btnNextVote.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Boolean fCitizenSick = false;
				if ((rbNoVote.isSelected())
						|| (rbYesVote.isSelected() && !(cbVoteParty.getSelectionModel().isEmpty()))) {
					if (allListeners.get(0).viewAskForCitizen(index) instanceof CoronaSickable) {
						if (!((CoronaSickable) allListeners.get(0).viewAskForCitizen(index)).isWearingProtection()) {
							Alert altProtectionErr = new Alert(AlertType.WARNING);
							altProtectionErr.setContentText(allListeners.get(0).viewAskForCitizen(index).getName()
									+ " Voter is Corona sick and not wearing protection, can't vote");
							altProtectionErr.showAndWait();
							System.out.println(allListeners.get(0).viewAskForCitizen(index).getName()
									+ " Voter is Corona sick and not wearing protection, can't vote");
							fCitizenSick = true;

						}
					}
					if (rbYesVote.isSelected() && !(fCitizenSick)) {
						partyIndex = cbVoteParty.getSelectionModel().getSelectedIndex();
						sendResult(index, partyIndex);
					} else if (rbNoVote.isSelected()) {
						Alert altNotVoting = new Alert(AlertType.INFORMATION);
						altNotVoting.setContentText("Voter " + allListeners.get(0).viewAskForCitizen(index).getName()
								+ " decided not to vote\n");
						System.out.println("Voter " + allListeners.get(0).viewAskForCitizen(index).getName()
								+ " decided not to vote\n");
						altNotVoting.showAndWait();
					}

					showNextVoter();
					tglYesNoVote.getSelectedToggle().setSelected(false);
					cbVoteParty.getSelectionModel().clearSelection();
				}
			}
		});

	}

	private void showNextVoter() { //pick next voter
		index++;
		if (allListeners.get(0).viewAskForCitizen(index) != null) {
			lblVoterName.setText("Voter Name: " + allListeners.get(0).viewAskForCitizen(index).getName());

		} else { // no more voters
			allListeners.get(0).viewUpdateElectionsDone(true);
			setVisible(false);

		}

	}

	public void partyListComboBox() { //organize combo box with party names
		for (int i = 0; i < allListeners.get(0).viewAskForPartyCounter(); i++) {
			cbVoteParty.getItems().add(allListeners.get(0).viewAskForPartyName(i));
		}
	}

	public void sendResult(int citizeIndex, int partyIndex) { //update results
		allListeners.get(0).viewSendVoteSelections(index, partyIndex);
	}
}
