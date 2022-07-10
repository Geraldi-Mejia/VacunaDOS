/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.domain;

/**
 *
 * @author admin
 */

public class Rounds {
    private int id;
    private String leader;
    private Group[] group;
    

    public Rounds() {
    }

    public Rounds(int id, String leader, Group[] group) {
        this.id = id;
        this.leader = leader;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Group[] getGroup() {
        return group;
    }

    public void setGroup(Group[] group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Rounds{" + "id=" + id + ", leader=" + leader + ", group=" + group.toString() + '}';
    }
    
    
    
    
}
