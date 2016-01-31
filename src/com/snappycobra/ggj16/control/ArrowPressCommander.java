package com.snappycobra.ggj16.control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;

import com.snappycobra.ggj16.model.Cursor;
import com.snappycobra.ggj16.model.Shrine;
import com.snappycobra.ggj16.model.WorldMap;

public class ArrowPressCommander extends CursorCommander{
	public ArrowPressCommander(Cursor cursor) {
		super(cursor);
		this.addCommand(KeyEvent.VK_LEFT, new AbstractAction() {
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
		
		this.addCommand(KeyEvent.VK_RIGHT, new AbstractAction() {
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
		
		this.addCommand(KeyEvent.VK_CONTROL, new AbstractAction() {
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
	}
}
