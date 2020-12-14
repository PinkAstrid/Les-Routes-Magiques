package sample;

public class CreatorFicheTech extends Creator{
    public CreatorFicheTech(){}
    public FicheTech createProduct(float duree, float distance, float denivele, int difficulte) {
        FicheTech ficheTechnique = new FicheTech(duree, distance, denivele, difficulte);
        return ficheTechnique;
    }
}
