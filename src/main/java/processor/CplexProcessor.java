package processor;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumExpr;
import ilog.cplex.IloCplex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CplexProcessor {

    private List<IloIntVar> variaveis;
    private IloCplex cplex = new IloCplex();;
    private IloLinearNumExpr funcaoObjetivo;

    public CplexProcessor(List<String[]> matrizModelo) throws IloException {
        this.variaveis = criarVariaveis(matrizModelo.get(0));
        criarFuncaoObjetivo(matrizModelo.get(1));
        criarRestricoes(matrizModelo);
    }

    private void criarRestricoes(List<String[]> matrizModelo) throws IloException {

        for (int i = 2; i < matrizModelo.size(); i++){
            List<IloNumExpr> termos = new ArrayList<>();
            for (int j = 0; j < matrizModelo.get(i).length-2; j++) {
                Double valorDouble = Double.parseDouble(matrizModelo.get(i)[j]);
                termos.add(cplex.prod(valorDouble,variaveis.get(j)));
            }
            IloNumExpr[] arrayTermos = termos.toArray(new IloNumExpr[0]);
            Integer valorRestricao = Integer.parseInt(matrizModelo.get(i)[matrizModelo.get(i).length-1]);
            if (matrizModelo.get(i)[matrizModelo.get(i).length-2].equals("=")){
                cplex.addEq(cplex.sum(arrayTermos),valorRestricao);
            }
            if (matrizModelo.get(i)[matrizModelo.get(i).length-2].equals(">=")){
                cplex.addGe(cplex.sum(arrayTermos),valorRestricao);
            }
        }

    }

    private void criarFuncaoObjetivo(String[] valores) throws IloException {
        funcaoObjetivo = cplex.linearNumExpr();
        for (int i = 0; i < valores.length; i++){
            if(!valores[i].equals("XX")){
                Double valorDouble = Double.parseDouble(valores[i]);
                funcaoObjetivo.addTerm(valorDouble, variaveis.get(i));
            }
        }
        cplex.addMinimize(funcaoObjetivo);
    }

    private List<IloIntVar> criarVariaveis(String[] matrizModelo) throws IloException {
        List<IloIntVar> variaveis = new ArrayList<>();
        Arrays.asList(matrizModelo).forEach(variavel -> {
            try {
                variaveis.add(cplex.intVar(0, Integer.MAX_VALUE, variavel));
                System.out.print(variavel + " ");
            } catch (IloException e) {
                System.out.println(e.getMessage());
            }
        });

        return variaveis;
    }

    public void rodarSolver() throws IloException {
        if (cplex.solve()){//roda o solver
                cplex.writeSolution("arquivo.xml");
            } else {
                System.out.println("Problema intratável");
            }
    }
}
