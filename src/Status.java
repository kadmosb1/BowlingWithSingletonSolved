import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.Observable;
import java.util.Observer;

class EventHandlerToggle implements EventHandler<ActionEvent> {

	private Lane lane;
	private Status status;

	public EventHandlerToggle (Lane lane, Status status) {
		this.lane = lane;
		this.status = status;
	}

	@Override
	public void handle (ActionEvent actionEvent) {
		this.lane.setOccupied (!this.lane.isOccupied ());
		this.status.draw ();
	}
}

class EventHandlerClose implements EventHandler<WindowEvent> {

	private Status status;

	public EventHandlerClose (Status status) {
		this.status = status;
	}

	@Override
	public void handle (WindowEvent windowEvent) {

		for (Lane lane : Bowling.getInstance ().lanes) {
			lane.deleteObserver (this.status);
		}
	}
}

public class Status extends Stage implements Observer {

	private Pane rootPane;

	public Status () {

		this.rootPane = new Pane();

		for (Lane lane : Bowling.getInstance ().lanes) {
			lane.addObserver (this);
		}

		setTitle("Status");
		this.rootPane.setMinSize (200, 200);
		draw ();
		Scene scene = new Scene (rootPane);
		setScene (scene);
		setOnCloseRequest (new EventHandlerClose (this));
		show ();
	}

	public void draw() {

		this.rootPane.getChildren ().clear ();
		VBox vBox = new VBox();
		this.rootPane.getChildren().add(vBox);

		for (Lane lane : Bowling.getInstance ().lanes) {
			HBox hBox = new HBox ();
			vBox.getChildren ().add (hBox);
			hBox.getChildren ().add (new Label ("Lane " + lane.getNumber () + "\t"));
			hBox.getChildren ().add (new Label (lane.isOccupied () ? "occupied\t" : "free\t"));
			Button button = new Button ("Toggle");
			button.setOnAction (new EventHandlerToggle (lane, this));
			hBox.getChildren ().add (button);
		}
	}

	@Override
	public void update (Observable o, Object arg) {
		draw ();
	}
}