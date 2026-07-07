package br.com.escolarium.model;

public class Endereco {

	private String cidade;
	private String rua;
	private String bairro;
	private String cep;
	private String logradouro;
	private String numeroResidencia;
	private String pontoReferencia;
	private String uf;

	public Endereco(String cidade, String rua, String bairro, String cep, String logradouro, String numeroResidencia,
			String pontoReferencia, String uf) {
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
		this.cep = cep;
		this.logradouro = logradouro;
		this.numeroResidencia = numeroResidencia;
		this.pontoReferencia = pontoReferencia;
		this.uf = uf;
	}

	public String getCidade() {
		return cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumeroResidencia(String numeroResidencia) {
		this.numeroResidencia = numeroResidencia;
	}

	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCep() {
		return cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getNumeroResidencia() {
		return numeroResidencia;
	}

	public String getPontoReferencia() {
		return pontoReferencia;
	}

	public String getUf() {
		return uf;
	}

}
