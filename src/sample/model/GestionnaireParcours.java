package sample.model;

import GPX.Reader;
import GPX.Writer;
import javafx.scene.image.Image;
import sample.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GestionnaireParcours extends Observable {
    private List<Parcours> listeParcours;
    private List<Parcours> listeParcoursRecherches;

    public GestionnaireParcours() {
        listeParcours = new ArrayList<Parcours>();
        listeParcoursRecherches = new ArrayList<Parcours>();
    }
    public GestionnaireParcours(String path) {
        listeParcours = new ArrayList<Parcours>();
        listeParcoursRecherches = new ArrayList<Parcours>();
        try {
            importParcours(path);
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public FicheTech createFicheTechnique(float duree, float distance, float denivele, int difficulte) {
        FicheTech ficheTechnique = new FicheTech(duree, distance, denivele, difficulte);
        return ficheTechnique;
    }

    public Parcours createParcours(List<Coordonees> chemin, float duree, float distance, float denivele, int difficulte, String title, String description, String details, ArrayList<Image> photos) {
        Trace t = new Trace(chemin);

        FicheTech ficheTechnique = createFicheTechnique(duree, distance, denivele, difficulte);

        Parcours p = new Parcours(title, description, details, photos, ficheTechnique, t);
        listeParcours.add(p);
        return p;
    }

    public void ajouterParcours(Parcours p){
        listeParcours.add(p);
    }

    public void supprimerParcours(Parcours p){
        listeParcours.remove(p);
    }

    public List<Parcours> getListeParcours() {
        return listeParcours;
    }

    public void setParcoursRecherche(List<Parcours> listeParcoursRecherches) {
        this.listeParcoursRecherches = listeParcoursRecherches;
    }

    public List<Parcours> getListeParcoursRecherches() {
        return listeParcoursRecherches;
    }

    public void importParcours(String path) throws IOException {
        Path folder = Paths.get(path);
        File[] files = folder.toFile().listFiles();
        Reader r;
        for (int i = 0; i < files.length; i++){
            r = Reader.CreateReader(files[i].getName());
            listeParcours.add(r.getParcours());
        }
    }

    public void exportParcours(String path){
        Writer w;
        String fileName = "";
        int len;
        int ntry;
        List<String> fileNames = new ArrayList<>();
        for (Parcours p : listeParcours){
            // recupere le nom du parcours, pour en faire le nom du fichier
            fileName = p.getName();
            // remplace les espaces par des underscores
            fileName.replace(' ', '_');
            len = fileName.length();
            ntry = 0;
            // test if a file was already saved under that name
            // ajoute un numero à la fin du nom du fichier
            while (fileNames.contains(fileName)){
                fileName = fileName.substring(0, len);
                fileName += ntry;
                ntry++;
            }
            // ajoute le nom final à la lite des noms utiisés
            fileNames.add(fileName);
            w = Writer.CreateWriter(path + fileName + ".gpx");
            w.writeGPX(p);
        }
    }

    public void afficherParcours(Parcours p){}
}
