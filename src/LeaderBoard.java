import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class LeaderBoard extends JPanel {
    @SuppressWarnings("FieldMayBeFinal")
    private JFrame frame;
    @SuppressWarnings("unused")
    private int width;
    @SuppressWarnings("unused")
    private int height;

    public LeaderBoard(JFrame frame, int width, int height) {
        this.frame = frame;
        this.width = width;
        this.height = height;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setAlignmentX(CENTER_ALIGNMENT);
        textArea.setAlignmentY(CENTER_ALIGNMENT);

        String data = readDataFromFile("lead.bin");
        textArea.setText(data);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.dispose();
                }
            }
        });
        setFocusable(true);
        requestFocusInWindow();
    }

    private String readDataFromFile(String filePath) {
        StringBuilder data = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 3) {
                    data.append(line.substring(3)).append("\n");
                } else {
                    data.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella lettura del file: " + filePath);
        }
        return data.toString();
    }
}