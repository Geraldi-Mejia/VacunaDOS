/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.domain;

/**
 *
 * @author admin
 */
public class Group {
    private String name;
    private String psycho;

    public Group() {
    }

    public Group(String name, String psycho) {
        this.name = name;
        this.psycho = psycho;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsycho() {
        return psycho;
    }

    public void setPsycho(String psycho) {
        this.psycho = psycho;
    }

    @Override
    public String toString() {
        return "Group{" + "name=" + name + ", psycho=" + psycho + '}';
    }
    
    
    
}
