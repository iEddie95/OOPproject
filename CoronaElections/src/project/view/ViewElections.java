package project.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import project.BallotBox;
import project.Citizen;
import project.listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ViewElections implements ElectionsViewable {
	private ArrayList<ViewListenable> allListeners;

	private BorderPane bpMain;

	private StackView stackView;
	private Label lblWelcome;

	private VBox vbMenu;
	private HBox hbBtnHome;
	private HBox hbMenuButton;

	private HBox hbTop;
	private Label lblElectionsDate;
	private VBox vbWelcome;
	private Button btnHome;

	private MenuButton menuButton;
	private MenuItem miBallotBox;
	private MenuItem miCitizen;
	private MenuItem miParty;
	private MenuItem miCandidate;
	private MenuItem miPrintBox;
	private MenuItem miPrintCitizen;
	private MenuItem miPrintParty;
	private MenuItem miVote;
	private MenuItem miResults;
	private MenuItem miExit;
	private Node lastNode;

	public ViewElections(Stage primaryStage) {
		allListeners = new ArrayList<ViewListenable>();
		bpMain = new BorderPane();

		bpMain.setPadding(new Insets(10));
		stackView = new StackView(allListeners);
		vbMenu = new VBox(15);
		vbMenu.setPadding(new Insets(10));
		hbTop = new HBox(150);

		vbWelcome = new VBox(5);
		vbWelcome.setAlignment(Pos.TOP_CENTER);
		lblWelcome = new Label("Welcome to the Elections");
		lblWelcome.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		lblElectionsDate = new Label("Elections Date: ");
		lblElectionsDate.setFont(Font.font("Verdana", FontWeight.SEMI_BOLD, FontPosture.ITALIC, 12));
		vbWelcome.getChildren().addAll(lblWelcome, lblElectionsDate);
		hbBtnHome = new HBox(10);
		hbBtnHome.setAlignment(Pos.BASELINE_LEFT);
		btnHome = new Button("Back");
		hbBtnHome.getChildren().add(btnHome);
		btnHome.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				unSetLastNodeUsed();
			}
		});

		hbTop.getChildren().addAll(hbBtnHome, vbWelcome);
		bpMain.setTop(hbTop);

		Label lbMenu = new Label("Please Choose from menu");

		miBallotBox = new MenuItem("1- Add Ballot Box");
		miBallotBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!(allListeners.get(0).viewAskIfElctionsDone())) {
					unSetLastNodeUsed();
					stackView.getAddBallotView().setVisible(true);
					lastNode = stackView.getAddBallotView();
				} else {
					showMsgElectionsOver();
				}
			}
		});
		miCitizen = new MenuItem("2- Add Citizen");
		miCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!(allListeners.get(0).viewAskIfElctionsDone())) {
					unSetLastNodeUsed();
					stackView.getAddCitizenView().setVisible(true);
					lastNode = stackView.getAddCitizenView();
				} else {
					showMsgElectionsOver();
				}
			}
		});
		miParty = new MenuItem("3- Add Party");

		miParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!(allListeners.get(0).viewAskIfElctionsDone())) {
					unSetLastNodeUsed();
					stackView.getAddPartyView().setVisible(true);
					lastNode = stackView.getAddPartyView();
				} else {
					showMsgElectionsOver();
				}
			}
		});
		miCandidate = new MenuItem("4- Add Candidate");
		miCandidate.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (!(allListeners.get(0).viewAskIfElctionsDone())) {
					unSetLastNodeUsed();
					stackView.getAddCandidateView().setVisible(true);
					lastNode = stackView.getAddCandidateView();
				} else {
					showMsgElectionsOver();
				}

			}
		});
		miPrintBox = new MenuItem("5- Show all Ballot boxes");
		miPrintBox.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				unSetLastNodeUsed();
				showBallotBoxList();
				stackView.getBallotListView().getSpList().setVisible(true);
				lastNode = stackView.getBallotListView().getSpList();
			}
		});
		miPrintCitizen = new MenuItem("6- Show all Citizens");
		miPrintCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				unSetLastNodeUsed();

				showCitizenList();
				lastNode = stackView.getCitizetList().getSpList();

			}
		});

		miPrintParty = new MenuItem("7- Show all parties");
		miPrintParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				unSetLastNodeUsed();
				showPartyList();
				stackView.getPartyListView().getSpList().setVisible(true);
				lastNode = stackView.getPartyListView().getSpList();

			}
		});

		miVote = new MenuItem("8- Start the elections");
		miVote.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				unSetLastNodeUsed();
				if (!(allListeners.get(0).viewAskIfElctionsDone())) {
					stackView.getElectionsRoundView().setVisible(true);
					menuButton.setDisable(true);
					btnHome.setDisable(true);
					showStartElctions();
				} else {
					showMsgElectionsOver();
				}
			}
		});
		miResults = new MenuItem("9- Show results");

		miResults.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (allListeners.get(0).viewAskIfElctionsDone()) {
					unSetLastNodeUsed();
					stackView.getRestulsView().getSpList().setVisible(true);
					lastNode = stackView.getRestulsView().getSpList();
					showElectionsResults();
				} else {
					showMsgElectionsNotOver();
				}
			}
		});
		miExit = new MenuItem("10- Exit");

		miExit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(Alert.AlertType.NONE,
						"THANK YOU FOR THE ELECTIONS, SEE YOU IN 3 MONTHS, MAY THE ODDS BE EVER IN YOUR FAVOR!");
				alert.setTitle("Goodbye :)");
				alert.getButtonTypes().add(ButtonType.FINISH);
				alert.showAndWait();
				primaryStage.close();

			}
		});

		menuButton = new MenuButton("Options", null, miBallotBox, miCitizen, miParty, miCandidate, miPrintBox,
				miPrintCitizen, miPrintParty, miVote, miResults, miExit);
		menuButton.setDisable(true);
		hbMenuButton = new HBox(menuButton);
		hbMenuButton.setSpacing(10);

		vbMenu.getChildren().addAll(lbMenu, hbMenuButton);

		bpMain.setLeft(vbMenu);
		bpMain.setCenter(stackView);

		Scene scene = new Scene(bpMain, 600, 500);
		primaryStage.setTitle("Corona Elections system");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	@Override
	public void registerListener(ViewListenable l) {
		allListeners.add(l);
	}

	public void unSetLastNodeUsed() {
		if (lastNode != null) {
			stackView.setVisibaleLastNode(lastNode);
		}
	}

	@Override
	public void showBallotBoxList() {
		stackView.getBallotListView().showBallotBoxList();
	}

	@Override
	public void showPartyList() {
		stackView.getPartyListView().showPartyList();

	}

	@Override
	public void showAddedBallotBox(BallotBox<?> ballotBox) {
	
	}

	@Override
	public void showAddedCitizen(Citizen citizen) {
		stackView.getCitizetList().addCitizenToView(citizen);

	}

	@Override
	public void showAddedCandidate(Citizen candidate) {
		stackView.getCitizetList().addCitizenToView(candidate);

	}

	@Override
	public void showAddedParty(String partyName) {
		stackView.getAddCandidateView().addParty(partyName);

	}

	@Override
	public void showCitizenException(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setContentText(msg);
		alert.showAndWait();

	}

	@Override
	public void showStartElctions() {
		stackView.getElectionsRoundView().showStartElctions();

	}

	public LocalDate getElectionsDate() {
		return allListeners.get(0).viewAskForElectionsLocalDate();
	}

	@Override
	public void showUpdateElectionsDate(LocalDate date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		lblElectionsDate.setText("Elections date:" + date.format(format).toString());
		menuButton.setDisable(false);

	}

	@Override
	public void showElectionsResults() {
		stackView.getRestulsView().showElectionsResults();

	}

	@Override
	public void showCitizenList() {
		stackView.getCitizetList().getSpList().setVisible(true);
	}

	public void showMsgElectionsOver() {
		Alert alertDone = new Alert(AlertType.INFORMATION, "ELECTIONS ARE OVER, can't preferom this action");
		alertDone.showAndWait();
	}

	@Override
	public void showElectionsAreDone() {
		Alert done = new Alert(AlertType.INFORMATION);
		done.setContentText("Elections done");
		System.out.println("done");
		done.showAndWait();
		menuButton.setDisable(false);
		btnHome.setDisable(false);
	}

	public void showMsgElectionsNotOver() {
		Alert alertNotDone = new Alert(AlertType.ERROR, "ELECTIONS ARE NOT DONE YET, can't preferom this action");
		alertNotDone.showAndWait();
	}

	@Override
	public void addPartyFailToUI() {
		Alert alertDone = new Alert(AlertType.ERROR, "Party already exist");
		alertDone.showAndWait();

	}

}
