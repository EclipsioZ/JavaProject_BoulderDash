package model.elements;

	import java.io.*;

	import java.net.URL;

	import javax.sound.sampled.*;

	/**
	 * The Class Sound
	 *
	 * @author Florian Rossi
	 * @author Baptiste Miquel
	 */

	public class SoundEffect implements Runnable {

		private String soundName;

		/**
		 * Constructor of sound class
		 */
		public SoundEffect() {
			soundName = "";
		}


		/**
		 * Executed when the thread of sound is started
		 */
		@Override
		public void run() {
			while(true) {
				if(this.soundName != "") {
					SoundThread sound = new SoundThread();
					sound.setSoundName(this.soundName);
					Thread soundTh = new Thread(sound);
					soundTh.start();
					this.soundName = "";
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		/**
		 * Change the sound with the name of the new song and execute the method run()
		 * 
		 * @param soundName String name of the music
		 */
		public void changeSound(String soundName) {
			this.soundName = soundName;	
		}
		
		public void setSoundName(String soundName) {
			this.soundName = soundName;
		}
		
		public void soundtest(String soundName) {
			this.soundName = soundName;
			this.run();
		}
	}

