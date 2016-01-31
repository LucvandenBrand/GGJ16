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

import com.snappycobra.ggj16.mastermind.Mastermind;
import com.snappycobra.ggj16.model.Base;
import com.snappycobra.ggj16.model.Building;
import com.snappycobra.ggj16.model.Cursor;
import com.snappycobra.ggj16.model.GameModel;
import com.snappycobra.ggj16.model.Player;
import com.snappycobra.ggj16.model.Resource;
import com.snappycobra.ggj16.model.ResourcePoint;
import com.snappycobra.ggj16.model.Shrine;
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
	private BufferedImage mindBoard, pastMoveBoard, lampB, lampG, lampR, lampY;
	private BufferedImage cloud, pointer1, pointer2, iconGear, iconOil, iconUranium, iconSilver;
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
			//System.out.println(posX);+
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
		
		int sWidth = this.getWidth();
		int sHeight = this.getHeight();
		int iconWidth = (int) (iconGear.getWidth()*scaledY*2);
		int iconHeight = (int) (iconGear.getHeight()*scaledY*2);
		
		g.setColor(new Color(0.7f,0,0));
		this.drawRAlignedString(g, "2", sWidth-iconWidth-(int)(15*scaledY), (int)(offY+17*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconGear, sWidth-iconWidth, offY, iconWidth, iconHeight, null);
		g.setColor(new Color(1f,0.7f,0.2f));
		this.drawRAlignedString(g, "8", sWidth-iconWidth-(int)(15*scaledY), (int)(offY+iconHeight+20*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconOil, sWidth-iconWidth, offY+iconHeight, iconWidth, iconHeight, null);
		g.setColor(new Color(0.2f,0.2f,0.7f));
		this.drawRAlignedString(g, "9", sWidth-iconWidth-(int)(15*scaledY), (int)(offY+iconHeight*2+20*scaledY), 55*scaledY, UIFont);
		g.drawImage(iconSilver, sWidth-iconWidth, offY+iconHeight*2, iconWidth, iconHeight, null);
		g.setColor(new Color(0.2f,0.6f,0.2f));
		this.drawRAlignedString(g, "3", sWidth-iconWidth-(int)(15*scaledY), (int)(offY+iconHeight*3+20*scaledY), 55*scaledY, UIFont);
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
			switch (currentTry.get(i).getResName()) {
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
		this.drawMasterMindHistory(g, player, offY+height/2+(int)(100*scaledY), scaledY);
	}
	
	protected void drawMasterMindHistory(Graphics2D g, Player player, int offY, float scaledY) {
		Mastermind mastermind = player.getMastermind();
		int level = mastermind.getLength();
		int width = (int) (pastMoveBoard.getWidth()*scaledY);
		int height = (int) (pastMoveBoard.getHeight()*scaledY);
		//System.o
		g.drawImage(pastMoveBoard, (int)(45*scaledY), offY, width, height, null);
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
			for (Unit unit : player.getUnitList()) {
				Sprite sprite = unit.getJob().getSprite();
				int x = getX(unit.getBody());
				int y = getY(unit.getBody());

				int width = sprite.getImage().getWidth();
				int height = sprite.getImage().getHeight();
				this.drawSprite(g, sprite, x, y-height);
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
		int offY = (int) (5*Math.cos(alfa));
		//g.drawImage(arrowUp, x+hudWidth/2-uArrowWidth/2-15-shrineImg.getWidth()/2, 55+offY, null);
		//g.drawImage(arrowDown, x+hudWidth/2-dArrowWidth/2-15-shrineImg.getWidth()/2, hudHeight-offY, null);
		//g.drawImage(sacrefice, x-shrineImg.getWidth()/2, 0, null);
		g.drawImage(shrineImg, x, y-shrineImg.getHeight(), null);
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
				int width = sprite.getImage().getWidth();
				int height = sprite.getImage().getHeight();
				this.drawSprite(g,sprite, x, y-height);
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
		this.drawSprite(g, godSprite, mapWidth/2-width/2+80, 280-breatheY, width, height+breatheY);
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
