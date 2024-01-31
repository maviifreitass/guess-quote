/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.bean;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import quote.service.GetQuote;

/**
 *
 * @author maria
 */
@ManagedBean
@ViewScoped // Garante que o Bean será mantido durante a sessão do usuário
public class AdviceBean implements Serializable {

    private String advice;

    @PostConstruct
    public void init() {
        GetQuote getQuote = new GetQuote();
        advice = getQuote.getAdvice();
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

}
