/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import quote.entity.Binding;
import static quote.entity.FamousPeople.getFamousPeople;
import quote.service.GetQuote;

/**
 *
 * @author maria
 */
@ManagedBean
@ViewScoped // Garante que o Bean será mantido durante a sessão do usuário
public class QuoteBean implements Serializable {

    public static final List<String> OPTIONS = new ArrayList<>();
    private String quote;
    private String author;
    private String info;
    private String advice;
    private Boolean getInfo = Boolean.FALSE;
    private int contador = 0;

    @PostConstruct
    public void init() {
            OPTIONS.clear();
            Map<String, String> resultRequest = new HashMap();
            GetQuote getQuote = new GetQuote();
            /*
        resultRequest = getQuote.getQuoteDay();
        for (Map.Entry<String, String> entry : resultRequest.entrySet()) {
            author = entry.getKey();
            quote = entry.getValue();
        }*/
            System.out.println("[QuoteBean] post construct init");
            resultRequest = getQuote.getQuotePensador();
            for (Map.Entry<String, String> entry : resultRequest.entrySet()) {
                author = entry.getKey();
                quote = entry.getValue();
            }
        }

    public void adc() {
        List<String> famousList = getFamousPeople();
        Collections.shuffle(famousList); // sorteia os nomes

        for (int i = 0; i < 3; i++) {
            if (famousList.get(i).equals(author)) {
                OPTIONS.add(famousList.get(i + 1));
            } else if (OPTIONS.contains(famousList.get(i))) {
                OPTIONS.add(famousList.get(i + 1));
            } else {
                OPTIONS.add(famousList.get(i));
            }

        }
        OPTIONS.add(author);
        Collections.shuffle(OPTIONS);
    }

    public void ds() {
        GetQuote getQuote = new GetQuote();
        List<Binding> list = getQuote.getFamous(author);

        for (Binding b : list) {
            OPTIONS.add(b.getName().getValue());
        }
    }

    private String nome;
    private String profissao;
    private String option;

    public void checkAnswer() {
        if (option.equals(author)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Parabéns! Você acertou!", ""));
            getInfo = Boolean.TRUE;
            return;
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Que pena! Você errou", "Tente novamente."));

    }

    public void getadvice() {
        GetQuote getQuote = new GetQuote();
        advice = getQuote.getAdvice();
    }

    public void infoFamous() {
        GetQuote getQuote = new GetQuote();
        info = getQuote.getInfoFamous(author);
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Boolean getGetInfo() {
        return getInfo;
    }

    public void setGetInfo(Boolean getInfo) {
        this.getInfo = getInfo;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public List<String> getOptions() {
        return OPTIONS;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

}
