package project.view;

import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.scene.layout.StackPane;

public class ShowBallotBoxList extends AbstarctListView {

	public ShowBallotBoxList(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		super(spMain, allListeners);
		lblList.setText("BallotBox List");
	}

	public void showBallotBoxList() {
		for (ViewListenable viewListenable : allListeners) {
			txtList.setText(viewListenable.viewAskForBallotBoxList());
		}
	}

}
