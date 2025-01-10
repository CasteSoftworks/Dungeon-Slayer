
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private final List<Enemy> nemici;
    private final Random random = new Random();


    /**
     * Costruttore della classe EnemyManager
     */
    public EnemyManager() {
        this.nemici = new ArrayList<>();
    }

    /**
     * Restituisce la lista dei nemici
     * 
     * @return la lista dei nemici
     */
    public List<Enemy> getNemici() {
        return nemici;
    }

    /**
     * Genera i nemici sulla mappa
     * 
     * @param mappa la mappa
     * @param livello il livello del gioco
     * 
     * @return la lista dei nemici
     */
    public List<Enemy> generaNemici(char[][] mappa, int livello) {
        nemici.clear();
        int maxNemici = random.nextInt(4) + 2; // Da 2 a 5 nemici
        for (int i = 0; i < maxNemici; i++) {
            while (true) {
                int row = random.nextInt(mappa.length);
                int col = random.nextInt(mappa[0].length);

                if (mappa[row][col] == '.') {
                    char tipoNemico = determinaTipoNemico(livello);
                    int vitanemico = determinaVitaNemico(tipoNemico, livello);
                    int danniNemico = determinaDanniNemico(tipoNemico, livello);
                    int expNemico = determinaExpNemico(tipoNemico, livello);

                    if (tipoNemico != ' ') {
                        nemici.add(new Enemy(row, col, tipoNemico, vitanemico, danniNemico, expNemico));
                        break;
                    }
                }
            }
        }

        return new ArrayList<>(nemici);
    }

    /**
     * Determina il tipo di nemico in base al livello
     * 
     * <p>
     * I nemici sono divisi in:
     * <ul>
     * <li>Z: Zombie</li>
     * <li>S: Scheletro</li>
     * <li>V: Vampiro</li>
     * </ul>
     * 
     * @param livello il livello del gioco
     * 
     * @return il tipo di nemico
     */
    private char determinaTipoNemico(int livello) {
        int probabilità = random.nextInt(100) + 1;

        if (livello <= 5) {
            return 'Z'; // Zombie (100%)
        } else if (livello <= 7) {
            if (probabilità <= 70) return 'Z'; // Zombie 70%
            if (probabilità <= 100) return 'S'; // Scheletro 30%
        } else if (livello <= 10) {
            if (probabilità <= 50) return 'Z'; // Zombie 50%
            if (probabilità <= 100) return 'S'; // Scheletro 50%
        } else if (livello <= 15) {
            if (probabilità <= 20) return 'Z'; // Zombie 20%
            if (probabilità <= 95) return 'S'; // Scheletro 75%
            if (probabilità <= 100) return 'V'; // Vampiro 5%
        }else{
            if (probabilità <= 70) return 'S'; // Scheletro 60%
            if (probabilità <= 100) return 'V'; // Vampiro 30%
        }
        return ' '; // Nessun nemico
    }

    /**
     * Determina la vita del nemico in base al tipo e al livello
     * 
     * <p>
     * La vita aumenta di 1 ogni 10 livelli
     * 
     * @param tipoNemico il tipo di nemico
     * @param livello il livello del gioco
     * 
     * @return la vita
     */
    private int determinaVitaNemico(char tipoNemico, int livello) {
        switch (tipoNemico) {
            case 'Z':
                return 1+(livello/10);
            case 'S':
                return 2+(livello/10);
            case 'V':
                return 8+(livello/10);
            default:
                return 0;
        }
    }

    /**
     * Determina i danni che il nemico infliggerà al giocatore in base al tipo e al livello
     * 
     * <p>
     * I danni aumentano di 1 ogni 10 livelli
     * 
     * @param tipoNemico il tipo di nemico
     * @param livello il livello del gioco
     * 
     * @return i danni
     */
    private int determinaDanniNemico(char tipoNemico, int livello) {
        switch (tipoNemico) {
            case 'Z':
                return 1+(livello/10);
            case 'S':
                return 2+(livello/10);
            case 'V':
                return 3+(livello/10);
            default:
                return 0;
        }
    }

    /**
     * Determina l'esperienza che il nemico darà al giocatore in base al tipo e al livello
     * 
     *  <p>
     * L'esperienza aumenta di 1 ogni 10 livelli
     * 
     * @param tipoNemico il tipo di nemico
     * @param livello il livello del giocatore
     * 
     * @return l'esperienza
     */
    private int determinaExpNemico(char tipoNemico, int livello) {
        switch (tipoNemico) {
            case 'Z':
                return 1+(livello/10);
            case 'S':
                return 2+(livello/10);
            case 'V':
                return 5+(livello/10);
            default:
                return 0;
        }
    }

    /**
     * Rimuove un nemico dalla lista
     * 
     * @param nemico il nemico da rimuovere
     */
    public void rimuoviNemico(Enemy nemico) {
        nemici.remove(nemico);
    }
}
