package com.snappycobra.ggj16.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.dyn4j.dynamics.Body;
import org.dyn4j.geometry.AABB;
import org.dyn4j.geometry.Vector2;

import com.snappycobra.ggj16.mastermind.Combination;
import com.snappycobra.ggj16.mastermind.Mastermind;
import com.snappycobra.ggj16.mastermind.OldCombination;
import com.snappycobra.ggj16.model.Base;
import com.snappycobra.ggj16.model.Building;
import com.snappycobra.ggj16.model.Cursor;
import com.snappycobra.ggj16.model.GameModel;
import com.snappycobra.ggj16.model.Gear;
import com.snappycobra.ggj16.model.Oil;
import com.snappycobra.ggj16.model.Player;
import com.snappycobra.ggj16.model.Resource;
import com.snappycobra.ggj16.model.ResourceAmount;
import com.snappycobra.ggj16.model.ResourcePoint;
import com.snappycobra.ggj16.model.Shrine;
import com.snappycobra.ggj16.model.Silverfish;
import com.snappycobra.ggj16.model.Unit;
import com.snappycobra.motor.graphics.AbstractPainter;
import com.snappycobra.motor.graphics.Frame;
import com.snappycobra.motor.graphics.ImageManager;
import com.snappycobra.motor.graphics.Sprite;
import com.snappycobra.motor.maps.GameObject;
import com.snappycobra.motor.maps.GameObjectGrabber;
import com.snappycobra.motor.maps.Map;
import com.snappycobra.motor.maps.MapContainer;
import com.snappycobra.motor.timing.Timer;

public class GodPainter extends AbstractPainter{
	private List<Player> players;
	private int mapWidth, mapHeight;
	private BufferedImage mapBuffer;
	private BufferedImage parBuffer;
	private BufferedImage air, path, foreground, scrap1, scrap2, shrineImg, arrowUp, arrowDown, sacrefice;
	private BufferedImage mindBoard, pastMoveBoard, lampB, lampG, lampR, lampY, selArrow;
	private BufferedImage cloud, pointer1, pointer2, iconGear, iconOil, iconUranium, iconSilver, iconCross;
	private Sprite godSprite = new Sprite(new Frame("data/images/Creatures/God.png", 10));
	private Font UIFont;
	private float alfa;
	
