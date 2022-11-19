package app.grupo5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);


        List<String> listaTitulos = new ArrayList<>();

        while (listaTitulos.size() <= 0) {
            listaTitulos = App.acharTitulo();
            if (listaTitulos.size() > 1) {
                break;
            }
        }

        List<Vaga> listavagas = new ArrayList<>();
        List<String> localidades =  App.localidade(listaTitulos);

        for (int i = 0; i < listaTitulos.size(); i++) {
            int x = listaTitulos.get(i).indexOf("em");
            Vaga v = new Vaga();
            v.setTitulo(listaTitulos.get(i).substring(0, x));
            v.setLocalidade(localidades.get(i));
            listavagas.add(v);
        }
        for (int i = 0; i < listavagas.size() ; i++) {
            System.out.println((i + 1) +" - " +listavagas.get(i).getTitulo());
        }


        System.out.println("----------------------------------------------------------------------------------------");
        System.out.print("Escolha uma vaga pelo id: ");
        int id = entrada.nextInt();
        App.recuperarDadosVaga(listavagas.get(0));
        System.out.println(listavagas.get(id - 1));
    }
}
