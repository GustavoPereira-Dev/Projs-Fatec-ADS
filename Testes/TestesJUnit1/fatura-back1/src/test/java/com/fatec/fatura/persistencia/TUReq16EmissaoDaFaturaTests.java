package com.fatec.fatura.persistencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fatec.fatura.model.Fatura;

class TUReq16EmissaoDaFaturaTests {
	Logger logger = LogManager.getLogger(this.getClass());
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	Fatura fatura;

	// Grau de qualidades: Regressão (possível em teste de regressão), Resiliência (possível de refatoração), Rapidez e de fácil compressão
	// Pode ser o caso do teste de regressão
	
	// Executa antes de cada caso de teste
	@BeforeEach
	public void setup() {
		logger.info(">>>>> Setup iniciado");
	}
	
	// Executa depois de cada caso de teste
	@AfterEach
	public void teardown() {
		logger.info(">>>>>> teste finalizado");
	}
	
	// @BeforeAll e @AfterAll para respectivamente antes ou depois de todos os testes
	
	@Test
	void ct01_quando_dados_validos_fatura_nao_eh_nulo() {
		try {
			// dado que as informacoes de fatura sao validas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje
			assertNotNull(fatura);
			assertFalse(fatura.isCancelada());
			assertFalse(fatura.isPaga());
		} catch (Exception e) {
			logger.info(">>>>>> ct01 - nao deveria falhar => " + e.getMessage());
			fail("nao deveria falhar fatura valida");

		}

	}
	
	@Test
	void ct02_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura(null, dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			logger.info(">>>>>> ct02 - deveria falhar");
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}

	}
	
	@Test
	void ct03_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("390863600091", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			logger.info(">>>>>> ct3 - deveria falhar");
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}

	}
	
	@Test
	void ct04_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes de fatura sao invalidas
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("3908636000", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			logger.info(">>>>>> ct4 - deveria falhar");
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}

	}
	
	@Test
	void ct05_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas caracter invalido
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("3908636000%", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			logger.info(">>>>>> ct5 - deveria falhar");
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}

	}
	
	@Test
	void ct06_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas cpf vazio
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura("", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			logger.info(">>>>>> ct6 - deveria falhar");
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}

	}
	
	@Test
	void ct07_quando_dados_invalidos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas cpf com espaco (branco)
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("02/10/2026", formatter);
			fatura = new Fatura(" ", dataVencimento, "moveis planejados", "1000.50");
			// entao retorna mensagem de erro
			logger.info(">>>>>> ct7 - deveria falhar");
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			assertEquals("CPF invalido", e.getMessage());
		}

	}
	
	@Test
	void ct08_quando_dados_validos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas 31/02
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("31/02/2026", formatter);
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje
			logger.info(">>>>>> ct08 - data de vencimento => " + fatura.getDataVencimento());
			assertNotNull(fatura);
		} catch (Exception e) {
			fail("nao deveria falhar fatura valida");

		}

	}
	
	@Test
	void ct09_quando_dados_validos_retorna_msg_erro() {
		try {
			// dado que as informacoes sao invalidas formatacao da data
			// quando confirmo a fatura
			LocalDate dataVencimento = LocalDate.parse("/02/2026", formatter);
			fatura = new Fatura("39086360009", dataVencimento, "moveis planejados", "1000.50");
			// entao fatura é registrada com data de emisssao igual a data de hoje
			fail("deveria falhar fatura invalida");
		} catch (Exception e) {
			logger.info(">>>>>> ct09 - mensagem de erro => " + e.getMessage());
			assertEquals("Text '/02/2026' could not be parsed at index 0", e.getMessage());
		}

	}
	
}
