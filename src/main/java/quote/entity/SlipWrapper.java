package quote.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SlipWrapper {

    @JsonProperty("slip")
    private Slip slip;

    public Slip getSlip() {
        return slip;
    }

    public void setSlip(Slip slip) {
        this.slip = slip;
    }

    @Override
    public String toString() {
        return new com.google.gson.Gson().toJson(this);
    }
}
