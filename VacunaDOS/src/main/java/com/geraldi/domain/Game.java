/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.domain;

/**
 *
 * @author admin
 */
public class Game {

    private String gameId;
    private String name;
    private String owner;
    private String password;
    private String[] players;
    private String[] psychos;
    private boolean[] psychoWin;
    private String status;
    private Rounds[] rounds;

    public Game() {
    }

    public Game(String gameId, String name, String owner, String password, String[] players, String[] psychos, boolean[] psychoWin, String status, Rounds[] rounds) {
        this.gameId = gameId;
        this.name = name;
        this.owner = owner;
        this.password = password;
        this.players = players;
        this.psychos = psychos;
        this.psychoWin = psychoWin;
        this.status = status;
        this.rounds = rounds;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getPlayers() {
        return players;
    }

    public void setPlayers(String[] players) {
        this.players = players;
    }

    public String[] getPsychos() {
        return psychos;
    }

    public void setPsychos(String[] psychos) {
        this.psychos = psychos;
    }

    public boolean[] getPsychoWin() {
        return psychoWin;
    }

    public void setPsychoWin(boolean[] psychoWin) {
        this.psychoWin = psychoWin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Rounds[] getRounds() {
        return rounds;
    }

    public void setRounds(Rounds[] rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        String result = "Game{" + "gameId=" + gameId + ", name=" + name + ", owner=" + owner + ", password=" + password + ", players=" + players + ", psychos=" + psychos + ", psychoWin=" + psychoWin + ", status=" + status ;
        for (int i = 0; i < rounds.length; i++) {
            String roundsT = rounds[i].toString();
                    result+=","+roundsT;
            
        }
        result+="}";

        return result;
                
    }

}
