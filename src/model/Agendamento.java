/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 2004s
 */
public class Agendamento {
    private int id;
    private Cliente cliente;
    private Barbeiro barbeiro;
    private Servico servico;
    private String dataHora;
    private String status;

    public Agendamento(int id, Cliente cliente, Barbeiro barbeiro, Servico servico, String dataHora) {
        this.id = id;
        this.cliente = cliente;
        this.barbeiro = barbeiro;
        this.servico = servico;
        this.dataHora = dataHora;
        this.status = "Agendado";
    }

    public void confirmar() {
        status = "Confirmado";
    }

    public void cancelar() {
        status = "Cancelado";
    }

    @Override
    public String toString() {
        return "Agendamento #" + id +
               "\nCliente: " + cliente.getNome() +
               "\nBarbeiro: " + barbeiro.getNome() +
               "\nServiço: " + servico.getNome() +
               "\nData/Hora: " + dataHora +
               "\nStatus: " + status;
    }
}
