package br.com.drinksapp.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Silvio Cedrim on 28/10/2017.
 */

public class Pedido implements Serializable{

    long codPedido;

    Usuarios usuario;

    Estabelecimento estabelecimento;

    List<PedidoProdutos> pedidoProdutos;

    long codEstabelecimento;

    long codUsuario;

    String dataPedido;

    String 	dataHoraPedido;

    double valorTotal;

    String status;

    String rua;

    int numero;

    String bairro;

    String cidade;

    String uf;

    String nome;

    String CEP;

    String pagamento;

    String bandeiraCartao;

    public String getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(String dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public String getBandeiraCartao() {
        return bandeiraCartao;
    }

    public void setBandeiraCartao(String bandeiraCartao) {
        this.bandeiraCartao = bandeiraCartao;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getComplemento() {
        return complemento;
    }

    private String complemento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCodEstabelecimento() {
        return codEstabelecimento;
    }

    public void setCodEstabelecimento(long codEstabelecimento) {
        this.codEstabelecimento = codEstabelecimento;
    }

    public long getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(long codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Pedido(long codPedido, Usuarios usuario, Estabelecimento estabelecimento, String dataPedido, double preco) {
        this.codPedido = codPedido;
        this.usuario = usuario;
        this.estabelecimento = estabelecimento;
        this.dataPedido = dataPedido;
        this.valorTotal = preco;
    }

    public Pedido() {

    }

    public List<PedidoProdutos> getPedidoProdutos() {
        return pedidoProdutos;
    }

    public void setPedidoProdutos(List<PedidoProdutos> pedidoProdutos) {
        this.pedidoProdutos = pedidoProdutos;
    }

    public long getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(long codPedido) {
        this.codPedido = codPedido;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }


}