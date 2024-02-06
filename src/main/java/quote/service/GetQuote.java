/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quote.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import quote.entity.Binding;
import quote.entity.FamousPeople;
import static quote.entity.FamousPeople.getFamousPeople;
import quote.entity.Name;
import quote.entity.QuoteResponse;
import quote.entity.Result;
import quote.entity.SlipWrapper;

/**
 *
 * @author maria
 */
public class GetQuote {

    public Map<String, String> getQuoteDay() {
        System.out.println("[getQuoteDay]");
        try {
            HttpGet httpget = new HttpGet("https://favqs.com/api/qotd");

            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            httpget.setConfig(requestConfig);
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());

            QuoteResponse quote = new Gson().fromJson(result, QuoteResponse.class);
            Map<String, String> resultRequest = new HashMap();
            resultRequest.put(quote.getQuote().getAuthor(), quote.getQuote().getBody());
            return resultRequest;
        } catch (IOException e) {
            return null;
        }
    }

    public List<Binding> getFamous(String author) {
        try {
            // Gera uma data aleatória entre "1700-01-01T00:00:00" e "2000-12-31T23:59:59"
            LocalDateTime randomDate1 = generateRandomDate(1700, 2000);
            LocalDateTime randomDate2 = generateRandomDate(1700, 2000);

            if (randomDate1.getYear() > randomDate2.getYear()) {
                randomDate2 = randomDate1;
                randomDate1 = randomDate2;
            }

            String url = "https://query.wikidata.org/sparql?query=SELECT%20%3Fname%0AWHERE%0A%7B%0A%20%20%3Fperson%20wdt%3AP31%20wd%3AQ5%20%3B%20%20%20%23%20human%0A%20%20%20%20%20wdt%3AP569%20%3Fborn%20.%0A%20%20%20FILTER%20(%3Fborn%20%3E%3D%20%22" + randomDate1.getYear() + "-01-01T00%3A00%3A00Z%22%5E%5Exsd%3AdateTime%20%26%26%20%3Fborn%20%3C%3D%20%22" + randomDate2.getYear() + 10 + "-12-31T23%3A59%3A59Z%22%5E%5Exsd%3AdateTime)%20.%0A%20%20%3Fperson%20wikibase%3Asitelinks%20%3Flinkcount%20.%0A%20%20FILTER%20(%3Flinkcount%20%3E%20100)%20.%0A%20%20%3Fperson%20rdfs%3Alabel%20%3Fname%20FILTER(lang(%3Fname)%3D%22en%22).%0A%20%20%3Fwikipedia_article%20schema%3Aabout%20%3Fperson%20.%0A%20%20%3Fwikipedia_article%20schema%3AisPartOf%20%3Chttps%3A%2F%2Fen.wikiquote.org%2F%3E%20.%0A%7D%0AORDER%20BY%20RAND()%0ALIMIT%203";
            System.out.println(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            HttpGet httpget = new HttpGet(url);
            requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Accept", "application/sparql-results+json;charset=utf-8");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());
            Result r = new Gson().fromJson(result, Result.class);
            List<Binding> list = r.getResults().getBindings();

            Name n = new Name();
            n.setValue(author);
            Binding b = new Binding();
            b.setName(n);
            list.add(b);
            Collections.shuffle(list); // sorteia os nomes

            for (Binding bs : list) {
                System.out.println("GetQuote: " + bs.getName().getValue());
            }
            return list;
        } catch (IOException e) {
            return null;
        }
    }

    public String getInfoFamous(String author) {
        String extractValue = null;
        author = author.replace(" ", "_");
        try {
            String url = "https://pt.wikipedia.org/w/api.php?action=query&format=json&exsentences=5&prop=extracts&exintro=true&titles=" + author;
            System.out.println(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            HttpGet httpget = new HttpGet(url);
            requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Content-Type", "application/json");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());

            // Convertendo o JSON para um objeto JsonObject
            JsonObject jsonObject = new Gson().fromJson(result, JsonObject.class);

            // Obtendo o valor do campo 'extract'
            extractValue = jsonObject.getAsJsonObject("query")
                    .getAsJsonObject("pages")
                    .entrySet()
                    .stream()
                    .findFirst()
                    .map(entry -> entry.getValue()
                    .getAsJsonObject()
                    .get("extract")
                    .getAsString())
                    .orElse(null);

            System.out.println(extractValue);
            return extractValue;
        } catch (IOException e) {
            return null;
        }
    }

    public String getAdvice() {
        try {
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            HttpGet httpget = new HttpGet("https://api.adviceslip.com/advice");
            requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Content-Type", "application/json");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());

            // Convertendo o JSON para um objeto JsonObject
            SlipWrapper slip = new Gson().fromJson(result, SlipWrapper.class);

            return slip.getSlip().getAdvice();
        } catch (IOException e) {
            return null;
        }
    }

    public Map<String, String> getQuotePensador() {
        try {
            Result rs = new Result();
            Random random = new Random();
            Integer number = random.nextInt(10);
            String name = null;
            
            do {
            List<String> famousList = getFamousPeople();
            Collections.shuffle(famousList); // sorteia os nomes
            name = famousList.get(0).replace(" ", "+");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            HttpGet httpget = new HttpGet("https://pensador-api.vercel.app/?term=" + name + "&max=10");
            requestConfig = RequestConfig.custom().setConnectTimeout(60000).build();
            httpget.setConfig(requestConfig);
            httpget.setHeader("Content-Type", "application/json");
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpget);
            String result = EntityUtils.toString(response.getEntity());

            // Convertendo o JSON para um objeto JsonObject
            rs = new Gson().fromJson(result, Result.class);
            
            System.out.println(rs);
            } while (number >= rs.getFrases().size() || rs.getFrases().isEmpty());
            System.out.println(number);
            System.out.println(rs.getFrases().size());
            
            Map<String, String> resultRequest = new HashMap();
            resultRequest.put(name.replace("+", " "), rs.getFrases().get(number).getTexto());
            return resultRequest;
        } catch (IOException e) {
            return null;
        }
    }

    private static LocalDateTime generateRandomDate(int startYear, int endYear) {
        int year = (int) (Math.random() * (endYear - startYear + 1)) + startYear;
        int month = (int) (Math.random() * 12) + 1;
        int day = (int) (Math.random() * 28) + 1;
        int hour = (int) (Math.random() * 24);
        int minute = (int) (Math.random() * 60);
        int second = (int) (Math.random() * 60);

        return LocalDateTime.of(year, month, day, hour, minute, second);
    }
}
