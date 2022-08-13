package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public List<String[]> lerArquivoCSV(String name) throws Exception {
        try {
            Path path = Paths.get("files/" + name);
            List<String> linhasArquivo = Files.readAllLines(path);
            List<String[]> matrizModelo = new ArrayList<>();
            for (int i = 0; i < linhasArquivo.size(); i++) {
                String [] linha = getLineData(linhasArquivo.get(i));
                matrizModelo.add(linha);
            }
            return matrizModelo;
        } catch (Exception e) {
            throw new IOException("Não foi possível a leitura do arquivo");
        }
    }

    public String[] getLineData(String input) throws Exception{
        try {
            return input.split(";");
        } catch (Exception e) {
            throw new Exception ();
        }
    }

    public void salvarArquivoTxt(String processInput) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("files/output.txt"));
            writer.write(processInput);
            writer.close();
        } catch (IOException | InvalidPathException e) {
            throw new IOException("Erro ao salvar arquivo txt.");
        }
    }

}
