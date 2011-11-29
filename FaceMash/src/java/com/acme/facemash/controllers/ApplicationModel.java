package com.acme.facemash.controllers;

import com.acme.facemash.models.Face;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ApplicationModel implements Serializable {
    
    private Face face1;
    
    private Face face2;
    
    private int selected;
    
    private List<Face> faces;

    public Face getFace1() {
        return face1;
    }

    public void setFace1(Face face1) {
        this.face1 = face1;
    }

    public Face getFace2() {
        return face2;
    }

    public void setFace2(Face face2) {
        this.face2 = face2;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
