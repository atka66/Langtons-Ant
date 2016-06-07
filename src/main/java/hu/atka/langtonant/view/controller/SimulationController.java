package hu.atka.langtonant.view.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import hu.atka.langtonant.controller.Rule;
import hu.atka.langtonant.controller.RuleSet;
import hu.atka.langtonant.controller.Simulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SimulationController implements Initializable {

	@FXML
	Canvas canvasSim;
	@FXML
	Slider sliderSpeed;
	@FXML
	Label labelSpeed;
	@FXML
	Button buttonStart;
	@FXML
	Button buttonStop;
	@FXML
	Button buttonAddLeft;
	@FXML
	Button buttonAddRight;
	@FXML
	Button buttonDelete;
	@FXML
	ListView<Rule> listViewRuleSet;

	Timeline timeline;
	int speed;
	GraphicsContext gcSimulation;
	Simulation simulation;

	@FXML
	private void handleButtonStart(ActionEvent event) {
		simulation = new Simulation(400, new RuleSet(listViewRuleSet.getItems().stream().collect(Collectors.toList())));
		clearAll();
		timeline.play();
	}
	
	@FXML
	private void handleButtonStop(ActionEvent event) {
		timeline.stop();
	}

	@FXML
	private void handleButtonAddLeft(ActionEvent event) {
		listViewRuleSet.getItems().add(new Rule(false));
	}

	@FXML
	private void handleButtonAddRight(ActionEvent event) {
		listViewRuleSet.getItems().add(new Rule(true));
	}

	@FXML
	private void handleButtonDelete(ActionEvent event) {
		listViewRuleSet.getItems().remove(listViewRuleSet.getSelectionModel().getSelectedItem());
	}

	private void clearAll() {
		gcSimulation.setFill(Color.WHITE);
		gcSimulation.fillRect(0, 0, 400, 400);
	}

	private Color getColorFromBlock(int block) {
		if (block < Constants.COLOR_RULES.length) {
			return Constants.COLOR_RULES[block];
		}
		return Constants.COLOR_RULE_DEFAULT;
	}

	private void renderMap() {
		for (int i = 0; i < simulation.getMap().length; i++) {
			for (int j = 0; j < simulation.getMap()[i].length; j++) {
				if (simulation.getMap()[i][j] != 0) {
					gcSimulation.setFill(getColorFromBlock(simulation.getMap()[i][j]));
					gcSimulation.fillRect(j, i, 1, 1);
				}
			}
		}
		gcSimulation.setFill(Color.BLACK);
		gcSimulation.fillRect(simulation.getAnt().getX(), simulation.getAnt().getY(), 1, 1);
	}

	private void renderAnt() {
		gcSimulation.setFill(Color.BLACK);
		gcSimulation.fillRect(simulation.getAnt().getX(), simulation.getAnt().getY(), 1, 1);
	}

	private void render() {
		clearAll();
		renderMap();
		renderAnt();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sliderSpeed.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				speed = new_val.intValue();
				labelSpeed.setText("Speed: " + speed);
			}
		});

		speed = 1;
		labelSpeed.setText("Speed: " + speed);
		gcSimulation = canvasSim.getGraphicsContext2D();
		clearAll();
		timeline = new Timeline(new KeyFrame(Duration.millis(10), ae -> {
			for (int i = 0; i < speed; i++) {
				try {
					simulation.tick();
				} catch (Exception e) {
					timeline.stop();
				}
			}
			render();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
	}

}
