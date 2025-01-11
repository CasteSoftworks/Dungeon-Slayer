
import java.io.Serializable;

public class Enemy implements Serializable{
    /** Le coordinate del nemico */
    private int row, col;
    /** Il tipo di nemico */
    @SuppressWarnings("FieldMayBeFinal")
    private char tipo;
    /** Vita e danni del nemico */
    @SuppressWarnings("FieldMayBeFinal")
    private int vita;
    @SuppressWarnings("FieldMayBeFinal")
    private int danni;
    @SuppressWarnings("FieldMayBeFinal")
    private int exp;

    /**
     * Costruttore di Enemy
     * 
     * @param row riga
     * @param col colonna
     * @param tipo tipo di nemico
     * @param vita vita del nemico
     * @param danni danni del nemico
     */
    public Enemy(int row, int col, char tipo, int vita, int danni, int exp) {
        this.row = row;
        this.col = col;
        this.tipo = tipo;
        this.vita = vita;
        this.danni = danni;
        this.exp = exp;
    }

    /**
     * Restituisce la riga del nemico
     * 
     * @return la riga
     */
    public int getRow() {
        return row;
    }

    /**
     * Imposta la riga del nemico
     * 
     * @param row riga
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Restituisce la colonna del nemico
     * 
     * @return la colonna
     */
    public int getCol() {
        return col;
    }

    /**
     * Imposta la colonna del nemico
     * 
     * @param col colonna
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Restituisce il tipo di nemico
     * 
     * @return il tipo
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Recupera la vita del nemico
     * 
     * @return vita
     */
    public int getVita() {
        return vita;
    }

    /**
     * Recupera i danni del nemico
     * 
     * @return
     */
    public int getDanni() {
        return danni;
    }

    /**
     * Recupera l'esperienza del nemico
     * 
     * @return
     */
    public int getExp() {
        return exp;
    }

    /**
     * Modifica la riga del nemico
     * 
     * @param var variazione della riga
     */
    public void updateRow(int var) {
        this.row+=var; 
    }

    /**
     * Modifica la colonna del nemico
     * 
     * @param var variazione della colonna
     */
    public void updateCol(int var) {
        this.col+=var;
    }
}
