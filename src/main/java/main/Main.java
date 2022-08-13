package main;

import ilog.concert.IloException;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;
import util.FileUtils;
import util.IOUtils;

import java.util.List;

public class Main {



    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        try {
            List<String[]> matrizModelo = fileUtils.lerArquivoCSV("modelo.csv");
            String leitura = IOUtils.lerModelo(matrizModelo);
            fileUtils.salvarArquivoTxt(leitura);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            IloCplex cplex = new IloCplex();
//            //variáveis, adiciona por valor mínimo e máximo
//            IloNumVar x1 = cplex.numVar(0, Double.MAX_VALUE, "x1"); // x1 vai de 0 até infinito
//            IloNumVar x2 = cplex.numVar(0, Double.MAX_VALUE, "x2"); // x2 vai de 0 até infinito
//
//            //função objetivo: 0,12x1 + 0,15x2
//            IloLinearNumExpr funcaoObjetivo = cplex.linearNumExpr();
//            funcaoObjetivo.addTerm(0.12, x1); // 0,12x1
//            funcaoObjetivo.addTerm(0.15, x2); // 0,15x2
//            cplex.addMinimize(funcaoObjetivo); //define que a FO é de minimizar
//
//            //restrições. ge -> greater or equal, maior ou igual
//            //restrição a seguir: 60x1 + 60x2 >=300
//            cplex.addGe(cplex.sum(cplex.prod(60,x1), cplex.prod(60,x2)),300);
//            //restrição a seguir: 12x1 + 6x2 >=36
//            cplex.addGe(cplex.sum(cplex.prod(12,x1), cplex.prod(6,x2)),36);
//            //restrição a seguir: 12x1 + 6x2 >=36
//            cplex.addGe(cplex.sum(cplex.prod(10,x1), cplex.prod(30,x2)),90);
//
//            if (cplex.solve()){//roda o solver
//                System.out.println("Valor da função objetivo: "+cplex.getObjValue());
//                System.out.println("Valor de x1: "+cplex.getValue(x1));
//                System.out.println("Valor de x2: "+cplex.getValue(x2));
//                cplex.writeSolution("arquivo.xls");
//            } else {
//                System.out.println("Problema intratável");
//            }
//
//        } catch (IloException e) {
//            e.printStackTrace();
//        }
    }
}
