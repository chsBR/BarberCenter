/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2004s Classe de Cliente
 */
public class Cliente {

    private int id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;

    public Cliente(int id, String nome, String email, String senha, TipoUsuario tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
