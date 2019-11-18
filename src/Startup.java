import java.util.*;

import objeto.*;

/**
 * Sistema de controle de Startup
 */
public class Startup {
    private Map<Integer, Equipe> equipes = new HashMap<>();
    private Map<Integer, Empresa> empresas = new HashMap<>();
    private Map<Integer, StartupDados> startups = new HashMap<>();

    public static void main(String args[]) {
        new Startup().abrirMenuPrincipal();
    }

    public void abrirMenuPrincipal(){
        String nomeStartup = null;
        int op;
        Scanner dadosEntrada = new Scanner(System.in);

        do{
            System.out.println("\n--- Sistema de controle de Startup ---");
            System.out.println("[1] - Cadastro de Equipe" +
                    "\n[2] - Cadastro da Empresa de fomento" +
                    "\n[3] - Cadastro de Startup" +
                    "\n[4] - Consulta de startup" +
                    "\n[5] - Sair" +
                    "\nDigite sua opção: ");
            op = dadosEntrada.nextInt();

            if (op == 1) {
                inserirEquipe(dadosEntrada);
            } else if (op == 2) {
                inserirEmpresa(dadosEntrada);
            } else if (op == 3) {
                insereStartup(dadosEntrada);
            } else if (op == 4) {
                consultaStartup(dadosEntrada);
            }
        } while (op != 5);

    }

    public void inserirEquipe(Scanner dadosEntrada){
        int codigoEquipe = 0;
        String addComponente;
        Equipe equipe = new Equipe();
        System.out.println("**************************************" +
                "\n-   Cadastro de Arquivos de Equipe   -" +
                "\n**************************************");

        do {
            System.out.print("\nDigite:\n <S> para adicionar novo componente. " +
                    "\n <N> para cancelar e voltar para o menu principal.\n");
            addComponente = dadosEntrada.next().toUpperCase();
            if (addComponente.equals("S")) {

                codigoEquipe += 1;

                System.out.print("\nNome do componente: ");
                equipe.setNome(dadosEntrada.next());

                System.out.print("E-mail: ");
                equipe.setEmail(dadosEntrada.next());

                System.out.print("Fim do cadastro");

                equipes.put(codigoEquipe, equipe);
            }
        } while (!addComponente.equals("N"));

        System.out.println("----------------------------------" +
                "\n--------Valores adicionados-------" +
                "\n----------------------------------");
        resultadoEquipe();
    }

    public void inserirEmpresa(Scanner dadosEntrada) {
        int codigoEmpresa = 0;
        String addEmpresa;
        Empresa empresa = new Empresa();

        System.out.println("***************************************" +
                "\n-   Cadastro de Arquivos da Empresa   -" +
                "\n***************************************");

        do {
            System.out.print("\nDigite:" +
                    "\n <S> para adicionar nova empresa." +
                    "\n <N> para cancelar e voltar para o menu principal: " +
                    "\n");
            addEmpresa = dadosEntrada.next().toUpperCase();

            if (addEmpresa.equals("S")){
                codigoEmpresa += 1;

                System.out.print("\nNome da empresa de fomento: ");
                empresa.setEmpresa(dadosEntrada.next());

                System.out.print("Valor de fomento aportado: ");
                empresa.setValor(dadosEntrada.nextFloat());

                System.out.print("Fim do cadastro");

                empresas.put(codigoEmpresa, empresa);

            }
        } while (!addEmpresa.equals("N"));

        System.out.println("\n----------------------------------" +
                "\n--------Valores adicionados-------" +
                "\n----------------------------------");
        resultadoEmpresa();
    }

