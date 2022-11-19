package app.grupo5;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.http.HttpClient;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import java.net.URI;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class App {

    public static List<String> acharTitulo() {
        //URL DO SITE QUE SERÁ ACESSADO
        String url = "https://www.catho.com.br/vagas/programador-java/?q=Programador%20Java";
        List<String> titulos = new ArrayList<>();
        try {
            //CONECTANDO E OBTENDO UMA COPIA HTML DA PAGINA
            Document doc = Jsoup.connect(url).get();

            //OBTENDO O TITULO DAS VAGAS PELA CLASSE
            List<Element> vagas = doc.getElementsByClass("Title__Heading-sc-14fvmc0-0 fGTSAd sc-kafWEX brgbFN");

            //CRIANDO UMA LISTA PARA ARMAZENAR OS ELEMENTOS QUE COMECEM COM A TAG "a" E OS TITULOS
            //DAS VAGAS
            List<Element> as = new ArrayList<>();

            //COLETANDO TODOS OS ELEMENTOS DA TAG "a"
            vagas.forEach(element -> {
                as.add(element.getElementsByTag("a").first());
            });

            //UTILIZANDO OS ELEMENTOS COM A TAG "a" PARA COLETAR O TITULO DAS VAGAS
            as.forEach(element -> {
                titulos.add(element.attr("title"));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return titulos;
    }

    public static List<String> localidade(List<String> vaga) {
        List<String> listRetorno = new ArrayList<>();
        for (String v : vaga) {
            String[] x = v.split("em");
            listRetorno.add(x[1]);
        }
        return listRetorno;
    }

    public static Vaga recuperarDadosVaga(Vaga vaga) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        HttpResponse<String> response = null;

        //CONECTANDO AO SITE
        String link = "https://www.catho.com.br/vagas/programador-java/21259468/?origem_apply=busca-de-vagas&entrada_apply=direto";
        request = HttpRequest.newBuilder().uri(URI.create(link)).GET().build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //COLETAR LOCALIZAÇÃO
        //DOWNLOAD DO HTML
        String html = response.body().toString();
        //IDENTIDIFICANDO A POSIÇÃO INICIAL DA INFORMAÇÃO
        int nomeEmpresaInicial = html.indexOf("Pacto Soluções Tecnológicas Ltda.");
        //IDENTIFICANDO A POSIÇÃO FINAL DA INFORMAÇÃO
        int nomeEmpresaFinal = html.indexOf(".", nomeEmpresaInicial);
        vaga.setNomeEmpresa( html.substring(nomeEmpresaInicial, nomeEmpresaFinal));

        //COLETAR NOME DO SALARIO
        //IDENTIDIFICANDO A POSIÇÃO INICIAL DA INFORMAÇÃO
        int nomeSalarioInicial = html.indexOf("<span class=\"sc-gmeYpB hEMfhS\">");
        //IDENTIFICANDO A POSIÇÃO FINAL DA INFORMAÇÃO
        int nomeSalarioFinal = html.indexOf("</span>", nomeSalarioInicial);
        //IMPRIMINDO A INFORMAÇÃO
        String salario = "A Combinar";
        vaga.setSalario(salario);
        //COLETAR INFORMAÇÕES ADICIONAIS
        //IDENTIFICANDO A POSIÇÃO INICIAL DA INFORMAÇÃO
        int InformacaoInicial = html.indexOf("Atividades totalmente home office");
        //IDENTIFICANDO A POSIÇÃO FINAL DA INFORMAÇÃO
        int InformacaoFinal = html.indexOf(".", InformacaoInicial);
        //IMPRIMINDO A INFORMAÇÃO
        String inf =  html.substring(InformacaoInicial, InformacaoFinal);
        vaga.setInformacoesAdicionais(inf);
        String linkVaga = "https://www.catho.com.br/vagas/programador-java/21259468/?origem_apply=busca-de-vagas&entrada_apply=direto";
        vaga.setLink(linkVaga);
        return vaga;
    }

}
