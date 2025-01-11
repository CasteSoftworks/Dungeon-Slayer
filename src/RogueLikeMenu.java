import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
    /** Sfondo del menù */
    public BufferedImage background; // Changed to public to allow access

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

        loadBackground();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        startGame();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        System.exit(0); // Esci dall'applicazione
                    case KeyEvent.VK_L:
                        showLeaderBoard();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * Carica lo sfondo del menù
     */
    private void loadBackground() {
        try {
            background = ImageIO.read(new File("src/Menu/dungeonSlayer.png"));
        } catch (IOException e) {
            System.out.println("Errore nel caricamento dell'immagine di sfondo");
        }
    }

    /**
     * Mostra la classifica
     */
    private void showLeaderBoard() {
        JFrame leaderBoardFrame = new JFrame("Classifica");
        leaderBoardFrame.setSize(width, height);
        leaderBoardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        LeaderBoard leaderBoard = new LeaderBoard(leaderBoardFrame, width, height); // Pannello della classifica
        leaderBoardFrame.add(leaderBoard); // Aggiunge la classifica alla finestra
        leaderBoardFrame.setVisible(true);
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

        g2d.drawImage(background, 0, 0, width, height, this);
    
        // Disegno delle istruzioni
        g2d.setFont(new Font("Arial", Font.PLAIN, Math.min(width, height) / 25));
        String startText = "Premi ENTER per iniziare";
        String leaderBoardText = "Premi L per visualizzare la classifica";
        String exitText = "Premi ESC per uscire";
        int startTextWidth = g2d.getFontMetrics().stringWidth(startText);
        int leaderBoardTextWidth = g2d.getFontMetrics().stringWidth(leaderBoardText);
        int exitTextWidth = g2d.getFontMetrics().stringWidth(exitText);

        // Calcola le dimensioni dei rettangoli di sfondo
        int padding = 10;
        int startTextHeight = g2d.getFontMetrics().getHeight();
        int leaderBoardTextHeight = g2d.getFontMetrics().getHeight();
        int exitTextHeight = g2d.getFontMetrics().getHeight();

        // Disegna i rettangoli di sfondo centrati dietro il testo
        g2d.setColor(Color.BLACK);
        g2d.fillRect((width - startTextWidth) / 2 - padding, height / 2 - startTextHeight - padding, startTextWidth + 2 * padding, startTextHeight + 2 * padding);
        g2d.fillRect((width - leaderBoardTextWidth) / 2 - padding, height / 2 + height / 24 - leaderBoardTextHeight - padding + 10, leaderBoardTextWidth + 2 * padding, leaderBoardTextHeight + 2 * padding);
        g2d.fillRect((width - exitTextWidth) / 2 - padding, height / 2 + height / 12 - exitTextHeight - padding + 20, exitTextWidth + 2 * padding, exitTextHeight + 2 * padding);
        g2d.setColor(Color.WHITE);
        g2d.drawString(startText, (width - startTextWidth) / 2, height / 2);
        g2d.drawString(leaderBoardText, (width - leaderBoardTextWidth) / 2, height / 2 + height / 24 + 10);
        g2d.drawString(exitText, (width - exitTextWidth) / 2, height / 2 + height / 12 + 20);
    }
}
