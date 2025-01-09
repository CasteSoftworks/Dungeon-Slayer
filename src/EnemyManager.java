
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyManager {
    private final List<Enemy> nemici;
    private final Random random = new Random();



    public EnemyManager() {
        this.nemici = new ArrayList<>();
    }

    public List<Enemy> getNemici() {
        return nemici;
    }

    public List<Enemy> generaNemici(char[][] mappa, int livello) {
        nemici.clear();
        int maxNemici = random.nextInt(4) + 2; // Da 2 a 5 nemici
        for (int i = 0; i < maxNemici; i++) {
            while (true) {
                int row = random.nextInt(mappa.length);
                int col = random.nextInt(mappa[0].length);

                if (mappa[row][col] == '.') {
                    char tipoNemico = determinaTipoNemico(livello);
                    int vitanemico = determinaVitaNemico(tipoNemico);
                    int danniNemico = determinaDanniNemico(tipoNemico);

                    if (tipoNemico != ' ') {
                        nemici.add(new Enemy(row, col, tipoNemico, vitanemico, danniNemico));
                        break;
                    }
                }
            }
        }

        return new ArrayList<>(nemici);
    }

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

    private int determinaVitaNemico(char tipoNemico) {
        switch (tipoNemico) {
            case 'Z':
                return 1;
            case 'S':
                return 2;
            case 'V':
                return 8;
            default:
                return 0;
        }
    }

    private int determinaDanniNemico(char tipoNemico) {
        switch (tipoNemico) {
            case 'Z':
                return 1;
            case 'S':
                return 1;
            case 'V':
                return 3;
            default:
                return 0;
        }
    }

    public void muoviNemici(char[][] mappa, int playerRow, int playerCol) {
        for (Enemy nemico : nemici) {
            int[] nuovaPosizione = calcolaNuovoMovimento(nemico, mappa, playerRow, playerCol);
            nemico.setRow(nuovaPosizione[0]);
            nemico.setCol(nuovaPosizione[1]);
        }
    }

    private int[] calcolaNuovoMovimento(Enemy nemico, char[][] mappa, int playerRow, int playerCol) {
        // Movimento semplice verso il giocatore o casuale
        int dx = playerCol - nemico.getCol();
        int dy = playerRow - nemico.getRow();
        int nuovaRiga = nemico.getRow();
        int nuovaColonna = nemico.getCol();

        if (Math.abs(dx) > Math.abs(dy)) {
            if (dx > 0 && mappa[nuovaRiga][nuovaColonna + 1] == '.') {
                nuovaColonna++;
            } else if (dx < 0 && mappa[nuovaRiga][nuovaColonna - 1] == '.') {
                nuovaColonna--;
            }
        } else {
            if (dy > 0 && mappa[nuovaRiga + 1][nuovaColonna] == '.') {
                nuovaRiga++;
            } else if (dy < 0 && mappa[nuovaRiga - 1][nuovaColonna] == '.') {
                nuovaRiga--;
            }
        }

        return new int[]{nuovaRiga, nuovaColonna};
    }

    public void rimuoviNemico(Enemy nemico) {
        nemici.remove(nemico);
    }
}
