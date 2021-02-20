package project.view;

import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.scene.layout.StackPane;

public class ShowPartyView extends AbstarctListView {

	public ShowPartyView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		super(spMain, allListeners);
		lblList.setText("Party List");

	}

	public void showPartyList() {
		for (ViewListenable viewListenable : allListeners) {
			txtList.setText(viewListenable.viewAskForPartyList());
		}
	}

}
