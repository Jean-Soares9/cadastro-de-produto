package model;

import java.time.LocalDate;

// TODO: Auto-generated Javadoc
/**
 * The Class JavaBeans.
 */
public class JavaBeans {

	/** The id. */
	private int id;
	
	/** The nome. */
	private String nome;
	
	/** The qtde. */
	private int qtde;
	
	/** The valor unit. */
	private float valor_unit;
	
	/** The data cad. */
	private LocalDate data_cad;
	
	
	/**
	 * Instantiates a new java beans.
	 */
	public JavaBeans() {
		super();
		}
	
		
	/**
	 * Instantiates a new java beans.
	 *
	 * @param id the id
	 * @param nome the nome
	 * @param qtde the qtde
	 * @param valor_unit the valor unit
	 * @param data_cad the data cad
	 */
	public JavaBeans(int id, String nome, int qtde, float valor_unit, LocalDate data_cad) {
		super();
		this.id = id;
		this.nome = nome;
		this.qtde = qtde;
		this.valor_unit = valor_unit;
		this.data_cad = data_cad;
	}


	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the nome.
	 *
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Sets the nome.
	 *
	 * @param nome the new nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Gets the qtde.
	 *
	 * @return the qtde
	 */
	public int getQtde() {
		return qtde;
	}
	
	/**
	 * Sets the qtde.
	 *
	 * @param qtde the new qtde
	 */
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}
	
	/**
	 * Gets the valor unit.
	 *
	 * @return the valor unit
	 */
	public float getValor_unit() {
		return valor_unit;
	}
	
	/**
	 * Sets the valor unit.
	 *
	 * @param valor_unit the new valor unit
	 */
	public void setValor_unit(float valor_unit) {
		this.valor_unit = valor_unit;
	}
	
	/**
	 * Gets the data cad.
	 *
	 * @return the data cad
	 */
	public LocalDate getData_cad() {
		return data_cad;
	}
	
	/**
	 * Sets the data cad.
	 *
	 * @param data_cad the new data cad
	 */
	public void setData_cad(LocalDate data_cad) {
		this.data_cad = data_cad;
	}
	
}

