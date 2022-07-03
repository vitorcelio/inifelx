package iniflex.model;

import java.math.BigDecimal;

public class Funcionario extends Pessoa implements Comparable<Funcionario> {
	
	private BigDecimal salario;
	private String funcao;

	public BigDecimal getSalario() {
		return salario.add(salario.multiply(new BigDecimal(0.1)));
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public int compareTo(Funcionario o) {
		return this.getNome().compareTo(o.getNome());
	}
	
	

}
