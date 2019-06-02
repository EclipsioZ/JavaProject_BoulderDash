package controller;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound implements Runnable {
	
	String soundName;
	
	Sound() {
		soundName = "";
	}

	public void loadSound(URL songFile) {
		SourceDataLine soundLine = null;
		int BUFFER_SIZE = 64 * 1024; // 64 KB

		try {
//			File soundFile = new File(songFile);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			soundLine = (SourceDataLine) AudioSystem.getLine(info);
			soundLine.open(audioFormat);
			soundLine.start();
			int nBytesRead = 0;
			byte[] sampledData = new byte[BUFFER_SIZE];
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(sampledData, 0, sampledData.length);
				if (nBytesRead >= 0) {
					soundLine.write(sampledData, 0, nBytesRead);
				}
			}
		} catch (UnsupportedAudioFileException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			ex.printStackTrace();
		} finally {
			soundLine.drain();
			soundLine.start();
		}
	}

	@Override
	public void run() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		URL songFile = classLoader.getResource(this.soundName + ".wav");	
//		String songFile = getClass().getClassLoader().getResource(this.soundName + ".wav").getFile();
		this.loadSound(songFile);
	}
	
	public void changeSound(String soundName) {
		this.soundName = soundName;
		this.run();
	}
}
