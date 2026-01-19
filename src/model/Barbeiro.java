/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2004s
 */
public class Barbeiro {

    private int id;
    private String nome;
    private double avaliacaoMedia;
    private int totalAvaliacoes;

    public Barbeiro(int id, String nome, double avaliacaoMedia, int totalAvaliacoes) {
        this.id = id;
        this.nome = nome;
        this.avaliacaoMedia = avaliacaoMedia;
        this.totalAvaliacoes = totalAvaliacoes;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }

    @Override
    public String toString() {
        return "Barbeiro: " + nome
                + " | Avaliação: " + avaliacaoMedia
                + " (" + totalAvaliacoes + " avaliações)";
    }
}
