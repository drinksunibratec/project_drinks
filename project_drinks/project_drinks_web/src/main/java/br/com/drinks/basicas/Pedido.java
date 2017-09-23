package br.com.drinks.basicas;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer codPedido;
	
	@ManyToOne
	@JoinColumn	(name = "codCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn (name = "codEstabelecimento")
	private Estabelecimento estabelecimento;
	
//	@ElementCollection(fetch=FetchType.LAZY)
//	private List<Produto> produtos;
	
	private Endereco endereco;
	
	@Column
	private double valorTotal;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido;
	
	@Enumerated (EnumType.STRING)
	private Status status;
	
	@Enumerated (EnumType.STRING)
	private Pagamento pagamento;

	public Integer getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(Integer codPedido) {
		this.codPedido = codPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

//	public List<Produto> getProdutos() {
//		return produtos;
//	}

//	public void setProdutos(List<Produto> produtos) {
//		this.produtos = produtos;
//	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Pedido(Integer codPedido, Cliente cliente, Estabelecimento estabelecimento, List<Produto> produtos,
			Endereco endereco, double valorTotal, Date dataPedido, Status status,Pagamento pagamento) {
		super();
		this.codPedido = codPedido;
		this.cliente = cliente;
		this.estabelecimento = estabelecimento;
//		this.produtos = produtos;
		this.endereco = endereco;
		this.valorTotal = valorTotal;
		this.dataPedido = dataPedido;
		this.status = status;
		this.pagamento = pagamento;
	}

	public Pedido() {
		super();
	}
}
