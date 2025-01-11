import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameLauncher {
    /**
     * Metodo per avviare il gioco
     */
    public void launch() {
        int width = 800;  // Larghezza della finestra
        int height = 600; // Altezza della finestra

        JFrame frame = new JFrame("Dungeon Slayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Centra la finestra

        // Load and set the custom icon
        try {
            BufferedImage icon = ImageIO.read(new File("src/icone/icon.png"));
            frame.setIconImage(icon);
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dell'icona");
        }

        RogueLikeMenu menuPanel = new RogueLikeMenu(frame, width, height); // Crea il menù con dimensioni dinamiche
        frame.add(menuPanel); // Aggiunge il menù alla finestra
        frame.setVisible(true);
    }
}