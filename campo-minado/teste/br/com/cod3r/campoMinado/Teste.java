package br.com.cod3r.campoMinado;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.cod3r.campoMinado.modelo.Campo;

class Teste {
	
	//As annotations @Teste, informam que o m�todo associado a ela ser� testado pelo jUnit

	@Test
	void testarSeIgualADois() {
		int a = 1+1;
		
		//O m�todo assertEquals espera como parametros as seguintes op��es: Na primeira posi��o voc� passa o valor que espera receber,
		//na segunda o elemento que voc� espera que de esse retorno desejado
		assertEquals(2, a);
	}
	
	@Test
	void testarSeIgualATres() {
		int x = 2 + 10 -9;
		assertEquals(3, x);
	}
	
		
	

}
