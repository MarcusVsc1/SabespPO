package main;

import ilog.concert.IloException;
import ilog.concert.IloIntVar;
import ilog.concert.IloLinearNumExpr;
import ilog.concert.IloNumVar;
import ilog.cplex.IloCplex;
import processor.CplexProcessor;
import util.FileUtils;
import util.IOUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {
        FileUtils fileUtils = new FileUtils();
        List<String[]> matrizModelo = new ArrayList<>();
        CplexProcessor processador = null;
        try {
            matrizModelo = fileUtils.lerArquivoCSV("modelo.csv");
            String leitura = IOUtils.lerModelo(matrizModelo);
            fileUtils.salvarArquivoTxt(leitura);
            processador = new CplexProcessor(matrizModelo);
            processador.rodarSolver();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
