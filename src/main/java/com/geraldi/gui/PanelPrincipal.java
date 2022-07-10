/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.gui;

import com.geraldi.connection.ConnectionAPI;
import com.geraldi.domain.ErrorMessageAPI;
import com.geraldi.domain.Game;
import com.geraldi.domain.GameHeader;
import com.geraldi.domain.Group;
import com.geraldi.domain.MessageAPI;
import com.geraldi.domain.Rounds;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    private ConnectionAPI con;
    private String urlAPI, playerName, gamePassword, gameName, gameId, groupProposal[];
    private GameHeader[] gameHeaders = null;
    private Game mainGame = null;
    private boolean psycho = false;

    private JLabel jblTitulo, jblInfo, jblInfo2, jblInfo3, jblInfo4, jblInfo5;
    private JTextField jtCampo, jtCampo2, jtCampo3, jtFilterValue;
    private JButton jbtnAceptar, jbtnCrear, jbtnGoSafe, jbtnGoWild, jbtnRound[], jbtnPlayers[], jbtnStart, jbtnRefreshList;
    private JPasswordField jtPassword;
    private JComboBox<String> jCombo, jComboFilter;

    public PanelPrincipal() {
        con = new ConnectionAPI();
        init();
    }

    public void init() {
        jblTitulo = new JLabel("Bienvenido a VacunaDOS");
        jblTitulo.setBounds(70, 5, 200, 40);
        jblTitulo.setVisible(true);

        jblInfo = new JLabel("Inserte la direccion del servidor");
        jblInfo.setBounds(45, 50, 200, 40);
        jblInfo.setVisible(true);

        jtCampo = new JTextField("https://vacunados.meseguercr.com");
        jtCampo.setBounds(25, 90, 240, 40);
        jtCampo.setVisible(true);

        jbtnAceptar = new JButton("Conectar");
        jbtnAceptar.setBounds(100, 130, 100, 20);
        jbtnAceptar.addActionListener(this);
        jbtnAceptar.setVisible(true);

        jblInfo2 = new JLabel("Info:");
        jblInfo2.setBounds(25, 150, 200, 20);
        jblInfo2.setVisible(true);

        jblInfo3 = new JLabel("");
        jblInfo3.setVisible(false);

        jblInfo4 = new JLabel("");
        jblInfo4.setVisible(false);
        jblInfo5 = new JLabel("");
        jblInfo5.setVisible(false);

        jtCampo2 = new JTextField();
        jtCampo2.setVisible(false);

        jtCampo3 = new JTextField();
        jtCampo3.setVisible(false);

        jtFilterValue = new JTextField();
        jtFilterValue.setVisible(false);

        jCombo = new JComboBox<String>();
        jCombo.setVisible(false);
        jCombo.addActionListener(this);

        jComboFilter = new JComboBox<String>();
        jComboFilter.setVisible(false);
        jComboFilter.addActionListener(this);

        jbtnCrear = new JButton();
        jbtnCrear.addActionListener(this);
        jbtnCrear.setVisible(false);

        jbtnGoSafe = new JButton();
        jbtnGoSafe.addActionListener(this);
        jbtnGoSafe.setVisible(false);

        jbtnGoWild = new JButton();
        jbtnGoWild.addActionListener(this);
        jbtnGoWild.setVisible(false);

        jbtnRound = new JButton[5];
        for (int i = 0; i < jbtnRound.length; i++) {
            jbtnRound[i] = new JButton();
            jbtnRound[i].setVisible(false);
            jbtnRound[i].addActionListener(this);
            this.add(jbtnRound[i]);
        }

        groupProposal = new String[0];
        jbtnPlayers = new JButton[10];
        for (int i = 0; i < jbtnPlayers.length; i++) {
            jbtnPlayers[i] = new JButton();
            jbtnPlayers[i].setVisible(false);
            jbtnPlayers[i].addActionListener(this);
            this.add(jbtnPlayers[i]);
        }

        jbtnStart = new JButton("Start");
        jbtnStart.addActionListener(this);
        jbtnStart.setVisible(false);

        jbtnRefreshList = new JButton("Refresh");
        jbtnRefreshList.addActionListener(this);
        jbtnRefreshList.setVisible(false);

        jtPassword = new JPasswordField();
        jtPassword.setVisible(false);

        this.add(jblTitulo);
        this.add(jblInfo);
        this.add(jtCampo);
        this.add(jtCampo2);
        this.add(jtCampo3);
        this.add(jbtnAceptar);
        this.add(jblInfo2);
        this.add(jblInfo3);
        this.add(jblInfo4);
        this.add(jCombo);
        this.add(jbtnCrear);
        this.add(jbtnGoSafe);
        this.add(jbtnGoWild);
        this.add(jbtnStart);
        this.add(jblInfo5);
        this.add(jbtnRefreshList);
        this.add(jtPassword);
        this.add(jComboFilter);
        this.add(jtFilterValue);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbtnAceptar) {
            actionBtnAceptar();

        } else if (e.getSource() == jbtnCrear) {
            actionBtnCrear();
        } else if (e.getSource() == jbtnGoSafe) {
            actionGoSafe();
        } else if (e.getSource() == jbtnGoWild) {
            actionGoWild();

        } else if (e.getSource() == jbtnStart) {
            actionBtnStart();

        } else if (e.getSource() == jbtnPlayers[1] || e.getSource() == jbtnPlayers[2] || e.getSource() == jbtnPlayers[3] || e.getSource() == jbtnPlayers[4] || e.getSource() == jbtnPlayers[5] || e.getSource() == jbtnPlayers[6] || e.getSource() == jbtnPlayers[7] || e.getSource() == jbtnPlayers[8] || e.getSource() == jbtnPlayers[9] || e.getSource() == jbtnPlayers[0]) {
            actionPlayers(e);
        } else if (e.getSource() == jbtnRefreshList) {
            actionRefreshList();

        } else if (e.getSource() == jComboFilter) {
            actionJFilter();
        }

    }

    public void actionBtnAceptar() {
        if (jbtnAceptar.getText().equals("Conectar")) {
            urlAPI = jtCampo.getText();
            Object response = null;
            try {
                response = con.getAllGames(urlAPI);

                if (response instanceof GameHeader[]) {
                    jblInfo2.setText("Info: Servidor Activo");
                    this.gameHeaders = (GameHeader[]) response;
                    selectGames();

                } else {
                    jblInfo2.setText("Info: Servidor No Diponible");
                }
            } catch (IOException ex) {
                Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jbtnAceptar.getText().equals("Jugar")) {
            int selec = jCombo.getSelectedIndex();

            playerName = jtCampo2.getText();
            gamePassword = String.valueOf(jtPassword.getPassword());
            System.out.println("game" + gamePassword);
            gameId = gameHeaders[selec].getGameId();
            gameName = gameHeaders[selec].getName();
            Object response = null;
            try {
                response = con.joinGame(urlAPI, playerName, gameId, gamePassword);

            } catch (IOException ex) {
                Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (response instanceof MessageAPI) {
                jblInfo4.setText("Info: " + ((MessageAPI) response).getMessage());
                jbtnCrear.setText("Refresh");
                try {
                    mainGame = (Game) con.getGame(urlAPI, playerName, gameId, gamePassword);

                } catch (IOException ex) {

                }
            } else if (response instanceof ErrorMessageAPI) {
                jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
                if (((ErrorMessageAPI) response).getError().equals("You are already part of this game")) {
                    jbtnAceptar.setEnabled(false);
                    jbtnCrear.setText("Refresh");
                    try {
                        mainGame = (Game) con.getGame(urlAPI, playerName, gameId, gamePassword);

                    } catch (IOException ex) {
                        Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } else if (jbtnAceptar.getText().equals("Crear")) {
            playerName = jtCampo2.getText();
            gamePassword = String.valueOf(jtPassword.getPassword());
            
            gameName = jtCampo3.getText();
            Object response = null;

            try {
                response = con.createGame(urlAPI, playerName, gameName, gamePassword);
            } catch (IOException ex) {
                Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (response instanceof Game) {
                gameId = ((Game) response).getGameId();
                mainGame = (Game) response;
                jbtnAceptar.setEnabled(false);
                jbtnCrear.setEnabled(true);
                jblInfo4.setText("Info: Juego Creado");

            } else {
                jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
            }

        }
    }

    public void actionBtnCrear() {
        if (jbtnCrear.getText().equals("Crear")) {
            jblInfo.setText("Game Name:");
            jblInfo.setBounds(25, 50, 200, 40);
            jCombo.setVisible(false);
            jbtnRefreshList.setVisible(false);
            jtCampo3.setText("Nombre");
            jtCampo3.setBounds(25, 90, 240, 40);
            jtCampo3.setVisible(true);
            jbtnCrear.setText("Refresh");
            jbtnCrear.setEnabled(false);
            jbtnAceptar.setText("Crear");
        } else if (jbtnCrear.getText().equals("Refresh")) {
            refresh();
        }
    }

    public void actionGoWild() {
        Object response = null;
        response = con.go(urlAPI, playerName, gameId, gamePassword, true);

        if (response instanceof MessageAPI) {
            jblInfo4.setText("Info: " + ((MessageAPI) response).getMessage());
        } else {
            jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
        }
    }

    public void actionGoSafe() {
        Object response = null;

        response = con.go(urlAPI, playerName, gameId, gamePassword, false);

        if (response instanceof MessageAPI) {
            jblInfo4.setText("Info: " + ((MessageAPI) response).getMessage());
        } else {
            jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
        }
    }

    public void actionBtnStart() {
        Object response = null;

        try {
            response = con.startGame(urlAPI, playerName, gameId, gamePassword);
        } catch (MalformedURLException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (response instanceof MessageAPI) {
            jblInfo4.setText("Info: " + ((MessageAPI) response).getMessage());
        } else if (response instanceof ErrorMessageAPI) {
            jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
        }
    }

    public void actionPlayers(ActionEvent e) {
        JButton temp = (JButton) e.getSource();

        if (mainGame.getStatus().equals("leader")) {
            Rounds[] rounds = mainGame.getRounds();
            temp.setEnabled(false);
            temp.setBackground(Color.blue);
            int lastRound = rounds.length;
            if (lastRound != 0) {
                if (playerName.equals(rounds[lastRound - 1].getLeader())) {

                    addGroupElement(temp.getText());

                    Object response = null;

                    response = con.sendGroup(urlAPI, playerName, gameId, gamePassword, groupProposal);

                    if (response instanceof MessageAPI) {
                        jblInfo4.setText("Info: " + ((MessageAPI) response).getMessage());
                    } else if (response instanceof ErrorMessageAPI) {
                        jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
                    }
                } else {
                    jblInfo4.setText("Info: No puede proponer grupo");
                }
            }
        } else {
            jblInfo4.setText("Info: Game is not in the groups stage ");

        }
    }

    public void actionRefreshList() {
        Object response = null;
        jCombo.removeAllItems();
        if (jComboFilter.getSelectedItem().toString().equals("All")) {
            try {
                response = con.getAllGames(urlAPI);

                if (response instanceof GameHeader[]) {
                    this.gameHeaders = (GameHeader[]) response;
                }

                for (int i = 0; i < gameHeaders.length; i++) {
                    GameHeader gameHeader = gameHeaders[i];
                    jCombo.addItem(gameHeader.getName());

                }
            } catch (IOException ex) {

            }
        } else {
            try {
                response = con.getFilteredGames(urlAPI, jComboFilter.getSelectedItem().toString(), jtFilterValue.getText());

                if (response instanceof GameHeader[]) {
                    this.gameHeaders = (GameHeader[]) response;
                }

                for (int i = 0; i < gameHeaders.length; i++) {
                    GameHeader gameHeader = gameHeaders[i];
                    jCombo.addItem(gameHeader.getName());

                }
                if (gameHeaders.length == 0) {
                    jblInfo4.setText("Info: Filtro sin elementos");
                } else {
                    jblInfo4.setText("Info: Filtrado");
                }

            } catch (IOException ex) {
                Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void actionJFilter() {
        if (jComboFilter.getSelectedItem().toString().equals("owner") || jComboFilter.getSelectedItem().toString().equals("gameId") || jComboFilter.getSelectedItem().toString().equals("status")) {
            jtFilterValue.setVisible(true);
        } else {
            jtFilterValue.setVisible(false);
        }
    }

    public void selectGames() {
        jtCampo.setVisible(false);
        jblInfo.setText("Seleccione un Juego y su filtro:");
        jblInfo.setBounds(25, 50, 200, 40);

        for (int i = 0; i < gameHeaders.length; i++) {
            GameHeader gameHeader = gameHeaders[i];
            jCombo.addItem(gameHeader.getName());

        }

        jCombo.setBounds(25, 140, 170, 20);
        jCombo.setVisible(true);

        jblInfo2.setText("Password:");
        jblInfo2.setBounds(25, 170, 225, 20);
        jblInfo2.setVisible(true);

        jtPassword.setText("qwerty");
        jtPassword.setToolTipText("Opcional");
        jtPassword.setBounds(25, 200, 225, 30);
        jtPassword.setVisible(true);

        jblInfo3.setText("NickName:");
        jblInfo3.setBounds(25, 240, 200, 20);
        jblInfo3.setVisible(true);

        jtCampo2.setText("Geraldi");
        jtCampo2.setBounds(25, 265, 225, 30);
        jtCampo2.setVisible(true);

        jbtnAceptar.setText("Jugar");
        jbtnAceptar.setBounds(25, 310, 70, 20);
        jbtnAceptar.setVisible(true);

        jbtnCrear.setText("Crear");
        jbtnCrear.setBounds(100, 310, 100, 20);
        jbtnCrear.setVisible(true);

        jbtnRefreshList.setText("🔄");
        jbtnRefreshList.setBounds(200, 140, 50, 20);
        jbtnRefreshList.setVisible(true);

        jblInfo4.setText("Info:");
        jblInfo4.setBounds(25, 330, 300, 20);
        jblInfo4.setVisible(true);

        String[] filtros = {"All", "owner", "gameId", "status"};
        for (int i = 0; i < filtros.length; i++) {
            jComboFilter.addItem(filtros[i]);
        }

        jComboFilter.setBounds(25, 100, 70, 20);
        jComboFilter.setVisible(true);
        jtFilterValue.setBounds(100, 100, 150, 20);

    }

    public void refresh() {

        groupProposal = new String[0];
        jComboFilter.setVisible(false);
        jtFilterValue.setVisible(false);
        jblTitulo.setText("Game: " + gameName + "   Nickname: " + playerName);
        jblTitulo.setBounds(10, 5, 300, 40);
        jtCampo.setVisible(false);
        jtPassword.setVisible(false);
        jtCampo2.setVisible(false);
        jtCampo3.setVisible(false);
        jbtnAceptar.setVisible(false);
        jCombo.setVisible(false);
        jbtnStart.setVisible(false);
        jbtnGoSafe.setVisible(false);
        jbtnGoWild.setVisible(false);
        jbtnRefreshList.setVisible(false);

        Object response = null;
        try {
            response = con.getGame(urlAPI, playerName, gameId, gamePassword);
        } catch (IOException ex) {
            Logger.getLogger(PanelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (response instanceof Game) {
            mainGame = (Game) response;
            imPsycho();
            jblInfo.setText("Status: " + mainGame.getStatus());

            getGroupLeader();

            jblInfo.setBounds(100, 20, 200, 40);

            setGo();
            setRoundPos();
            setPlayerPos();

            jblInfo4.setText("Info: ");

            isEnded();

        } else if (response instanceof ErrorMessageAPI) {
            jblInfo4.setText("Info: " + ((ErrorMessageAPI) response).getError());
            jblInfo4.setBounds(25, 50, 300, 20);
        }

    }

    public void getGroupLeader() {
        if (mainGame.getStatus().equals("leader")) {
            Rounds[] rounds = mainGame.getRounds();
            int lastRound = rounds.length;
            if (lastRound != 0) {
                jblInfo.setText("Status: " + mainGame.getStatus() + " (" + rounds[lastRound - 1].getLeader() + ")");

            }
        }
    }

    //determina si un juego ya termino y quien lo gano
    public void isEnded() {
        if (mainGame.getStatus().equals("ended")) {
            int psyconWins = 0;
            boolean tempWins[] = mainGame.getPsychoWin();

            for (int i = 0; i < tempWins.length; i++) {
                if (tempWins[i]) {
                    psyconWins += 1;
                }
            }

            if (psyconWins == 3) {
                jblInfo4.setText("Info: Psychos Wins");
            } else {
                jblInfo4.setText("Info: Ejemplares Wins");
            }

        }
    }

    public void setGo() {
        Rounds[] rounds = mainGame.getRounds();
        String grupo[] = null;

        if (mainGame.getStatus().equals("rounds")) {
            int lastRound = rounds.length;
            if (lastRound != 0) {
                Group[] g = rounds[lastRound - 1].getGroup();
                grupo = new String[g.length];
                for (int i = 0; i < g.length; i++) {
                    grupo[i] = g[i].getName();
                }

            }
            if (grupo != null && existe(playerName, grupo)) {
                jbtnGoSafe.setText("Go");
                jbtnGoSafe.setBackground(Color.green);
                jbtnGoSafe.setBounds(75, 60, 60, 60);
                jbtnGoSafe.setVisible(true);

                if (psycho) {
                    //si es psyco se activa esto
                    jbtnGoWild.setText("Go");
                    jbtnGoWild.setBackground(Color.red);
                    jbtnGoWild.setBounds(145, 60, 60, 60);
                    jbtnGoWild.setVisible(true);
                }
            }

        }
    }

    public void setPlayerPos() {
        int pos = 190;
        String players[] = mainGame.getPlayers();
        jblInfo3.setText("Players: ");
        jblInfo3.setBounds(10, pos, 70, 20);

        for (int i = 0; i < players.length; i++) {
            jbtnPlayers[i].setBounds(100, pos, 100, 20);
            jbtnPlayers[i].setText(players[i]);
            jbtnPlayers[i].setBackground(Color.white);
            jbtnPlayers[i].setEnabled(true);

            if (psycho && existe(players[i], mainGame.getPsychos())) {
                jbtnPlayers[i].setForeground(Color.red);
            }

            jbtnPlayers[i].setVisible(true);
            pos += 30;
        }

        jbtnCrear.setBounds(10, pos, 100, 20);

        if (mainGame.getStatus().equals("lobby") && playerName.equals(mainGame.getOwner())) {
            jbtnStart.setBounds(130, pos, 100, 20);
            jbtnStart.setVisible(true);
        }

        jblInfo4.setBounds(25, pos + 25, 300, 20);

        if (psycho) {
            jblInfo5.setBounds(25, pos + 50, 400, 20);
            jblInfo5.setText("Im psycho");

            jblInfo5.setVisible(true);
        }

    }

    public void setRoundPos() {
        jblInfo2.setText("Rounds:");
        jblInfo2.setBounds(10, 135, 70, 30);
        boolean[] wins = mainGame.getPsychoWin();
        int pos = 60;
        for (int i = 0; i < 5; i++) {
            jbtnRound[i].setBounds(pos, 135, 30, 30);
            jbtnRound[i].setVisible(true);
            pos += 35;
        }
        for (int i = 0; i < wins.length; i++) {

            Rounds[] rounds = mainGame.getRounds();

            int lastRound = rounds.length;

            if (wins[i]) {
                jbtnRound[i].setBackground(Color.red);
            } else {
                jbtnRound[i].setBackground(Color.green);
            }
            String namesG = "Group: ";
            for (int j = 0; j < rounds[i].getGroup().length; j++) {
                namesG += " " + rounds[i].getGroup()[j].getName();
            }

            jbtnRound[i].setToolTipText(namesG);

        }

    }

    public boolean existe(String cadena, String[] arreglo) {
        boolean exist = false;
        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i].equalsIgnoreCase(cadena)) {
                exist = true;
            }
        }

        return exist;
    }

    public void imPsycho() {
        psycho = existe(playerName, mainGame.getPsychos());
    }

    public void addGroupElement(String player) {
        String temp[] = new String[groupProposal.length + 1];

        for (int i = 0; i < temp.length; i++) {
            if (i < groupProposal.length) {
                temp[i] = groupProposal[i];
            } else {
                temp[i] = player;
            }

        }

        groupProposal = temp;

    }
}
