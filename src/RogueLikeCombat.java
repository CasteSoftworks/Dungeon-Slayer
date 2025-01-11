import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.*;

public class RogueLikeCombat extends JPanel implements KeyListener {
    /** Il costrutto gioco */
    private final RogueLikeGame game;

    /** Vita, danni e armatura del giocatore */
    private int playerHealth;
    @SuppressWarnings("FieldMayBeFinal")
    private int playerDmg;
    private int playerArmor;

    /** Nemico con vita e danni del nemico */
    private final Enemy enemy;
    private int enemyHealth;
    @SuppressWarnings("FieldMayBeFinal")
    private int enemyDmg;
    @SuppressWarnings("FieldMayBeFinal")
    private int enemyExp;

    /** Variabili per il lancio dei dadi */
    private boolean playerRolled = false;
    private boolean enemyRolled = false;
    private int playerRoll = 0;
    private int enemyRoll = 0;
    /** Generatore di numeri casuali */
    private final Random random = new Random();

    /**
     * Costruttore di RogueLikeCombat
     * 
     * @param game costrutto gioco
     * @param playerHealth vita del giocatore
     * @param playerDmg danni del giocatore
     * @param playerArmor armatura del giocatore
     * @param enemy nemico
     */
    public RogueLikeCombat(RogueLikeGame game, int playerHealth, int playerDmg, int playerArmor, Enemy enemy) {
        this.game = game;
        this.playerHealth = playerHealth;
        this.playerDmg = playerDmg;
        this.playerArmor = playerArmor;
        
        this.enemy = enemy;
        this.enemyHealth = enemy.getVita();
        this.enemyDmg = enemy.getDanni();
        this.enemyExp = enemy.getExp();

        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
    }

    /**
     * Lancia un dado a 6 facce
     * 
     * @return risultato del lancio
     */
    private int rollDice() {
        return random.ints(1, 1, 7).findFirst().getAsInt();
    }

    private void resolveCombat() {
        boolean playerWon = enemyHealth <= 0;
        game.endCombat(playerWon, playerHealth, playerArmor, enemyExp, enemy);
        SwingUtilities.getWindowAncestor(this).dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        g.fillRect(50, 150, 100, 100); // Enemy square

        g.setColor(Color.MAGENTA);
        g.fillRect(250, 150, 100, 100); // Player square

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 24));
        g.drawString(enemy.getTipo()+": " + enemyHealth, 50, 100);
        g.drawString("Player: " + playerHealth, 250, 100);

        if (enemyRolled) {
            g.drawString(String.valueOf(enemyRoll), 90, 200);
        }

        if (playerRolled) {
            g.drawString(String.valueOf(playerRoll), 290, 200);
        }

        FontMetrics fm = g.getFontMetrics();
        String text = "SPACEBAR";
        int textWidth = fm.stringWidth(text);
        int x = (getWidth() - textWidth) / 2;
        int y = 300;

        g.drawString(text, x, y);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!playerRolled || !enemyRolled) {
                if (!playerRolled) {
                    playerRoll = rollDice();
                    playerRolled = true;
                } else if (!enemyRolled) {
                    enemyRoll = rollDice();
                    enemyRolled = true;
                }
                repaint();
            } else {
                // Apply damage
                if (playerRoll > enemyRoll) {
                    enemyHealth-=playerDmg;
                } else if (enemyRoll > playerRoll) {
                    if(enemyRoll-playerRoll>playerArmor){
                        playerHealth-=enemyDmg;
                    }else if(enemyRoll-playerRoll==playerArmor){
                        //playerArmor-=enemyRoll-playerRoll;
                        playerArmor--;
                    }
                }

                // Reset rolls for next round
                playerRolled = false;
                enemyRolled = false;

                // Check if combat ends
                if (enemyHealth <= 0 || playerHealth <= 0) {
                    resolveCombat();
                }

                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) { }
}
