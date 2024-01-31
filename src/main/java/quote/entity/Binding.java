/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author maria
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Binding {

        @JsonProperty("name")
        private Name name;

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }
    }