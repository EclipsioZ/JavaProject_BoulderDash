package model.elements;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundThread implements Runnable {

	private String soundName;

	SoundThread() {

	}

	public String getSoundName() {
		return soundName;
	}

	public void setSoundName(String soundName) {
		this.soundName = soundName;
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
			// Open the sound and play him
			soundLine = (SourceDataLine) AudioSystem.getLine(info);
			soundLine.open(audioFormat);
			soundLine.start();
			// Define the bitrate
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
	 * Method for run the thread
	 */
	@Override
	public void run() {
		if (soundName != "") {
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			URL songFile = classLoader.getResource(this.soundName + ".wav");
			this.loadSound(songFile);
			this.soundName = "";
		}

	}
}
