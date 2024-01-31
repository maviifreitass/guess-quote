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
    public class Results {

        @JsonProperty("bindings")
        private List<Binding> bindings;

        public List<Binding> getBindings() {
            return bindings;
        }

        public void setBindings(List<Binding> bindings) {
            this.bindings = bindings;
        }
    }