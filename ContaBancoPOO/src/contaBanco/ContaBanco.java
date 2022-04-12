package contaBanco;

import java.util.Random;
import java.util.Scanner;

public class ContaBanco {

	private double saldo;
	private boolean status; //false = conta fechada       true = conta aberta
	public String numConta;	
	protected String tipoConta;
	private String nomeDono;
	private int qtdDepos;
	private boolean statusMensal;

	public ContaBanco() {
		this.saldo = 0;
		this.status = false;
		this.qtdDepos = 0;
	}

	public String getTipoConta() {
		return this.tipoConta;
	}

	public void setTipoConta() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual tipo de conta deseja abrir?\n"
				+ "(Digite 'cp' para conta poupança e 'cc' para conta corrente.)");
		this.tipoConta = sc.nextLine();

		while(!(this.tipoConta.contentEquals("cp")) && !(this.tipoConta.contentEquals("cc"))){
			System.out.println("Tipo de conta inválido.\n"
					+ "(Digite 'cp' para conta poupança e 'cc' para conta corrente.)");
			this.tipoConta = sc.next();

		}

	}

	public String getNomeDono() {
		return nomeDono;
	}

	public void setNomeDono() {
		Scanner sc = new Scanner(System.in);
		this.nomeDono = sc.nextLine();

	}

	public String nomeFormatado() {
		String[] s = new String[3];
		s[0] = this.nomeDono.substring(0, this.nomeDono.indexOf(" "));
		s[1] = this.nomeDono.substring(this.nomeDono.lastIndexOf(" "), this.nomeDono.length());
		s[2] = s[0] + s[1];
		return s[2];
	}

	public String getSaldo() {
		if(this.status == true) {
			return "Saldo atual: " + this.saldo;
		}else {
			return "Para verificar o saldo primeiramente abra uma conta";
		}
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumConta() {
		if(this.status == true) {
			return "Número da conta: " + this.numConta;
		}else {
			return "Para verificar o número da conta primeiramente abra uma conta.";
		}
	}

	public void setNumConta() {
		Random rnd = new Random();
		this.numConta = Integer.toString(Math.abs((rnd.nextInt())));
	}

	public String getStatusMensal() {
		if(this.status == true) {
			if(this.statusMensal == true) {
				return "Mensalidade não está pendente.";
			}else {
				return "Mensalidade está pendente.";
			}
		}else {
			return "Para verificar mensalidade primeiramente abra uma conta";
		}
	}

	public void setStatusMensal(boolean statusMensal) {
		this.statusMensal = statusMensal;
	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void  abrirConta() {
		if(this.status == false) {
			this.setTipoConta();
			this.setStatus(true);

			System.out.println("Informe o nome do propietário da conta: ");
			this.setNomeDono();

			if(this.tipoConta.contentEquals("cc")) {
				System.out.println("Na abertura de uma conta corrente te presenteamos com 50 reais!");
				System.out.println("Saldo atual: " + this.depositar(50));
				System.out.println("");
			}else if(this.tipoConta.contentEquals("cp")){
				System.out.println("Na abertura de uma conta poupança te presenteamos com 150 reais!");
				System.out.println("Saldo atual: " + this.depositar(150));
				System.out.println("");
			}	
			System.out.println("Conta aberta com sucesso!");
			setNumConta();
			System.out.println(getNumConta());
		}else {
			System.out.println("Conta já aberta.");
		}
	}

	public void fecharConta() {
		if(this.status == true) {
			if(this.saldo >= 0) {
				Scanner sc = new Scanner(System.in);
				System.out.println("");
				if(this.saldo > 0) {
					System.out.println("Para fechar a conta é necessário o saque de todo o dinheiro depositado."
							+ "\nDeseja realizar o saque de todo o saldo?"
							+ "\n(Digite 1 para sim e 2 para não)"
							+ "\n1) sim"
							+ "\n2) não");
					String confirm = sc.next();

					while(!(confirm.contentEquals("1")) && !(confirm.contentEquals("2"))) {
						System.out.println("Opção inválida inserida, insira novamente."
								+ "\n(Digite 1 para sim e 2 para não)"
								+ "\n1) sim"
								+ "\n2) não");
						confirm = sc.next();
					}

					switch (confirm) {
					case "1":
						this.saldo = 0;
						this.status = false;
						System.out.println("Saldo zerado."
								+ "\nConta fechada com sucesso.");
						break;

					case "2":
						System.out.println("Operação de fechamento de conta cancelada.");
						break;
					}
				}else {
					System.out.println("Confirmar fechamento de conta?"
							+ "\n(Digite 1 para sim e 2 para não)"
							+ "\n1) sim"
							+ "\n2) não");
					String confirm = sc.next();

					while(!(confirm.contentEquals("1")) && !(confirm.contentEquals("2"))) {
						System.out.println("Opção inválida inserida, insira novamente."
								+ "\n(Digite 1 para sim e 2 para não)"
								+ "\n1) sim"
								+ "\n2) não");
						confirm = sc.next();
					}

					switch (confirm) {
					case "1":
						this.status = false;
						System.out.println("Conta fechada com sucesso.");
						break;

					case "2":
						System.out.println("Operação de fechamento de conta cancelada.");
						break;
					}
				}
			}else {
				System.out.println("Conta em débito, impossível realizar o fechamento.");
			}
		}else {
			System.out.println("Para fechar uma conta, primeiramente, ela precisa ser aberta.");
		}
	}

	public double depositar(int presente) {
		Scanner sc = new Scanner(System.in);

		if(this.status == true) {
			if(qtdDepos > 0) {
				System.out.println("Digite o valor do depósito: ");
				double valor = sc.nextDouble();
				this.saldo = this.saldo + valor;
				if(valor > 0 && valor != 1) {
					System.out.println(valor + " reais depositados com sucesso.");
					System.out.println(getSaldo());
				}else if(valor == 1){
					System.out.println(valor + " real depositado com sucesso.");
					System.out.println(getSaldo());
				}else{
					System.out.println("Valor inválido para deposito.");
				}
			}else {
				this.saldo = this.saldo + presente;
			}
			this.qtdDepos++;
			return this.saldo;
		}else {
			System.out.println("Para depositar primeiramente abra uma conta.");
			return this.saldo;
		}
	}

	public void sacar() {
		Scanner sc = new Scanner(System.in);
		if(this.status == false) {
			System.out.println("Para efetuar um saque primeiramente abra uma conta.");
		}
		else {
			System.out.println("Digite o valor do saque: ");
			double valor = sc.nextDouble();
			if(valor <= this.saldo) {
				this.saldo = this.saldo - valor;
				if(valor > 0 && valor != 1) {
					System.out.println(valor + " reais sacados com sucesso.");
					System.out.println(this.getSaldo());
				}else if(valor == 1){
					System.out.println(valor + " real sacado com sucesso.");
					System.out.println(this.getSaldo());
				}else{
					System.out.println("Valor inválido para saque.");
				}
			}else {
				System.out.println("A conta possui " + this.saldo + " reais, saque impossível de ser realizado.");
			}
		}

	}

	public void pagarMensal() {
		if(this.status == true) {
			final short PAG = 20;
			this.saldo = this.saldo - PAG;
			System.out.println("");
			System.out.println("Mensalidade de 20 reais paga com sucesso!");
			System.out.println("Saldo atual: " + this.saldo);
			this.setStatusMensal(true);
		}else {
			System.out.println("Para pagar mensalidade primeiramente abra uma conta");
		}
	}

	public void sair() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Deseja realmente sair do sistema?"
				+ "\n(Digite 1 para sim e 2 para não)"
				+ "\n1) sim"
				+ "\n2) não");
		String confirm = sc.next();

		while(!(confirm.contentEquals("1")) && !(confirm.contentEquals("2"))) {
			System.out.println("Opção inválida inserida, insira novamente."
					+ "\n(Digite 1 para sim e 2 para não)"
					+ "\n1) sim"
					+ "\n2) não");
			confirm = sc.next();
		}

		switch (confirm) {
		case "1":
			System.exit(0);
			break;

		case "2":
			this.menu();
			break;
		}
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);
		short choice;
		if(this.nomeDono != null) {
			System.out.println("Bem-vindo ao Banco Sodalita, " + this.nomeFormatado() + ", o que deseja?"
					+ "\n1) Saque"
					+ "\n2) Depósito"
					+ "\n3) Verificar saldo"
					+ "\n4) Verificar número de conta"
					+ "\n5) Verificar mensalidade"
					+ "\n6) Pagar mensalidade"
					+ "\n7) Abrir conta"
					+ "\n8) Fechar conta"
					+ "\n9) Sair");
			choice = sc.nextShort();
		}else {
			System.out.println("Bem-vindo ao Banco Sodalita, o que deseja?"
					+ "\n1) Saque"
					+ "\n2) Depósito"
					+ "\n3) Verificar saldo"
					+ "\n4) Verificar número de conta"
					+ "\n5) Verificar mensalidade"
					+ "\n6) Pagar mensalidade"
					+ "\n7) Abrir conta"
					+ "\n8) Fechar conta"
					+ "\n9) Sair");
			choice = sc.nextShort();
		}

		while(choice < 1 || choice > 9) {
			System.out.println("Opção inválida inserida, insira novamente:"
					+ "\n1) Saque"
					+ "\n2) Depósito"
					+ "\n3) Verificar saldo"
					+ "\n4) Verificar número de conta"
					+ "\n5) Verificar mensalidade"
					+ "\n6) Pagar mensalidade"
					+ "\n7) Abrir conta"
					+ "\n8) Fechar conta"
					+ "\n9) Sair");
			choice = sc.nextShort();
		}

		switch (choice) {
		case 1:
			this.sacar();
			System.out.println("");
			this.menu();
			break;

		case 2:
			this.depositar(0);
			System.out.println("");
			this.menu();
			break;

		case 3:
			System.out.println(this.getSaldo());
			System.out.println("");
			this.menu();
			break;

		case 4:
			System.out.println(this.getNumConta());
			System.out.println("");
			this.menu();
			break;

		case 5:
			System.out.println(this.getStatusMensal());
			System.out.println("");
			this.menu();
			break;

		case 6:
			this.pagarMensal();
			System.out.println("");
			this.menu();
			break;

		case 7:
			this.abrirConta();
			System.out.println("");
			this.menu();
			break;

		case 8:
			this.fecharConta();
			System.out.println("");
			this.menu();
			break;

		case 9:
			this.sair();
			System.out.println("");
		}
	}
}