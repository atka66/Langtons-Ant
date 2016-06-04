package hu.atka.langtonant.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import hu.atka.langtonant.controller.RuleSet;
import hu.atka.langtonant.controller.Simulation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SimulationController implements Initializable {

	@FXML
	Canvas canvasSim;
	@FXML
	Button buttonStart;

	Timeline timeline;
	GraphicsContext gcSimulation;
	Simulation simulation;

	@FXML
	private void handleButtonStart(ActionEvent event) {
		clearAll();
		timeline.play();
	}

	private void clearAll() {
		gcSimulation.setFill(Color.WHITE);
		gcSimulation.fillRect(0, 0, 400, 400);
	}

	private Color getColorFromBlock(int block) {
		switch (block) {
		case 0:
			return Color.WHITE;
		case 1:
			return Color.RED;
		case 2:
			return Color.GREEN;
		case 3:
			return Color.BLUE;
		case 4:
			return Color.YELLOW;
		case 5:
			return Color.PURPLE;
		case 6:
			return Color.CYAN;
		case 7:
			return Color.DARKRED;
		case 8:
			return Color.DARKGREEN;
		case 9:
			return Color.DARKBLUE;
		default:
			return Color.WHITE;
		}
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
		simulation = new Simulation(300, new RuleSet(true, false));
		gcSimulation = canvasSim.getGraphicsContext2D();
		clearAll();
		timeline = new Timeline(new KeyFrame(Duration.millis(10), ae -> {
			simulation.tick();
			render();
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
	}

}
