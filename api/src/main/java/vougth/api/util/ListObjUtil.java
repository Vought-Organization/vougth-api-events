package vougth.api.util;

import java.io.IOException;

public class ListObjUtil<T>{
    private T[] vetor;
    private int nroElem;

    public ListObjUtil(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public boolean isEmpty() {
        return nroElem == 0;
    }

    public boolean adicionar(T elemento) throws IOException {
        if (nroElem >= vetor.length) {
            throw new IOException();
        }
        vetor[nroElem++] = elemento;
        return true;
    }

    //metodo que adiciona no inicio (Provavelmente com erro)
    public boolean adicionaNoInicio(T elemento) throws IOException {
        if (nroElem >= vetor.length) {
            throw new IOException();
        }

        T[] vetorNovo = (T[]) new Object[nroElem++];

        for (T t : vetor) {
            vetorNovo[0] = elemento;
            vetorNovo[nroElem++] = t;
        }
        return true;

    }

    public void exibir() throws IOException {
        if (nroElem == 0) {
            throw new IOException();
        }
        else {
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i] + "\t");
            }
            System.out.println();
        }
    }

    public int buscar(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }


    public boolean removePeloIndice (int indice) throws IOException {
        if (indice < 0 || indice >= nroElem) {
            throw new IOException();
        }

        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i+1];
        }
        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) throws IOException {
        return removePeloIndice(buscar(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        }
        return vetor[indice];
    }

    public void limpa() {
        nroElem = 0;
    }
}
