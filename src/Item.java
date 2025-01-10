public class Item {
    /** Coordinate dell'oggetto */
    private int row, col;
    /** Tipo di oggetto */
    private char tipo; // 'A' = Armor, 'W' = Weapon, 'H' = Health
    /** Valore dell'oggetto */
    private int value;

    /**
     * Costruttore di Item
     * 
     * @param row riga
     * @param col colonna
     * @param tipo tipo di oggetto
     * @param value valore dell'oggetto
     */
    public Item(int row, int col, char tipo, int value) {
        this.row = row;
        this.col = col;
        this.tipo = tipo;
        this.value = value;
    }

    /**
     * Restituisce la riga dell'oggetto
     * 
     * @return la riga
     */
    public int getRow() {
        return row;
    }

    /**
     * Imposta la riga dell'oggetto
     * 
     * @param row riga
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Restituisce la colonna dell'oggetto
     * 
     * @return la colonna
     */
    public int getCol() {
        return col;
    }

    /**
     * Imposta la colonna dell'oggetto
     * 
     * @param col colonna
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Restituisce il tipo di oggetto
     * 
     * @return
     */
    public char getTipo() {
        return tipo;
    }

    /**
     * Restituisce il valore dell'oggetto
     * 
     * @return
     */
    public int getValue() {
        return value;
    }
}
