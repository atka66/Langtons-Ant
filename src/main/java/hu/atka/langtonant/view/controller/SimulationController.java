package hu.atka.langtonant.view.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import hu.atka.langtonant.controller.RuleSet;
import hu.atka.langtonant.controller.Simulation;
import hu.atka.langtonant.model.entity.RuleVo;
import hu.atka.langtonant.model.service.RuleService;
import hu.atka.langtonant.model.service.RuleServiceImpl;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
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
	Button buttonLoad;
	@FXML
	TextArea textAreaRuleset;

	private Timeline timeline;
	private int speed;
	private GraphicsContext gcSimulation;
	private Simulation simulation;

	@FXML
	private void handleButtonStart(ActionEvent event) {
		String text = textAreaRuleset.getText();
		if (checkIfValidRuleset(text)) {
			EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("RuleDB");
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			try {
				RuleService ruleService = new RuleServiceImpl(entityManager);
				entityManager.getTransaction().begin();
				ruleService.add(new RuleVo(text));
				entityManager.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				entityManager.close();
				entityManagerFactory.close();
			}

			simulation = new Simulation(400, new RuleSet(text));
			clearAll();
			timeline.play();
		}
	}

	@FXML
	private void handleButtonStop(ActionEvent event) {
		timeline.stop();
	}

	@FXML
	private void handleButtonLoad(ActionEvent event) {
		// TODO
	}

	private boolean checkIfValidRuleset(String string) {
		return string.toUpperCase().matches("[LR]+");
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
