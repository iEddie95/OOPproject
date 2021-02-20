package project.view;

import java.util.ArrayList;

import project.Citizen;
import project.listeners.ViewListenable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.StackPane;

public class ShowCitizenListView extends AbstarctListView {

	private Accordion accoCitizenList;

	public ShowCitizenListView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		super(spMain, allListeners);
		lblList.setText("Citizen List");
		accoCitizenList = new Accordion();
		vbList.getChildren().add(accoCitizenList);

	}

	public void addCitizenToView(Citizen citizen) {
		accoCitizenList.getPanes().add(new TitledPane(citizen.getName(), new Label(citizen.toString())));
	}
	
	
}
