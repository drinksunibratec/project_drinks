package basicas;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Produto {

	public Produto() {
		super();
	}
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Integer codigo;
	
	@Column(length = 150, nullable = false)
	private String nome;
	
	@Column(length = 400, nullable = false)
	private String descricao;
	
	@Column(length = 4, nullable = false)
	private Float preco;
	
	@Column(length = 150, nullable = false)
	private String categoria;
}
