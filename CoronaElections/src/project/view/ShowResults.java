package project.view;

import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.scene.layout.StackPane;

public class ShowResults extends AbstarctListView {

	public ShowResults(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		super(spMain, allListeners);
		lblList.setText("Total Results");

	}

	public void showElectionsResults() {
		for (ViewListenable viewListenable : allListeners) {
			txtList.setText(viewListenable.viewAskForElectionsBallotBoxResults()
					+ viewListenable.viewAskForElectionsPartyResults());
		}
	}

}
