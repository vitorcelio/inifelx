package iniflex;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import iniflex.model.Funcionario;

public class Principal {

	private static AtomicLong longId = new AtomicLong();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		List<Funcionario> funcionarios = new ArrayList<>();

		String continuar = "S";
		do {

			switch (menu()) {
			case 1:
				String decisao = "S";
				while (decisao.equalsIgnoreCase("S")) {
					Funcionario funcionario = new Funcionario();
					funcionario.setId(longId.getAndIncrement());

					System.out.print("Digite o nome do funcion�rio: ");
					funcionario.setNome(scanner.next());

					System.out.print("Digite a data de nascimento do funcion�rio: ");
					String data[] = scanner.next().split("/");
					LocalDate dataCerta = LocalDate.of(Integer.parseInt(data[2]), Integer.parseInt(data[1]),
							Integer.parseInt(data[0]));
					funcionario.setDataNascimento(dataCerta);

					System.out.print("Digite o sal�rio do funcion�rio: ");
					funcionario.setSalario(scanner.nextBigDecimal());

					System.out.println("Escolha a fun��o do funcion�rio: ");
					funcionario.setFuncao(menuFuncao());

					funcionarios.add(funcionario);

					System.out.print("Deseja cadastrar mais algum funcion�rio?(S/N): ");
					decisao = scanner.next();
				}

				break;
			case 2:
				Map<String, List<Funcionario>> funcoes = new HashMap<>();
				List<Funcionario> funcionariosOperador = new ArrayList<Funcionario>();
				List<Funcionario> funcionariosCoordenador = new ArrayList<Funcionario>();
				List<Funcionario> funcionariosDiretor = new ArrayList<Funcionario>();
				List<Funcionario> funcionariosRecepcionista = new ArrayList<Funcionario>();
				List<Funcionario> funcionariosContador = new ArrayList<Funcionario>();
				List<Funcionario> funcionariosGerente = new ArrayList<Funcionario>();
				List<Funcionario> funcionariosEletricista = new ArrayList<Funcionario>();

				funcionarios.forEach(func -> {
					if (func.getFuncao().equals("Operador")) {
						funcionariosOperador.add(func);
						funcoes.put("Operador", funcionariosOperador);
					} else if (func.getFuncao().equals("Coordenador")) {
						funcionariosCoordenador.add(func);
						funcoes.put("Coordenador", funcionariosCoordenador);
					} else if (func.getFuncao().equals("Diretor")) {
						funcionariosDiretor.add(func);
						funcoes.put("Diretor", funcionariosDiretor);
					} else if (func.getFuncao().equals("Recepcionista")) {
						funcionariosRecepcionista.add(func);
						funcoes.put("Recepcionista", funcionariosRecepcionista);
					} else if (func.getFuncao().equals("Contador")) {
						funcionariosContador.add(func);
						funcoes.put("Contador", funcionariosContador);
					} else if (func.getFuncao().equals("Gerente")) {
						funcionariosGerente.add(func);
						funcoes.put("Gerente", funcionariosGerente);
					} else if (func.getFuncao().equals("Eletricista")) {
						funcionariosEletricista.add(func);
						funcoes.put("Eletricista", funcionariosEletricista);
					}
				});

				List<Funcionario> list = funcoes.get(menuFuncao());

				System.out.println("NOME | DATA NASCIMENTO | SAL�RIO | FUNC�O");
				list.forEach(func -> {
					System.out.println(func.getNome() + " | "
							+ func.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
							+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(func.getSalario())
									.toString()
							+ " | " + func.getFuncao());
				});
				break;
			case 3:
				int mesInt = menuAniversario();
				System.out.println("NOME | DATA NASCIMENTO | SAL�RIO | FUNC�O");
				funcionarios.forEach(func -> {

					String mesFunc[] = func.getDataNascimento().toString().split("-");
					if (Integer.parseInt(mesFunc[1]) == mesInt) {
						System.out.println(func.getNome() + " | "
								+ func.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
								+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(func.getSalario())
										.toString()
								+ " | " + func.getFuncao());
					}
				});

				break;
			case 4:
				int anoCompara = 0;
				Map<String, Funcionario> funcionarioMap = new HashMap<String, Funcionario>();
				for (int i = 0; i < funcionarios.size(); i++) {
					String[] anoFunc = funcionarios.get(i).getDataNascimento().toString().split("-");

					int ano = LocalDate.now().getYear() - Integer.parseInt(anoFunc[0]);

					if (ano > anoCompara) {
						anoCompara = ano;
						funcionarioMap.put("idade", funcionarios.get(i));
					}
				}

				Funcionario funcionario = funcionarioMap.get("idade");
				System.out.println("Funcion�rio com maior idade: \n ----------------------------");
				System.out.println("nome: " + funcionario.getNome());
				System.out.println("idade: " + anoCompara + " anos");
				break;
			case 5:

				funcionarios.sort((f1, f2) -> {
					return f1.getNome().compareTo(f2.getNome());
				});

				System.out.println("NOME | DATA NASCIMENTO | SAL�RIO | FUNC�O");
				funcionarios.forEach(func -> {
					System.out.println(func.getNome() + " | "
							+ func.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " | "
							+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(func.getSalario())
									.toString()
							+ " | " + func.getFuncao());
				});

				break;
			case 6:

				Double totalSalarios = 0.0;

				for (int i = 0; i < funcionarios.size(); i++) {
					totalSalarios += funcionarios.get(i).getSalario().doubleValue();
				}

				System.out.println("---------------------------------");
				System.out.println("Sal�rio total dos funcion�rios: " + totalSalarios);
				System.out.println("---------------------------------");
				break;
			case 7:
				System.out.println("NOME | QUANTIDADE DE SAL�RIOS");
				funcionarios.forEach(func -> {
					System.out.println(func.getNome() + " | " + (func.getSalario().divide(new BigDecimal(1212))));
				});
				break;
			case 8:
				System.out.println("Remover funcion�rio: \n---------------------");
				int excluir;
				funcionarios.forEach(func -> {
					System.out.println(func.getId() + ": " + func.getNome());
				});
				System.out.print("Qual funcion�rio?: ");
				excluir = scanner.nextInt();
				
				funcionarios.remove(excluir);
				break;
			}

			System.out.print("Deseja continuar a aplica��o?(S/N): ");
			continuar = scanner.next();

		} while (continuar.equalsIgnoreCase("S"));
	}

	public static Integer menu() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("---------------------------");
		System.out.println("1. Cadastrar funcion�rio");
		System.out.println("2. Mostrar Lista de funcion�rios por fun��o");
		System.out.println("3. Funcion�rios que fazem anivers�rio em determinado m�s");
		System.out.println("4. Funcion�rio de maior idade");
		System.out.println("5. Funcion�rios em ordem alfab�tica");
		System.out.println("6. Total de sal�rios dos funcion�rios");
		System.out.println("7. Sal�rios m�nimos de cada funcion�rio");
		System.out.println("8. Remover funcion�rio");
		System.out.println("---------------------------");
		System.out.print("Resposta: ");
		int menu = scanner.nextInt();

		if (menu > 0 && menu < 9) {
			return menu;
		} else {
			menu();
		}

		return null;
	}

	public static String menuFuncao() {
		Scanner scanner = new Scanner(System.in);
		String menuFuncao = "";

		System.out.println("---------------------------");
		System.out.println("1. Operador");
		System.out.println("2. Coordenador");
		System.out.println("3. Diretor");
		System.out.println("4. Recepcionista");
		System.out.println("5. Contador");
		System.out.println("6. Gerente");
		System.out.println("7. Eletricista");
		System.out.println("---------------------------");
		System.out.print("Resposta: ");
		int menu = scanner.nextInt();

		if (menu > 0 && menu < 8) {
			switch (menu) {
			case 1:
				menuFuncao = "Operador";
				break;
			case 2:
				menuFuncao = "Coordenador";
				break;
			case 3:
				menuFuncao = "Diretor";
				break;
			case 4:
				menuFuncao = "Recepcionista";
				break;
			case 5:
				menuFuncao = "Contador";
				break;
			case 6:
				menuFuncao = "Gerente";
				break;
			case 7:
				menuFuncao = "Eletricista";
				break;
			}

		} else {
			menuFuncao();
		}

		return menuFuncao;
	}

	public static Integer menuAniversario() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Funcion�rios que fazem anivers�rio em: ");
		System.out.println("1. Janeiro");
		System.out.println("2. Fevereiro");
		System.out.println("3. Mar�o");
		System.out.println("4. Abril");
		System.out.println("5. Maio");
		System.out.println("6. Junho");
		System.out.println("7. Julho");
		System.out.println("8. Agosto");
		System.out.println("9. Setembto");
		System.out.println("10. Outubto");
		System.out.println("11. Novembro");
		System.out.println("12. Dezembro");
		System.out.print("Resposta: ");
		int mes = scanner.nextInt();

		if (mes > 0 && mes < 13) {
			return mes;
		} else {
			menuAniversario();
		}

		return null;
	}

}
