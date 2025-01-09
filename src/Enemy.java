public class Enemy {
    private int row;
    private int col;
    private char tipo; // 'Z' per Zombie, 'S' per Scheletro, V per Vampiro.
    private int vita;
    private int danni;

    public Enemy(int row, int col, char tipo, int vita, int danni) {
        this.row = row;
        this.col = col;
        this.tipo = tipo;
        this.vita = vita;
        this.danni = danni;
        //this.sprite = sprite;
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

    public int getVita() {
        return vita;
    }

    public void setVita(int vita) {
        this.vita = vita;
    }

    public int getDanni() {
        return danni;
    }

    public void setDanni(int danni) {
        this.danni = danni;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    /*public BufferedImage getSprite() {
        return sprite;
    }*/

    public void updateRow(int var) {
        this.row+=var; 
    }

    public void updateCol(int var) {
        this.col+=var;
    }
}
