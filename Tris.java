//Yzeir Plaka 4C INF

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;


public class Tris extends JFrame implements ActionListener {

//Attributi
    private boolean giocoFinito = false;
    private boolean playerX = true;

//Componenti
    private JButton[][] bottoniButton;
    private JLabel statoPartitaLabel;

//Costruttore
    public Tris() {
        setTitle("Tris");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pannelloPanel = new JPanel();
        pannelloPanel.setLayout(new GridLayout(3, 3));
        bottoniButton = new JButton[3][3];

        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bottoniButton[i][j] = new JButton("");
                bottoniButton[i][j].addActionListener(this);
                pannelloPanel.add(bottoniButton[i][j]);
            }
        }

        statoPartitaLabel = new JLabel("Tocca al Giocatore X");
        add(pannelloPanel, BorderLayout.CENTER);
        add(statoPartitaLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

//Metodi
    private void cercaVincitore() {
        String vincitore = "";

        //Controllo righe
        for(int i = 0; i < 3; i++) {
            if(!bottoniButton[i][0].getText().equals("") && bottoniButton[i][0].getText().equals(bottoniButton[i][1].getText())
                    && bottoniButton[i][1].getText().equals(bottoniButton[i][2].getText())) {
                vincitore = bottoniButton[i][0].getText();
            }
        }

        //Controllo colonne
        for(int i = 0; i < 3; i++) {
            if(!bottoniButton[0][i].getText().equals("") && bottoniButton[0][i].getText().equals(bottoniButton[1][i].getText())
                    && bottoniButton[1][i].getText().equals(bottoniButton[2][i].getText())) {
                vincitore = bottoniButton[0][i].getText();
            }
        }

        //Controllo diagonali
        if(!bottoniButton[0][0].getText().equals("") && bottoniButton[0][0].getText().equals(bottoniButton[1][1].getText())
                && bottoniButton[1][1].getText().equals(bottoniButton[2][2].getText())) {
            vincitore = bottoniButton[0][0].getText();
        }
        if(!bottoniButton[0][2].getText().equals("") && bottoniButton[0][2].getText().equals(bottoniButton[1][1].getText())
                && bottoniButton[1][1].getText().equals(bottoniButton[2][0].getText())) {
            vincitore = bottoniButton[0][2].getText();
        }

        //Controllo vincitore
        if(!vincitore.equals("")) {
            giocoFinito = true;
            statoPartitaLabel.setText("Il Giocatore " + vincitore + " ha vinto!");
        } else {
            boolean draw = true;
            for(int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(bottoniButton[i][j].getText().equals("")) {
                        draw = false;
                        break;
                    }
                }
            }
            if(draw) {
                giocoFinito = true;
                statoPartitaLabel.setText("Pareggio!");
            }
        }

        
    }

//Ascoltatore
    public void actionPerformed(ActionEvent e) {
        JButton bottoneTemp = (JButton) e.getSource();
        if (bottoneTemp.getText().equals("") && !giocoFinito) {
            if (playerX) {
                bottoneTemp.setText("X");
                statoPartitaLabel.setText("Tocca al Giocatore O");
            } else {
                bottoneTemp.setText("O");
                statoPartitaLabel.setText("Tocca al Giocatore X");
            }
            playerX = !playerX;
            cercaVincitore();
        }
    }
}
