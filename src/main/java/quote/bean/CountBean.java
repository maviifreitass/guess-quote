/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.bean;

/**
 *
 * @author maria
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CountBean {

    private int contador = 0;
    private int incorrect = 0;
    private int correct = 0;

    public int getContador() {
        return contador++;
    }

    public int getIncorrect() {
        return incorrect++;
    }

    public int getCorrect() {
        return correct++;
    }

}
