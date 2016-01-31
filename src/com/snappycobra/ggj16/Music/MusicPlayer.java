package com.snappycobra.ggj16.Music;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class MusicPlayer extends Thread{
	
	public MusicPlayer() {
		TinySound.init();
	}
	
	public void run() {
		//initialize TinySound
		//load a sound and music
		//note: you can also load with Files, URLs and InputStreams
		//File muziekje = new File("music/cropelise8kHz.wav");
		List <File> muziek  = new ArrayList<>();
		muziek.add(new File("data/sounds/music/evolvingcurrents.wav"));
		muziek.add(new File("data/sounds/music/robotfactory.wav"));
		muziek.add(new File("data/sounds/music/ghost.wav"));
		
		//File geluidje = new File("music/handel.wav");
		
		
		boolean state = true;
		while (state) {
			for (File songDes : muziek) {
				Music song = TinySound.loadMusic(songDes);
				song.play(false);
				while (!song.done()) {
				}
			}
		}
		TinySound.shutdown();
	}
	
	public void playSound(String sound) {

		System.out.println("play");
		Sound wav = TinySound.loadSound(new File(sound));
		wav.play(3);
	}
}
