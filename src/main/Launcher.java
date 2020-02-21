package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Data;
import model.Igrac;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class Launcher {
    public static void main(String[] args) {
        staticFiles.location("/public");
        String path = "igraci.json";
        port(5000);

        HashMap<String, Object> polja = new HashMap<>();

        //napisati kod za potrebne rute
        get("/", (request, response) -> {
            polja.put("igrac", Data.readFromJson(path));
            return new ModelAndView(polja, "index.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edit", (request, response) -> {
            polja.put("igrac", Data.readFromJson(path));
            return new ModelAndView(polja, "uredjivanje.hbs");
        }, new HandlebarsTemplateEngine());

        get("/edit/:id", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            polja.put("zaIzmenu", Data.readFromJson(path).stream().filter(s -> s.getId() == id).toArray());
            return new ModelAndView(polja, "izmena.hbs");
        }, new HandlebarsTemplateEngine());

        get("/add",(request, response) -> {
            return new ModelAndView(null,"dodavanje.hbs");
        },new HandlebarsTemplateEngine());

        post("/api/uredi", (request, response) -> {
            response.type("application/json");
            Gson gson = new Gson();
            String pom = request.queryParams("data");
            Igrac edit;
            try {
                edit = gson.fromJson(pom, Igrac.class);
            } catch (Exception e) {
                return gson.toJson("Nije sacuvano");
            }
            if(!edit.getDatRodj().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) return gson.toJson("Nije sacuvano");
            System.out.println(pom);
            ArrayList<Igrac> lista = Data.readFromJson(path);
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() == edit.getId()) {
                    lista.set(i, edit);
                    Data.writeToJSON(lista, path);

                }
            }
            return gson.toJson("Uspesno sacuvano");

        });

        post("/api/add",(request, response) -> {
            response.type("application/json");
            Gson gson = new Gson();
            String pom = request.queryParams("data");
            Igrac edit;
            try {
                edit = gson.fromJson(pom, Igrac.class);
            } catch (Exception e) {
                return gson.toJson("Nije dodat");
            }
            if(!edit.getDatRodj().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) return gson.toJson("Nije dodat");
            System.out.println(pom);
            ArrayList<Igrac> lista = Data.readFromJson(path);
            int last=-1;
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getId() >last) {
                    last=lista.get(i).getId();
                }
            }
            last++;
            edit.setId(last);
            lista.add(edit);
            Data.writeToJSON(lista, path);
            return gson.toJson("Uspesno dodat");

        });

        post("/api/find",(request, response) -> {
            Gson gson=new Gson();
            String uzorak=request.queryParams("sample").toLowerCase();
            if(uzorak.split("█").length<2) return null;
            String sample=uzorak.split("█")[1];
            if(uzorak.split("█")[0].equals("imeprezime")){
                return gson.toJson(Data.readFromJson(path).stream().filter(s->s.getIme().toLowerCase().contains(sample)|| s.getPrezime().toLowerCase().contains(sample)).toArray());
            }
            if(uzorak.split("█")[0].equals("klub")){
                return gson.toJson(Data.readFromJson(path).stream().filter(s->s.getKlub().toLowerCase().contains(sample)).toArray());
            }
            if(uzorak.split("█")[0].equals("pozicija")){
                return gson.toJson(Data.readFromJson(path).stream().filter(s->s.getPozicija().toLowerCase().contains(sample)).toArray());
            }
            return null;

        });

    }

}
