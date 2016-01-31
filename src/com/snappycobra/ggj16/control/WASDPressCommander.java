package com.snappycobra.ggj16.control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import com.snappycobra.ggj16.model.Cursor;
import com.snappycobra.ggj16.model.Shrine;
import com.snappycobra.ggj16.model.WorldMap;

public class WASDPressCommander extends CursorCommander{

	public WASDPressCommander(Cursor cursor) {
		super(cursor);
		this.addCommand(KeyEvent.VK_A, new AbstractAction() {
			private static final long serialVersionUID = 331430491826847158L;
			@Override
			public void actionPerformed(ActionEvent e) {
				getCursor().setMovingLeft(true);
				WorldMap worldMap = getCursor().getWorldMap();
				Shrine shrine = worldMap.getShrine();
				if (shrine.existSI()) {
					shrine.removeSI();
				}
			}
		});
		
		this.addCommand(KeyEvent.VK_D, new AbstractAction() {
			private static final long serialVersionUID = 331430491826847158L;
			@Override
			public void actionPerformed(ActionEvent e) {
				getCursor().setMovingRight(true);
				WorldMap worldMap = getCursor().getWorldMap();
				Shrine shrine = worldMap.getShrine();
				if (shrine.existSI()) {
					shrine.removeSI();
				}
			}
		});
		
		this.addCommand(KeyEvent.VK_E, new AbstractAction() {
			private static final long serialVersionUID = 331430491826847158L;
			@Override
			public void actionPerformed(ActionEvent e) {
				WorldMap worldMap = getCursor().getWorldMap();
				Shrine shrine = worldMap.getShrine();
				if (shrine.existSI()) {
					shrine.getSacrificeInterface().confirm();
				} else {
					getCursor().click();
				}
			}
		});
		
		this.addCommand(KeyEvent.VK_W, new AbstractAction() {
			private static final long serialVersionUID = 331430491826847158L;
			@Override
			public void actionPerformed(ActionEvent e) {
				WorldMap worldMap = getCursor().getWorldMap();
				Shrine shrine = worldMap.getShrine();
				if (shrine.existSI()) {
					shrine.getSacrificeInterface().up();
				}
			}
		});
		
		this.addCommand(KeyEvent.VK_S, new AbstractAction() {
			private static final long serialVersionUID = 331430491826847158L;
			@Override
			public void actionPerformed(ActionEvent e) {
				WorldMap worldMap = getCursor().getWorldMap();
				Shrine shrine = worldMap.getShrine();
				if (shrine.existSI()) {
					shrine.getSacrificeInterface().down();
				}
			}
		});
	}

}
