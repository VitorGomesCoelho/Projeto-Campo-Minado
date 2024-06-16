package br.com.cod3r.campoMinado.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.campoMinado.excecao.ExplosaoException;

public class Campo {
	
	private final int linha;
	private final int coluna;
	
	public boolean aberto;
	public boolean marcado;
	private boolean minado;
	
	private List<Campo> vizinho = new ArrayList<>();

	Campo(int linha, int coluna){
		this.linha = linha;
		this.coluna = coluna;
	}
	
	boolean adicionarVizinho(Campo canditatoVizinho) {
		boolean linhaDiferente = linha != canditatoVizinho.linha;
		boolean colunaDiferente = coluna != canditatoVizinho.coluna;
		boolean diagonal = linhaDiferente && colunaDiferente;
		
		int deltaLinha = Math.abs(linha - canditatoVizinho.linha);
		int deltaColuna =Math.abs(coluna - canditatoVizinho.coluna);
		int deltaGeral = deltaLinha + deltaColuna;
		
		if(deltaGeral == 1 && !diagonal){
			vizinho.add(canditatoVizinho);
			return true;
		}
		else if(deltaGeral == 2 && diagonal) {
			vizinho.add(canditatoVizinho);
			return true;
		}
		else {
			return false;
		}
		
	}
	
	void alternarMarcacao() {
		if(!aberto) {
			marcado = !marcado;
		}
		
	}
	
	boolean abrir() {
		if(!marcado && !aberto) {
			aberto = true;
			
			if(minado) {
				throw new ExplosaoException();
			}
			
			if(vizinhancaSegura()) {
				vizinho.forEach(v-> v.abrir());
			}
			
		return true;
		}
		else {
			return false;
		}
		
	}

	boolean vizinhancaSegura() {
		return vizinho.stream().noneMatch(v-> v.minado);
	}

	void minar() {
		minado = true;
	}
	
	public boolean isMinado() {
		return minado;
	}
	
	public boolean isMarcado() {
		return marcado;
	}
	
	void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	public boolean isAberto() {
		return aberto;
	}

	public boolean isFechado() {
		return !isAberto();
	}
	
	public int getLinha() {
		return linha;
	}
	
	public int getColuna() {
		return coluna;
	}
	
	boolean objetivoAlcancado() {
		boolean desvendado = aberto && !minado;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}
	
	long minasNaVizinhança() {
		return vizinho.stream().filter(v -> v.minado).count();
	}
	
	void reiniciarJogo() {
		aberto = false;
		minado = false;
		marcado = false;
		
	}
	
	public String toString() {
		
		if(marcado) {
			return "X";
		} else if(aberto && minado) {
			return "*";
		} else if(aberto && minasNaVizinhança() > 0) {
			return Long.toString(minasNaVizinhança());
		} else if(aberto) {
			return " ";
		} else {
			return "?";
		}
	}
	
}
