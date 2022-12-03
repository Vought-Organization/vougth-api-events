package vougth.api.uteis;

import vougth.api.domain.Event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TxtAdapter {

    // Método para fazer o download do Txt dos eventos (Exportação de arquivo)
    public static void downloadTxtEvent(PrintWriter writer, ListObj<Event> eventos) {

        for (int i = 0; i < eventos.getTamanho(); i++){
            Event eventTxt  = eventos.getElemento(i);
            writer.write("ID: " + eventTxt.getIdEvent() + "\n" +
                    "Nome: " + eventTxt.getNameEvent() + "\n" +
                    "CEP: " + eventTxt.getCep() + "\n" +
                    "Categoria: " + eventTxt.getCategoryEvent() + "\n" +
                    "Endereço: " + eventTxt.getAddressEvent() + "\n" +
                    "Descrição: " + eventTxt.getDescription() + "\n" +
                    "Cidade: " + eventTxt.getCity() + "\n" +
                    "Estado: " + eventTxt.getState() + "\n" +
                    "Data de Inicio: " + eventTxt.getStartData() + "\n" +
                    "Encerramento do Evento: " + eventTxt.getEndData() + "\n \n");
        }
    }


    // Método para gravar registros para o import de arquivo
    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;    // objeto usado para gravar no arquivo

        // Abre o arquivo
        try {
            // Abre o arquivo com append = true, para poder ir acrescentando registros no arquivo
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo: " + erro.getMessage());
        }

        // Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");  // Grava o registro e o final de registro (\n)
            saida.close();                  // Fecha o arquivo
        } catch (IOException erro) {
            System.out.println("Erro ao gravar no arquivo: " + erro.getMessage());
        }
    }
}
