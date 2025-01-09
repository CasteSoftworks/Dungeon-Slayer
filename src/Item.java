public class Item {
    private int row;
    private int col;
    private char tipo; // 'A' = Armor, 'W' = Weapon, 'H' = Health
    private int value;

    public Item(int row, int col, char tipo, int value) {
        this.row = row;
        this.col = col;
        this.tipo = tipo;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public char getTipo() {
        return tipo;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
}