	public GodPainter(GameModel gameModel) {
		super(gameModel);
		this.players = gameModel.getPlayerList();
		Map map = getMap();
		this.mapWidth = map.getWidth()*map.getTileWidth();
		this.mapHeight = map.getHeight()*map.getTileHeight();
		this.mapBuffer = newBuffer(mapWidth, mapHeight, Transparency.TRANSLUCENT);
		this.parBuffer = newBuffer(mapWidth, mapHeight, Transparency.TRANSLUCENT);
		this.air = ImageManager.getImage("data/images/air.png");
		this.path = ImageManager.getImage("data/images/background_Path.png");
		this.foreground = ImageManager.getImage("data/images/voorgrond.png");
		this.scrap1 = ImageManager.getImage("data/images/Background/background_Scrap1.png");
		this.scrap2 = ImageManager.getImage("data/images/Background/background_Scrap2.png");
		this.sacrefice = ImageManager.getImage("data/images/Hud_Sacrifice.png");
		this.shrineImg = ImageManager.getImage("data/images/Shrine.png");
		this.arrowUp = ImageManager.getImage("data/images/Interface_Pijl_up.png");
		this.arrowDown = ImageManager.getImage("data/images/Interface_Pijl_down.png");
		this.mindBoard = ImageManager.getImage("data/images/MasterMind/Hud_Mastermind.png");
		this.pastMoveBoard = ImageManager.getImage("data/images/MasterMind/Hud_Mastermind_Collapse.png");
		this.cloud = ImageManager.getImage("data/images/background_Cloud.png");
		this.pointer1 = ImageManager.getImage("data/images/UI/pijltje.png");
		this.pointer2 = ImageManager.getImage("data/images/UI/pijltje2.png");
		
		this.iconGear = ImageManager.getImage("data/images/UI/Icon_Gear.png");
		this.iconOil = ImageManager.getImage("data/images/UI/Icon_Oil.png");
		this.iconUranium = ImageManager.getImage("data/images/UI/Icon_Uranium.png");
		this.iconSilver = ImageManager.getImage("data/images/UI/Icon_Silver.png");
		this.iconCross = ImageManager.getImage("data/images/UI/Icon_Cross.png");
		this.selArrow = ImageManager.getImage("data/images/UI/Pijl_Heel.png");
		
		this.lampB = ImageManager.getImage("data/images/MasterMind/Lamp_Blue.png");
		this.lampG = ImageManager.getImage("data/images/MasterMind/Lamp_Green.png");
		this.lampR = ImageManager.getImage("data/images/MasterMind/Lamp_Red.png");
		this.lampY = ImageManager.getImage("data/images/MasterMind/Lamp_Yellow.png");
		
		try (InputStream stream = new BufferedInputStream(new FileInputStream("data/fonts/rexlia.ttf"))){
			this.UIFont = Font.createFont(Font.TRUETYPE_FONT, stream);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		this.alfa=0;
	}

	@Override
	protected void drawFrame(Graphics2D g) {
		bufferMap();
		alfa += 0.001*Timer.getPassedTime()/Math.pow(10, 7);
		alfa %= 2*Math.PI;
		this.drawScreens(g);
		
	}
	
	protected void drawScreens(Graphics2D g) {
		int sWidth = this.getWidth();
		int sHeight = this.getHeight();
		int numPlayers = players.size();
		float scaledY = sHeight/(1f*numPlayers)/mapHeight;
		
		int i=0;
		for (Player player : players) {
			Cursor cursor = player.getCursor();
			int posX = (int) (getMap().getWidth()*scaledY-cursor.getPosition()*getMap().getTileWidth()*scaledY);
			
			this.drawTiled(g, air, i*(sHeight/numPlayers), scaledY);
			//g.drawImage(air, 0, i*(sHeight/numPlayers), (int)(air.getWidth()*scaledY), (int)(air.getHeight()*scaledY), null);
			//g.drawImage(air, (int)(air.getWidth()*scaledY), i*(sHeight/numPlayers), (int)(air.getWidth()*scaledY), (int)(air.getHeight()*scaledY), null);
			this.drawLoopMap(g, sWidth/2+posX, i*(sHeight/numPlayers));
			this.drawMasterMind(g, player, i*(sHeight/numPlayers), scaledY*1.2f);
			this.drawStats(g, player, i*(sHeight/numPlayers), scaledY);
			i++;
		}
		g.setColor(new Color(1,.5f,.5f,0.1f));
		g.drawLine(sWidth/2, sHeight, sWidth/2, 0);

		g.setColor(Color.BLACK);
		g.drawLine(0, sHeight/2-1, sWidth, sHeight/2-1);
		int pointWidth = (int)(pointer1.getWidth()*2*scaledY);
		int pointHeight = (int)(pointer1.getHeight()*2*scaledY);
		g.drawImage(pointer2, sWidth/2-pointWidth/2, 0, pointWidth, pointHeight, null);
		g.drawImage(pointer1, sWidth/2-pointWidth/2, sHeight/2-pointHeight, pointWidth, pointHeight, null);
		g.drawImage(pointer2, sWidth/2-pointWidth/2, sHeight/2, pointWidth, pointHeight, null);
		g.drawImage(pointer1, sWidth/2-pointWidth/2, sHeight-pointHeight, pointWidth, pointHeight, null);
	}
	
	protected void drawStats(Graphics2D g, Player player, int offY, float scaledY) {
		List<ResourceAmount> resourceAmounts = player.getResourceAmountList();
		int sWidth = this.getWidth();
		int sHeight = this.getHeight();
		int iconWidth = (int) (iconGear.getWidth()*scaledY*2);
		int iconHeight = (int) (iconGear.getHeight()*scaledY*2);
		
		g.setColor(new Color(0.7f,0,0));
		this.drawRAlignedString(g, ""+resourceAmounts.get(0).getAmount(), sWidth-iconWidth-(int)(15*scaledY), (int)(offY+17*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconGear, sWidth-iconWidth, offY, iconWidth, iconHeight, null);
		g.setColor(new Color(1f,0.7f,0.2f));
		this.drawRAlignedString(g, ""+resourceAmounts.get(1).getAmount(), sWidth-iconWidth-(int)(15*scaledY), (int)(offY+iconHeight+20*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconOil, sWidth-iconWidth, offY+iconHeight, iconWidth, iconHeight, null);
		g.setColor(new Color(0.2f,0.2f,0.7f));
		this.drawRAlignedString(g, ""+resourceAmounts.get(2).getAmount(), sWidth-iconWidth-(int)(15*scaledY), (int)(offY+iconHeight*2+20*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconSilver, sWidth-iconWidth, offY+iconHeight*2, iconWidth, iconHeight, null);
		g.setColor(new Color(0.2f,0.6f,0.2f));
		this.drawRAlignedString(g, ""+resourceAmounts.get(3).getAmount(), sWidth-iconWidth-(int)(15*scaledY), (int)(offY+iconHeight*3+20*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconUranium, sWidth-iconWidth, offY+iconHeight*3, iconWidth, iconHeight, null);
	}
	
	protected void drawRAlignedString(Graphics2D g, String string, int x, int y, float size, Font font) {
		int width = (int) this.getStringSize(g, string, size, font).getWidth();
		this.drawString(g, string, x-width, y, size, false, font);
	}
	
	protected void drawMasterMind(Graphics2D g, Player player, int offY, float scaledY) {
		Mastermind mastermind = player.getMastermind();
		int level = mastermind.getLength();
		BufferedImage nodes = ImageManager.getImage("data/images/MasterMind/"+level+"-nodes.png");
		int width = (int) (mindBoard.getWidth()*scaledY);
		int height = (int) (mindBoard.getHeight()*scaledY);
		int nodeWidth = (int) (nodes.getWidth()*scaledY);
		int nodeHeight = (int) (nodes.getHeight()*scaledY);
		int nodesDX = (int)(97*scaledY);
		int nodesDY = (int)(129*scaledY);
		g.drawImage(mindBoard, 0, offY, width, height, null);
		g.drawImage(nodes, nodesDX, nodesDY+offY, nodeWidth, nodeHeight, null);
		List<Resource> currentTry = mastermind.getCurrentTry();
		int x=0, y=0;
		for (int i=0; i<currentTry.size(); i++) {
			BufferedImage lamp = null;
			switch (i) {
			case 0:
				x=77;
				y=100;
				break;
			case 1:
				x=130;
				y=120;
				break;
			case 2:
				x=198;
				y=125;
				break;
			case 3:
				x=252;
				y=145;
				break;
			case 4:
				x=310;
				y=125;
				break;
			}
			switch (currentTry.get(i).getRealName()) {
			case "Oil":
				lamp = lampY;
				break;
			case "Gear":
				lamp = lampR;
				break;
			case "Uranium":
				lamp = lampG;
				break;
			case "Silverfish":
				lamp = lampB;
				break;
			}
			
			int lampLocX = (int) (x*scaledY);
			int lampLocY = (int) (y*scaledY);
			int lampW = (int) (lamp.getWidth()*scaledY);
			int lampH = (int) (lamp.getHeight()*scaledY);
			g.drawImage(lamp, lampLocX, lampLocY+offY, lampW, lampH, null);
		}
		int j=0;
		for (OldCombination oldTry : mastermind.getMastermindGame().getOldTries()) {	
			this.drawMasterMindHistory(g, oldTry, offY+height/2+((int)(100*scaledY)+j*(int)(pastMoveBoard.getHeight()*scaledY)), scaledY);	
			j++;
			if (j>3) {
				break;
			}
		}
		/*
		Combination combo = new Combination(3);
		combo.addResource(new Oil());
		combo.addResource(new Oil());
		combo.addResource(new Gear());
		//combo.addResource(new Gear());
		//combo.addResource(new Silverfish());
		this.drawMasterMindHistory(g, new OldCombination(combo, 1, 2), offY+height/2+((int)(100*scaledY)+j*(int)(pastMoveBoard.getHeight()*scaledY)), scaledY);
		*/
	}
	
	protected void drawMasterMindHistory(Graphics2D g, OldCombination oldTry, int offY, float scaledY) {
		int level = oldTry.getSize();
		int width = (int) (pastMoveBoard.getWidth()*scaledY);
		int height = (int) (pastMoveBoard.getHeight()*scaledY);
		g.drawImage(pastMoveBoard, (int)(45*scaledY), offY, width, height, null);
		int i=0;
		int stepX=(int) (35*scaledY);
		int shiftY=(int) (24*scaledY);
		int shiftX=(int) (130*scaledY);
		int r = (int)(20*scaledY);
		Color color = Color.WHITE;
		for (Resource res : oldTry.getResourceList()) {
			switch (res.getRealName()) {
			case "Oil": 
				color = new Color(1f,0.7f,0.2f);
				break;
			case "Gear":
				color = new Color(0.7f,0,0);
				break;
			case "Uranium":
				color = new Color(0.2f,0.6f,0.2f);
				break;
			case "Silverfish":
				color = new Color(0.2f,0.2f,0.7f);
				break;
			}
			g.setColor(color);
			g.fillOval(shiftX+i*stepX, offY+shiftY, r, r);
			i++;
		}
		shiftY=(int) (20*scaledY);
		shiftX=(int) (125*scaledY);
		r = (int)(32*scaledY);
		for (;i<5;i++) {
			color = new Color(0.285f, 0.273f, 0.265f);
			g.setColor(color);
			g.fillOval(shiftX+i*stepX, offY+shiftY, r, r);
		}
		
		int posCol = oldTry.getPoscol();
		int col = oldTry.getCol();
		stepX =(int) (20*scaledY);
		int stepY =(int) (25*scaledY);
		shiftY=(int) (10*scaledY);
		shiftX=(int) (325*scaledY);
		r = (int)(15*scaledY);
		int z=0;
		for (int j=0; j<5; j++) {
			color = new Color(1,1,1,0);
			if (posCol > 0) {
				color = Color.WHITE;
				posCol--;
			} else if (col > 0) {
				color = Color.BLACK;
				col--;
			}
			g.setColor(color);
			if (j>2) {
				z=1;
				shiftY=(int) (2*scaledY);
				shiftX=(int) (276*scaledY);
			}
			g.fillOval(shiftX+j*stepX, offY+shiftY+z*stepY, r, r);
		}
		
	}
	
	protected void drawResources(Graphics2D g) {
		Map map = this.getMap();
		List<ResourcePoint> points = new GameObjectGrabber<ResourcePoint>().getObjects(map, ResourcePoint.class);
		for (ResourcePoint point : points) {
			int x = getX(point.getBody());
			int y = getY(point.getBody());
			Sprite sprite = point.getResource().getSprite();
			int width = sprite.getImage().getWidth();
			int height = sprite.getImage().getHeight();
			this.drawSprite(g,sprite, x, y-132, 132, 132);
		}
	}
	
	protected void drawUnits(Graphics2D g) {
		Map map = this.getMap();
		for (Player player : players) {
			Unit selUnit = player.getCursor().getSelectedUnit();
			for (Unit unit : player.getUnitList()) {
				Sprite sprite = unit.getJob().getSprite();
				int x = getX(unit.getBody());
				int y = getY(unit.getBody());

				int width = sprite.getImage().getWidth();
				int height = sprite.getImage().getHeight();
				if (unit.isFacingLeft()) {
					this.drawSprite(g, sprite, x, y-height);
				} else {
					this.drawSpriteFlipped(g, sprite, x, y-height);
				}
				
				if (unit == selUnit) {
					int arrWidth = selArrow.getWidth()*2;
					int arrHeight = selArrow.getHeight()*2;
					int offY = (int) (5*Math.cos(alfa*80));
					g.drawImage(selArrow, x+width/2-arrWidth/2, y-height-120-offY, arrWidth, arrHeight, null);
				}
				g.setColor(Color.BLACK);
				g.fillRect(x, y, 10, 10);
			}
		}
	}
	
	protected void drawShrine(Graphics2D g) {
		Map map = this.getMap();
		Shrine shrine = new GameObjectGrabber<Shrine>().getObjects(map, Shrine.class).get(0);
		int x = getX(shrine.getBody());
		int y = getY(shrine.getBody());
		int uArrowWidth = arrowUp.getWidth();
		int dArrowWidth = arrowDown.getWidth();
		int hudWidth = sacrefice.getWidth();
		int hudHeight = sacrefice.getHeight();
		int offY = (int) (5*Math.cos(alfa*80));
		
		if (shrine.existSI()) {
			String selected = shrine.getSacrificeInterface().getSelected();
			if (selected != "") {
				g.drawImage(arrowUp, x+hudWidth/2-uArrowWidth/2-15, 55+offY, null);
				g.drawImage(arrowDown, x+hudWidth/2-dArrowWidth/2-15, hudHeight-offY, null);
			}
			g.drawImage(sacrefice, x, 0, null);
			
			int iconWidth = (int) (iconGear.getWidth()*3);
			int iconHeight = (int) (iconGear.getHeight()*3);
			
			BufferedImage icon = this.iconCross;
			switch (selected) {
			case "Oil":
				icon = this.iconOil;
				break;
			case "Silver":
				icon = this.iconSilver;
				break;
			case "Uranium":
				icon = this.iconUranium;
				break;
			case "Gear":
				icon = this.iconGear;
				break;
			}
			g.drawImage(icon, x+210, 270, iconWidth, iconHeight, null);
			
		}
		int shrineWidth = (int) (shrineImg.getWidth()*1.5f);
		int shrineHeight = (int) (shrineImg.getHeight()*1.5f);
		g.drawImage(shrineImg, x, y-shrineHeight, shrineWidth, shrineHeight, null);
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 10, 10);
	}
	
	protected int getX(Body body) {
		return (int) (getLoc(body).x*getMap().getTileWidth());
	}
	
	protected int getY(Body body) {
		return (int) (getLoc(body).y*getMap().getTileHeight());
	}
	
	protected Vector2 getLoc(Body body) {
		AABB aabb = body.createAABB();
		double width = aabb.getWidth();
		double height = aabb.getHeight();
		double x= body.getWorldCenter().x-width/2;
		double y= body.getWorldCenter().y+height/2;
		return new Vector2(x, y);
	}
	
	protected void drawBuildings(Graphics2D g) {
		Map map = this.getMap();
		for (Player player : players) {
			List<Building> buildings = player.getBuildingList();
			for (Building building : buildings) {
				int x = getX(building.getBody());
				int y = getY(building.getBody());
				Sprite sprite = building.getSprite();
				int width = sprite.getImage().getWidth()*2;
				int height = sprite.getImage().getHeight()*2;
				this.drawSprite(g,sprite, x, y-height, width, height);
			}
		}
	}
	
	protected void drawLoopMap(Graphics2D g, int offX, int offY){
		int sWidth = this.getWidth();
		int sHeight = this.getHeight();
		float scaledY = sHeight/2f/mapHeight;
		int scaledWidth =  (int) (mapWidth*scaledY);
		this.drawMap(g, offX%(scaledWidth)+scaledWidth, offY);
		this.drawMap(g, offX%(scaledWidth), offY);
		this.drawMap(g, offX%(scaledWidth)-scaledWidth, offY);
	}
	
	protected void drawMap(Graphics2D g, int offX, int offY) {
		int sWidth = this.getWidth();
		int sHeight = this.getHeight();
		float scaledY = sHeight/2f/mapHeight;
		int scaledWidth =  (int) (mapWidth*scaledY);
		int scaledHeight =  (int) (mapHeight*scaledY);
		g.drawImage(mapBuffer, offX, offY, scaledWidth, scaledHeight, null);
	}
	
	protected void bufferMap() {
		Graphics2D g = mapBuffer.createGraphics();
		g.setBackground(new Color(1,1,1,0));
		g.clearRect(0, 0, mapWidth, mapHeight);
		g.drawImage(cloud, (int)(-1000*Math.cos(alfa)+mapWidth/2), 250, null);
		this.drawGod(g);
		g.drawImage(cloud, (int)(-2000*Math.cos(alfa+Math.PI)+mapWidth/2), 450, null);
		g.drawImage(scrap1, 0, 150, null);
		g.drawImage(scrap2, scrap2.getWidth()+400, 180, null);
		g.drawImage(scrap2, mapWidth-scrap2.getWidth(), 180, null);
		g.drawImage(scrap1, mapWidth-scrap2.getWidth()*2-300, 150, null);
		drawTiled(g, path, mapHeight-path.getHeight());
		this.drawResources(g);
		this.drawBuildings(g);
		this.drawUnits(g);
		this.drawShrine(g);
		drawTiled(g, foreground, mapHeight-path.getHeight());
		g.dispose();
	}
	
	protected void drawGod(Graphics2D g) {
		int width = godSprite.getImage().getWidth();
		int height = godSprite.getImage().getHeight();
		int breatheY = (int) (20*Math.cos(alfa*10));
		Player p1 = players.get(0);
		Player p2 = players.get(1);
		int len1 = p1.getMastermind().getLength();
		int len2 = p2.getMastermind().getLength();
		
		int shiftX = (len2-len1)*100;
		this.drawSprite(g, godSprite, mapWidth/2-width/2+80-shiftX, 280-breatheY, width, height+breatheY);
	}
	
	protected void drawTiled(Graphics2D g, BufferedImage image, int height) {
		this.drawTiled(g, image, height, 1f);
	}
	
	protected void drawTiled(Graphics2D g, BufferedImage image, int height, float scaledY) {
		for (int x=0; x<mapWidth; x+=image.getWidth()*scaledY) {
			g.drawImage(image, x, height, (int) (image.getWidth()*scaledY), (int)(image.getHeight()*scaledY), null);
		}
	}

	@Override
	protected void drawGameObjects(Graphics2D g, List<GameObject> gameObjects) {
		// Not needed.
	}

}
