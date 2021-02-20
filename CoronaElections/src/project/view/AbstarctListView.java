package project.view;

import java.util.ArrayList;

import project.listeners.ViewListenable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public abstract class AbstarctListView {
	protected ArrayList<ViewListenable> allListeners;
	protected ScrollPane spList;
	protected VBox vbList;
	protected Label lblList;
	protected Text txtList;


	public AbstarctListView(StackPane spMain, ArrayList<ViewListenable> allListeners) {
		this.allListeners = allListeners;
		spList = new ScrollPane();
		vbList = new VBox(10);
		lblList = new Label();
		txtList = new Text();


		lblList.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		lblList.setAlignment(Pos.TOP_CENTER);

		txtList.wrappingWidthProperty().bind(spList.widthProperty());
		vbList.getChildren().addAll(lblList, txtList);
		spList.setFitToWidth(true);
		spList.setContent(vbList);
		spList.setVisible(false);
		spMain.getChildren().add(spList);

	}

	public ScrollPane getSpList() {
		return spList;
	}

}
