package com.snappycobra.ggj16;

import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.Rectangle;

import com.snappycobra.ggj16.control.ArrowCursorController;
import com.snappycobra.ggj16.control.CursorController;
import com.snappycobra.ggj16.control.WASDCursorController;
import com.snappycobra.ggj16.graphics.GodPainter;
import com.snappycobra.ggj16.model.Cursor;
import com.snappycobra.ggj16.model.GameModel;
import com.snappycobra.ggj16.model.JobLess;
import com.snappycobra.ggj16.model.Player;
import com.snappycobra.ggj16.model.Unit;
import com.snappycobra.motor.demo.DemoGame;
import com.snappycobra.motor.demo.DemoPainter;
import com.snappycobra.motor.demo.WindowCloseController;
import com.snappycobra.motor.gui.Window;

public class Main {
	public static void main(String[] args){
		GameModel model = new GameModel(2);
		GodPainter painter = new GodPainter(model);
		Window window = new Window(800,600,painter);
		List<Player> players = model.getPlayerList();

		CursorController controller1 = new ArrowCursorController(players.get(0).getCursor());
		CursorController controller2 = new WASDCursorController(players.get(1).getCursor());
		window.addKeyListener(controller1);
		window.addKeyListener(controller2);
		window.addKeyListener(new WindowCloseController());
		window.setResizable(true);
		painter.showFPS(false);
		window.setTitle("SnappyRoba");
		window.setVisible(true);
	}
}
