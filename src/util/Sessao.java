/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import model.Cliente;
import model.Barbeiro;
import model.TipoUsuario;

/**
 *
 * @author 2004s Classe responsável por guardar cliente logado.
 */
public class Sessao {

    private static Cliente clienteLogado;
    private static TipoUsuario tipoUsuario;
    private static Barbeiro barbeiroLogado;

    public static void login(Cliente cliente) {
        clienteLogado = cliente;
        tipoUsuario = cliente.getTipoUsuario();
    }

    public static Cliente getCliente() {
        return clienteLogado;
    }

    public static void logout() {
        clienteLogado = null;
        tipoUsuario = null;
    }

    public static TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public static boolean isCliente() {
        return tipoUsuario == TipoUsuario.CLIENTE;
    }

    public static boolean isBarbeiro() {
        return tipoUsuario == TipoUsuario.BARBEIRO;
    }

    public static boolean isGerente() {
        return tipoUsuario == TipoUsuario.GERENTE;
    }

    public static void loginBarbeiro(Cliente cliente, Barbeiro barbeiro) {
        clienteLogado = cliente;
        barbeiroLogado = barbeiro;
        tipoUsuario = TipoUsuario.BARBEIRO;
    }

    public static Barbeiro getBarbeiro() {
        return barbeiroLogado;
    }
}
