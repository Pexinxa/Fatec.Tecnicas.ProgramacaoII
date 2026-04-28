package br.edu.fatecpg.fatec;

import br.edu.fatecpg.fatec.model.Aluno;
import br.edu.fatecpg.fatec.model.Receita;
import br.edu.fatecpg.fatec.repository.AlunoRepository;
import br.edu.fatecpg.fatec.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FatecApplication implements CommandLineRunner {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private ReceitaRepository receitaRepository;

	public static void main(String[] args) {
		SpringApplication.run(FatecApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Aluno a1 = new Aluno("Ale",      "133.123.789", "124", "1623@gmail.com");
		Aluno a2 = new Aluno("Pedro",    "331.321.987", "621", "3241@gmail.com");
		Aluno a3 = new Aluno("Gabriela", "241.654.988", "311", "1311@gmail.com");
		alunoRepository.save(a1);
		alunoRepository.save(a2);
		alunoRepository.save(a3);

		List<Aluno> alunos = alunoRepository.findAll();
		alunos.forEach(a -> System.out.println("Nome: " + a.getNome() + ", Email: " + a.getEmail()));


		List<Aluno> alunos1 = alunoRepository.findAll();
		System.out.println("=== Todos os Alunos ===");
		alunos.forEach(a -> System.out.println(
				"ID: " + a.getId() + ", Nome: " + a.getNome() + ", Email: " + a.getEmail()
		));

		System.out.println("\n=== Busca por nome ===");
		alunoRepository.buscarPorNome("Ale").forEach(a -> System.out.println(
				"Nome: " + a.getNome() + ", Email: " + a.getEmail()
		));

		Long idPrimeiro = alunos.get(0).getId();
		System.out.println("\n=== Busca por ID " + idPrimeiro + " ===");
		alunoRepository.findById(idPrimeiro).ifPresentOrElse(
				a -> System.out.println("Nome: " + a.getNome() + ", Matrícula: " + a.getMatricula() + ", Email: " + a.getEmail()),
				() -> System.out.println("Aluno não encontrado")
		);

		Long idSegundo = alunos.get(1).getId();
		System.out.println("\n=== Deletando aluno ID " + idSegundo + " ===");
		alunoRepository.deleteById(idSegundo);
		System.out.println("Alunos restantes:");
		alunoRepository.findAll().forEach(a -> System.out.println(
				"ID: " + a.getId() + ", Nome: " + a.getNome() + ", Email: " + a.getEmail()
		));

		receitaRepository.save(new Receita("Bolo de Chocolate", "Sobremesa",       25.90, true));
		receitaRepository.save(new Receita("Frango Grelhado",   "Prato Principal", 38.50, false));
		receitaRepository.save(new Receita("Salada Caesar",     "Entrada",         19.90, true));
		receitaRepository.save(new Receita("Sopa de Cebola",    "Entrada",         15.00, false));
		receitaRepository.save(new Receita("Cheesecake",        "Sobremesa",       32.00, true));

		System.out.println("\n=== Todas as Receitas ===");
		receitaRepository.findAll().forEach(r -> System.out.printf(
				"ID: %d | %-20s | %-15s | R$ %.2f | Promoção: %s%n",
				r.getId(), r.getNome(), r.getCategoria(), r.getPreco(),
				r.getEmPromocao() ? "SIM" : "NÃO"
		));

		System.out.println("\n=== Receitas em Promoção ===");
		receitaRepository.buscarEmPromocao().forEach(r -> System.out.printf(
				"ID: %d | %-20s | R$ %.2f%n",
				r.getId(), r.getNome(), r.getPreco()
		));
	}
}