    public void insereStartup(Scanner dadosEntrada) {
        int codigoStartup = 0, codigoEmpresa, codigoEquipes;
        String addStartup = null;
        StartupDados startup = new StartupDados();

        System.out.println("***************************************" +
                "\n-   Cadastro de Arquivos de Startup   -" +
                "\n***************************************");
        do {
            System.out.print("\nDigite:\n <S> para adicionar nova Startup." +
                    "\n <N> para cancelar e voltar para o menu principal: \n");
            addStartup = dadosEntrada.next().toUpperCase();

            if (addStartup.equals("S")){
                codigoStartup += 1;


                System.out.print("Nome: ");
                startup.setNomeStartup(dadosEntrada.next());

                System.out.print("Area de atuação: ");
                startup.setAreaStartup(dadosEntrada.next());

                System.out.print("Descriçao: ");
                startup.setDescricaoStartup(dadosEntrada.next());

                System.out.print("Data de criação: ");
                startup.setMesAnoCriacaoStartup(dadosEntrada.next());

                System.out.print("Codigo da empresa de fomento: ");
                codigoEmpresa = dadosEntrada.nextInt();
                if (!empresas.isEmpty()) {
                    for (Integer codigoEmpresaMemoria : empresas.keySet()) {
                        if (codigoEmpresa == codigoEmpresaMemoria){
                            resultadoEmpresa();
                        }
                    }
                } else {
                    System.out.println("Código da Empresa não existe no sistema." +
                            "\n Favor Cadastrar.");
                    inserirEmpresa(dadosEntrada);
                }

                System.out.print("Codigo da Equipe: ");
                codigoEquipes = dadosEntrada.nextInt();

                if (!equipes.isEmpty()){
                    for (Integer codigoEquipeMemoria : equipes.keySet()) {
                        if (codigoEquipes == codigoEquipeMemoria){
                            resultadoEquipe();
                        }
                    }
                } else {
                    System.out.println("Código da Equipe não existe no sistema." +
                            "\n Iniciar processo de Cadastro.");
                    inserirEquipe(dadosEntrada);
                }

                System.out.print("Fim do cadastro");
                startups.put(codigoStartup, startup);
            }
        } while (!addStartup.equals("N"));

        System.out.println("----------------------------------" +
                "\n--------Valores adicionados-------" +
                "\n----------------------------------");
        resultadoStartup();
    }

    public void consultaStartup(Scanner dadosEntrada){

        System.out.println("Digite nome da startup: ");
        String valorDigitado = dadosEntrada.next();
        for (StartupDados nome : startups.values()) {
            if (valorDigitado.equals(nome.getNomeStartup())){
                resultadoStartup();
            }
        }

    }

    public void resultadoEquipe(){
        for (Integer codigo : equipes.keySet()) {
            Equipe equipe = equipes.get(codigo);
            System.out.println("Codigo da equipe: " + codigo +
                    "\nComponemte : " + equipe.getNome() +
                    "\nE-mail     : " + equipe.getEmail() +
                    "\n----------------------------------");
        }
    }

    public void resultadoEmpresa(){
        for (Integer codigo : empresas.keySet()) {
            Empresa empresa = empresas.get(codigo);
            System.out.println("Codigo da empresa         : " + codigo +
                    "\nNome da Empresa           : " + empresa.getNomeEmpresa() +
                    "\nValor de fomento aportado : " + empresa.getValor() +
                    "\n----------------------------------");
        }
    }

    public void resultadoStartup(){
        System.out.println("codigo = " + startups.keySet());
        //System.out.println("nome = " + startups.);
        for (Integer codigo : startups.keySet()) {
            StartupDados startup = startups.get(codigo);
            Empresa empresa = empresas.get(codigo);
            System.out.println("\n----------------------------------------" +
                    "\n----- Dados Cadastrados da Startup -----" +
                    "\n----------------------------------------" +
                    "\n Código da Startup            : " + codigo +
                    "\n Nome                         : " + startup.getNomeStartup() +
                    "\n Area de atuação              : " + startup.getAreaStartup() +
                    "\n Descrição                    : " + startup.getDescricaoStartup() +
                    "\n Data de criação              : " + startup.getMesAnoCriacaoStartup() +
                    "\n-----------------------------------------");
            resultadoEquipe();
            resultadoEmpresa();
        }
    }
}
