import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager {
    /** Lista di oggetti */
    private final List<Item> items;
    /** Generatore di numeri casuali */
    private final Random random = new Random();

    /** 
     * Costruttore di ItemManager
     */
    public ItemManager() {
        this.items = new ArrayList<>();
    }
    
    /**
     * Restituisce la lista di oggetti
     * 
     * @return lista di oggetti
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Genera oggetti casuali sulla mappa
     * 
     * @param mappa mappa del livello
     * @param livello livello corrente
     * 
     * @return lista di oggetti generati
     */
    public List<Item> generaOggetti(char[][] mappa, int livello) {
        items.clear();
        int maxOggetti = random.nextInt(1)+1; // Da 1 a 2 oggetti
        
        for (int i = 0; i < maxOggetti; i++) {
            while (true) {
                int row = random.nextInt(mappa.length);
                int col = random.nextInt(mappa[0].length);

                if (mappa[row][col] == '.') {
                    char tipoOggetto = determinaTipoOggetto(livello);
                    int valoreOggetto = determinaValoreOggetto(tipoOggetto);

                    if (tipoOggetto != ' ') {
                        items.add(new Item(row, col, tipoOggetto, valoreOggetto));
                        break;
                    }
                }
            }
        }

        return new ArrayList<>(items);
    }

    /**
     * Determina il tipo di oggetto da generare a seconda del livello
     * 
     * @param livello livello corrente
     * 
     * @return tipo di oggetto
     */
    private char determinaTipoOggetto(int livello) {
        int probabilità = random.nextInt(100) + 1;

        if (livello <= 5) {
            return 'H'; // Health (100%)
        } else if (livello <= 7) {
            if (probabilità <= 70) return 'H'; // Health 70%
            if (probabilità <= 100) return 'A'; // Armor 30%
        } else if (livello <= 10) {
            if (probabilità <= 50) return 'H'; // Health 50%
            if (probabilità <= 100) return 'A'; // Armor 50%
        } else {
            if (probabilità <= 40) return 'H'; // Health 40%
            if (probabilità <= 80) return 'A'; // Armor 40%
            if (probabilità <= 100) return 'W'; // Weapon 20%
        } 

        return ' ';
    }

    /**
     * Determina il valore dell'oggetto a seconda del tipo
     * 
     * @param tipoOggetto tipo di oggetto
     * 
     * @return valore dell'oggetto
     */
    private int determinaValoreOggetto(char tipoOggetto) {
        return switch (tipoOggetto) {
            case 'H' -> random.nextInt(10) + 1;
            case 'A' -> 1;
            case 'W' -> 1;
            default -> 0;
        };
    }
}
