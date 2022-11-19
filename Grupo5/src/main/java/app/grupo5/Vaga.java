package app.grupo5;

public class Vaga {

    private String titulo;

    private String localidade;

    private String link;

    private String nomeEmpresa;

    private String salario;

    private String informacoesAdicionais;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getInformacoesAdicionais() {
        return informacoesAdicionais;
    }

    public void setInformacoesAdicionais(String informacoesAdicionais) {
        this.informacoesAdicionais = informacoesAdicionais;
    }

    @Override
    public String toString() {
        return "========================================================================================\n" +
                "\t\t\t\t\t\t\t" + this.titulo.toUpperCase() +
                "\n========================================================================================\n" +
                "\nLocalidade:" + this.localidade +
                "\nEmpresa: " + this.nomeEmpresa +
                "\nSalario: " + this.salario +
                "\nInformações adicionais: " + this.informacoesAdicionais +
                "\nLink: " + this.link +"\n";
    }
}
