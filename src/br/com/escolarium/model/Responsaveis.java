package br.com.escolarium.model;

import java.time.LocalDate;

public class Responsaveis {
	private String nomeMae;

	private String nomePai;
	private String cpfMae;
	private String cpfPai;
	private String telefoneMae;
	private String telefonePai;
	private String responsavelFinaceiro;
	private String emailMae;
	private String emailPai;
	private String profissaoMae;
	private String profissaoPai;
	private String grauParentesco;
	private String estadoCivil;
	private LocalDate dataNascimentoMae;
	private LocalDate dataNascimentoPai;

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public String getCpfMae() {
		return cpfMae;
	}

	public void setCpfMae(String cpfMae) {
		this.cpfMae = cpfMae;
	}

	public String getCpfPai() {
		return cpfPai;
	}

	public void setCpfPai(String cpfPai) {
		this.cpfPai = cpfPai;
	}

	public String getTelefoneMae() {
		return telefoneMae;
	}

	public void setTelefoneMae(String telefoneMae) {
		this.telefoneMae = telefoneMae;
	}

	public String getTelefonePai() {
		return telefonePai;
	}

	public void setTelefonePai(String telefonePai) {
		this.telefonePai = telefonePai;
	}

	public String getResponsavelFinaceiro() {
		return responsavelFinaceiro;
	}

	public void setResponsavelFinaceiro(String responsavelFinaceiro) {
		this.responsavelFinaceiro = responsavelFinaceiro;
	}

	public String getEmailMae() {
		return emailMae;
	}

	public void setEmailMae(String emailMae) {
		this.emailMae = emailMae;
	}

	public String getEmailPai() {
		return emailPai;
	}

	public void setEmailPai(String emailPai) {
		this.emailPai = emailPai;
	}

	public String getProfissaoMae() {
		return profissaoMae;
	}

	public void setProfissaoMae(String profissaoMae) {
		this.profissaoMae = profissaoMae;
	}

	public String getProfissaoPai() {
		return profissaoPai;
	}

	public void setProfissaoPai(String profissaoPai) {
		this.profissaoPai = profissaoPai;
	}

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public LocalDate getDataNascimentoMae() {
		return dataNascimentoMae;
	}

	public void setDataNascimentoMae(LocalDate dataNascimentoMae) {
		this.dataNascimentoMae = dataNascimentoMae;
	}

	public LocalDate getDataNascimentoPai() {
		return dataNascimentoPai;
	}

	public void setDataNascimentoPai(LocalDate dataNascimentoPai) {
		this.dataNascimentoPai = dataNascimentoPai;
	}
}
