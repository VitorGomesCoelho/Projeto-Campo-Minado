package br.com.cod3r.campoMinado.excecao;

public class ExplosaoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	void campoMinado() {
		throw new RuntimeException("Campo Minado!");
	}
	
}
