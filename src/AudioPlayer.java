import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioPlayer {
    private Clip clip;

    /**
     * Carica un file audio.
     *
     * @param filePath il percorso del file audio
     */
    public void load(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Errore nel caricamento del file audio: " + filePath);
        }
    }

    /**
     * Avvia la riproduzione dell'audio.
     */
    public void play() {
        if (clip != null) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Riproduce in loop
        }
    }

    /**
     * Ferma la riproduzione dell'audio.
     */
    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
