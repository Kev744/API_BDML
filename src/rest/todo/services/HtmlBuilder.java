package rest.todo.services;

import jakarta.servlet.http.HttpServlet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileWriter;
import java.io.IOException;

@Path("/getAllMovies")
public class HtmlBuilder extends HttpServlet {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public String generateFile() {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head><title>Page HTML générée avec StringBuilder</title></head><body>");
        htmlBuilder.append("<h1>Bienvenue sur ma page HTML générée avec StringBuilder !</h1>");
        htmlBuilder.append("<p>Cette page a été générée dynamiquement en utilisant Java et StringBuilder.</p>");
        htmlBuilder.append("</body></html>");

        //String path = getServletContext().getRealPath("/webcontent");
/*
        try (FileWriter fileWriter = new FileWriter("D:/Devoir Projet/API & Web Services/projetSarankan/rest.todo copie 3/WebContent/index.html")) {
            fileWriter.write(htmlBuilder.toString());
            System.out.println("Le fichier HTML a été généré avec succès !");
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de la génération du fichier HTML : " + e.getMessage());
        }*/

        return htmlBuilder.toString(); //TODO replace this stub to something useful
    }
}
