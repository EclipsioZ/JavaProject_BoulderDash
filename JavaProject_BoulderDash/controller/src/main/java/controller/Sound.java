package controller;

import java.io.*;

import java.net.URL;

import javax.sound.sampled.*;

/**
 * The Class Sound
 *
 * @author Florian Rossi
 * @author Baptiste Miquel
 */

public class Sound implements Runnable {

	private String soundName;

	/**
	 * Constructor of sound class
	 */
	Sound() {
		soundName = "";
	}

	/**
	 * Load the sound with the song file
	 * 
	 * @param songFile URL of file
	 */
	public void loadSound(URL songFile) {
		SourceDataLine soundLine = null;
		int BUFFER_SIZE = 64 * 1024; // 64 KB

		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(songFile);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			//Open the sound and play him
			soundLine = (SourceDataLine) AudioSystem.getLine(info);
			soundLine.open(audioFormat);
			soundLine.start();
			//Define the bitrate
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

	/**
	 * Executed when the thread of sound is started
	 */
	@Override
	public void run() {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		URL songFile = classLoader.getResource(this.soundName + ".wav");
		this.loadSound(songFile);
	}

	/**
	 * Change the sound with the name of the new song and execute the method run()
	 * 
	 * @param soundName String name of the music
	 */
	public void changeSound(String soundName) {
		this.soundName = soundName;
		this.run();
	}
	
	public void setSoundName(String soundName) {
		this.soundName = soundName;
	}
}
