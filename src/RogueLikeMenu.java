import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class RogueLikeMenu extends JPanel {
    /** Il frame del menù */
    @SuppressWarnings("FieldMayBeFinal")
    private JFrame frame;
    /** La larghezza del menù */
    @SuppressWarnings("FieldMayBeFinal")
    private int width;
    /** La altezza del menù */
    @SuppressWarnings("FieldMayBeFinal")
    private int height;

    /**
     * Costruttore del menù del gioco
     * 
     * @param frame il frame del menù
     * @param width la larghezza del menù
     * @param height l'altezza del menù
     */
    public RogueLikeMenu(JFrame frame, int width, int height) {
        this.frame = frame;
        this.width = width;
        this.height = height;

        setBackground(Color.BLACK);
        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startGame();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.exit(0); // Esci dall'applicazione
                }
            }
        });
    }

    /**
     * Avvia il gioco rimuovendo il menù e aggiungendo il pannello del gioco alla finestra
     */
    private void startGame() {
        frame.getContentPane().removeAll(); // Rimuove il menù
        RogueLikeGame gamePanel = new RogueLikeGame(width, height); // Pannello del gioco
        frame.add(gamePanel); // Aggiunge il gioco alla finestra
        frame.revalidate();
        frame.repaint();
        gamePanel.start(gamePanel); // Avvia il ciclo del gioco
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Disegno del titolo
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, Math.min(width, height) / 15)); // Font proporzionale
        String title = "DUNGEON SLAYER";
        int titleWidth = g2d.getFontMetrics().stringWidth(title);
        g2d.drawString(title, (width - titleWidth) / 2, height / 3);

        // Disegno delle istruzioni
        g2d.setFont(new Font("Arial", Font.PLAIN, Math.min(width, height) / 25));
        String startText = "Premi ENTER per iniziare";
        String exitText = "Premi ESC per uscire";
        int startTextWidth = g2d.getFontMetrics().stringWidth(startText);
        int exitTextWidth = g2d.getFontMetrics().stringWidth(exitText);

        g2d.drawString(startText, (width - startTextWidth) / 2, height / 2);
        g2d.drawString(exitText, (width - exitTextWidth) / 2, height / 2 + height / 12);
    }
}