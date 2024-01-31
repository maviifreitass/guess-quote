/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author maria
 */
public class QuoteResponse {

    @SerializedName("qotd_date")
    @Expose
    private String qotdDate;
    @SerializedName("quote")
    @Expose
    private Quote quote;

    public String getQotdDate() {
        return qotdDate;
    }

    public void setQotdDate(String qotdDate) {
        this.qotdDate = qotdDate;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

}
