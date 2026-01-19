/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2004s
 */
public class Servico {
    private int id;
    private String nome;
    private double valor;
    private String duracaoMin;

    public Servico(int id, String nome, double valor, String duracaoMin) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.duracaoMin = duracaoMin;
    }

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return nome + " - R$" + valor;
    }
}
