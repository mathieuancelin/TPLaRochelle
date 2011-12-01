package com.acme.facemash.controllers;

import com.acme.facemash.util.ApplicationBoundary;
import com.acme.facemash.models.Face;
import com.acme.facemash.util.Elo;
import com.acme.facemash.util.Functionnal.Function;
import com.acme.facemash.util.Functionnal.Unit;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 * Main controller for the app.
 * Is responsible for pick logic and stats logic.
 * 
 * @author mathieuancelin
 */
@Named
@RequestScoped
public class ApplicationController {

    @Inject EntityManager em;
    
    @Inject ApplicationModel model;
    
    @Inject ErrorModel error;
    
    @Inject Elo elo;
    
    @EJB ApplicationBoundary boundary;
    
    private String title = "Who's the cutest ?";
    
    private static final String REDIRECT_SUFFIX = ".xhtml?faces-redirect=true";
    
    @PostConstruct
    public void index() {
        if (model.getFace1() == null && model.getFace2() == null) {
            Face face1 = Face.randomFace(em);
            Face face2 = Face.randomFace(em, face1.getId());
            model.setFace1(face1);
            model.setFace2(face2);
        }
    }
    
    public String backToIndex() {
        return redirectToView("index");
    }

    public String pickAFace(final Long id, final Long panelId) {
        if (panelId == 1) {
            elo.vote(id, model.getFace2().getId());
        } else {
            elo.vote(id, model.getFace1().getId());
        }
        Function<Unit, String> function = new Function<Unit, String>() {
            @Override
            public String apply(Unit arg) {
                if (id == null) {
                    error.setMessage("No valid id specified");
                    return redirectToView("error");
                }
                if (panelId == null) {
                    error.setMessage("No panel id specified");
                    return redirectToView("error");
                }
                Face face = Face.findById(em, id);
                if (face == null) {
                    error.setMessage("Unable to find face at " + id);
                    return redirectToView("error");
                }
                face.picked(em);
                Face newFace = Face.randomFace(em, face.getId());
                if (panelId == 1) {
                    model.setFace1(face);
                    model.setFace2(newFace);
                } else {
                    model.setFace2(face);
                    model.setFace1(newFace);
                }
                return redirectToView("index");
            }
        };
        return boundary.apply(function, Unit.INSTANCE);
    }

    public String stats() {
        Comparator<Face> byPicked = new Comparator<Face>() {
            @Override
            public int compare(Face cat1, Face cat2) {
                return cat1.getPicked().compareTo(cat2.getPicked());
            }
        };
        List<Face> originalList = Face.all(em);
        Collections.sort(originalList, Collections.reverseOrder(byPicked));
        model.setFaces(originalList);
        return redirectToView("stats");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public static String redirectToView(String viewName) {
        return viewName + REDIRECT_SUFFIX;
    }
}
