import javax.swing.JFrame;

public class GameLauncher {
    public void launch() {
        int width = 600;  // Larghezza della finestra
        int height = 400; // Altezza della finestra

        JFrame frame = new JFrame("Dungeon Slayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null); // Centra la finestra

        RogueLikeMenu menuPanel = new RogueLikeMenu(frame, width, height); // Crea il menù con dimensioni dinamiche
        frame.add(menuPanel); // Aggiunge il menù alla finestra
        frame.setVisible(true);
    }
}