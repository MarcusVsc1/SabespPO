package util;

import java.util.List;

public class IOUtils {

    public static String lerModelo(List<String[]> modelo) {
        String modeloString = "";
        for (String[] linha : modelo) {
            Boolean primeiro = true;
            if (modelo.indexOf(linha) != 0) {
                String input = "";
                for (int i = 0; i < linha.length - 3; i++) {
                    Integer valor = Integer.parseInt(linha[i]);
                    if (!(valor == 0)) {
                        if (primeiro) {
                            primeiro = false;
                        } else input = valor > 0 ? input + " + " : input + " - ";
                        input = input + Math.abs(valor) + modelo.get(0)[i];
                    }
                }
                input = input + " " + linha[linha.length - 2] + " " + linha[linha.length - 1];
                System.out.println(input);
                modeloString = modeloString + input + '\n';
            }

        }
        return modeloString;
    }

}
