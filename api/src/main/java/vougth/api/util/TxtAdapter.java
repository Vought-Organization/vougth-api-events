package vougth.api.util;

import vougth.api.domain.Event;
import vougth.api.domain.User;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TxtAdapter {

    // Método para fazer o download do Txt dos eventos (Exportação de arquivo)
    public static void downloadTxtEvent(PrintWriter writer, ListObjUtil<Event> eventos, ListObjUtil<User> users) {

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

        for (int i = 0; i < users.getTamanho(); i++){
            User userTxt  = users.getElemento(i);
            writer.write("ID: " + userTxt.getIdUser() + "\n" +
                    "Nome: " + userTxt.getUserName() + "\n" +
                    "Email: " + userTxt.getEmail() + "\n" +
                    "Nickname: " + userTxt.getNickname() + "\n" +
                    "CPF: " + userTxt.getCpf() + "\n" +
                    "Telefone: " + userTxt.getTelefone() + "\n" +
                    "Cep: " + userTxt.getCep() + "\n" +
                    "Organizador: " + userTxt.isOrganize() + "\n \n");
        }
    }


    // Método para gravar registros para o import de arquivo
    public static void gravaRegistro(String registro, String nomeArq) throws IOException {
        BufferedWriter saida;
        saida = new BufferedWriter(new FileWriter(nomeArq, true));

        try {
            saida.append(registro).append("\n");
            saida.close();
        } finally {
            saida.close();
        }
    }
}
