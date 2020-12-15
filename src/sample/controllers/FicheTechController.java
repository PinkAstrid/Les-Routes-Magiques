package sample.controllers;

import sample.Parcours;

import java.awt.*;

public class FicheTechController {
    private String name;
    public TextArea textArea;
    public FicheTechController(){}

    public void getNameController(Parcours p) {
        this.name = p.getName();
        textArea.setText(name);
    }
}
