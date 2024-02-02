/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author maria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("results")
    private Results results;

    @JsonProperty("frases")
    private List<Frases> frases;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    public List<Frases> getFrases() {
        return frases;
    }

    public void setFrases(List<Frases> frases) {
        this.frases = frases;
    }

}
