package hu.atka.langtonant.view.controller;

import javafx.scene.paint.Color;

public class Constants {
	public static final Color COLOR_RULES[];
	public static final Color COLOR_RULE_DEFAULT;
	static {
		COLOR_RULES = new Color[] { Color.rgb(255, 0, 0), Color.rgb(0, 255, 0), Color.rgb(0, 0, 255),
				Color.rgb(192, 64, 0), Color.rgb(0, 192, 64), Color.rgb(64, 0, 192), Color.rgb(128, 128, 0),
				Color.rgb(0, 128, 128), Color.rgb(128, 0, 128), Color.rgb(64, 192, 0), Color.rgb(0, 64, 192),
				Color.rgb(192, 0, 64) };
		COLOR_RULE_DEFAULT = Color.WHITE;
	}
}
