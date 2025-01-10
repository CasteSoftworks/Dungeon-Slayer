import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemManager {
    private final List<Item> items;
    private final Random random = new Random();

    public ItemManager() {
        this.items = new ArrayList<>();
    }
    
    public List<Item> getItems() {
        return items;
    }

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
        } else if (livello <= 15) {
            if (probabilità <= 20) return 'H'; // Health 20%
            if (probabilità <= 95) return 'A'; // Armor 75%
            if (probabilità <= 100) return 'W'; // Weapon 5%
        } else {
            if (probabilità <= 10) return 'H'; // Health 10%
            if (probabilità <= 80) return 'A'; // Armor 70%
            if (probabilità <= 100) return 'W'; // Weapon 20%
        }

        return ' ';
    }

    private int determinaValoreOggetto(char tipoOggetto) {
        switch (tipoOggetto) {
            case 'H':
                return random.nextInt(10) + 1; // Da 1 a 10 punti vita
            case 'A':
                return 1; // 1 punto armatura
            case 'W':
                return 1; // 1 punto danno
            default:
                return 0;
        }
    }
    
}
