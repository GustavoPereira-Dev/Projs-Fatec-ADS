package br.edu.fateczl.AulaSpringData;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.fateczl.AulaSpringData.model.Categoria;
import br.edu.fateczl.AulaSpringData.model.Depto;
import br.edu.fateczl.AulaSpringData.model.Empregado;
import br.edu.fateczl.AulaSpringData.model.Projeto;
import br.edu.fateczl.AulaSpringData.repository.ICategoriaRepository;
import br.edu.fateczl.AulaSpringData.repository.IDeptoRepository;
import br.edu.fateczl.AulaSpringData.repository.IEmpregadoRepository;
import br.edu.fateczl.AulaSpringData.repository.IProjetoRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
class AulaSpringDataApplicationTests {

	@Autowired
	private ICategoriaRepository cRep;
	
	@Autowired
	private IEmpregadoRepository empRep;
	
	@Autowired
	private IProjetoRepository projRep;
	
	@Autowired
	private IDeptoRepository dRep;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void deptoRepository() {
		List<Depto> deptos = dRep.findDeptosSigla();
		deptos.forEach(depto -> System.out.println(depto));
		System.out.println("=============================");
		
		Depto depto = dRep.findDeptoSigla(102);
		System.out.println(depto);
		
		String novaSigla = dRep.sp_depto_sigla(101);
		System.out.println(novaSigla);
	}
	
	@Test
	void projetoRepository() {
		Projeto p1 = new Projeto(9991, "Des. Modulo Cadastro", 30);
		Projeto p2 = new Projeto(9992, "Des. Modulo Consultas", 15);
		Projeto p3 = new Projeto(9993, "Des. Telas Casdatro", 35);
		Projeto p4 = new Projeto(9994, "Des. Telas Consultas", 18);

		projRep.save(p1);
		projRep.save(p2);
		projRep.save(p3);
		projRep.save(p4);
		
		int horas = 20;
		List<Projeto> projs = projRep.findProjetosMaisHoras(horas);
		projs.forEach(p -> System.out.println(p));
	}
	
	@Test
	void empregadoRepository() {
		Empregado e1 = new Empregado();
		e1.setCargo("Desenvolvedor Jr.");
		e1.setDataAdmissao(LocalDate.of(2025, 2, 15));
		e1.setMatricula(100001);
		e1.setNomeEmpregado("Fulano de Tal");
		e1.setSalario(BigDecimal.valueOf(5000.00d));
		e1.setSexo("M");
		e1.setTelefone("999991111");
		e1.setDepto(new Depto(101, "Team 03", "T03"));

		Empregado e2 = new Empregado();
		e2.setCargo("Desenvolvedor Pl.");
		e2.setDataAdmissao(LocalDate.of(2025, 2, 10));
		e2.setMatricula(100002);
		e2.setNomeEmpregado("Cicrana de Tal");
		e2.setSalario(BigDecimal.valueOf(12000.00d));
		e2.setSexo("F");
		e2.setTelefone("999991112");
		e2.setDepto(new Depto(102, "Team 02", "T02"));

		Empregado e3 = new Empregado();
		e3.setCargo("Desenvolvedor Jr.");
		e3.setDataAdmissao(LocalDate.of(2025, 2, 10));
		e3.setMatricula(100003);
		e3.setNomeEmpregado("Beltrano de Tal");
		e3.setSalario(BigDecimal.valueOf(5000.00d));
		e3.setSexo("M");
		e3.setTelefone("999991113");
		e3.setDepto(new Depto(102, "Team 02", "T02"));

		
		empRep.save(e1);
		empRep.save(e2);
		empRep.save(e3);
	}
	
	@Test
	@Transactional
	void empregadoFinds() {
		Empregado empNome = empRep.findByNomeEmpregado("Cicrana de Tal");
		System.out.println(empNome);
		System.out.println("============================");
		
		List<Empregado> empSalCincoMil = 
				empRep.findBySalario(BigDecimal.valueOf(5000.00d));
		empSalCincoMil.forEach(emp -> System.out.println(emp));
		System.out.println("============================");
		
		Empregado empData = 
				empRep.findFirstByDataAdmissao(LocalDate.of(2025, 2, 10));
		System.out.println(empData);

		System.out.println("============================");
		
		empSalCincoMil = 
				empRep.findBySalarioOrderByNomeEmpregadoAsc(BigDecimal.valueOf(5000.00d));
		empSalCincoMil.forEach(emp -> System.out.println(emp));
		System.out.println("============================");
	}

	@Test
	@Transactional
	void empregadoJoin() {
		List<Empregado> emps = empRep.findEmpregadoDepto("T02");
		emps.forEach(emp -> System.out.println(emp));
	}
	
	@Test
	void categoriaRepository() {
		Categoria c1 = new Categoria(1, "Categora C1");
		Categoria c2 = new Categoria(2, "Categoria C2");
		cRep.save(c1);
		cRep.save(c2);
		
		c1.setTipo("Categoria C1");
		cRep.save(c1);
		
		List<Categoria> categorias = cRep.findAll();
		for (Categoria c : categorias) {
			System.out.println(c);
		}

		System.out.println("=========================");
		
		int idCat = 2;
		
		Categoria categoria = cRep.findById(idCat).get();
		System.out.println(categoria);
		
		Categoria c3 = new Categoria(3, "Categoria C3");
		cRep.save(c3);
		
		cRep.delete(c2);
	}
}
