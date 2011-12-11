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
public class ApplicationController {
    
    private static final String REDIRECT_SUFFIX = ".xhtml?faces-redirect=true";
    
    @PostConstruct
    public void index() {
		// TODO : si le model courant n'est pas rempli (pas de face1 ou de face2 alors le remplir avec 2 faces aléatoires)
    }
    
    public String backToIndex() {
        return redirectToView("index");
    }

    public String pickAFace(final Long id, final Long panelId) {
		// TODO : ici, effectuer le calcul de score pour la Face gagnante
		// TODO : enregistrer le vote pour la face gagnante
		// TODO : selectionner une nouvelle face de manière aléatoire et remplacer avec la face perdante
    }

    public String stats() {
		// TODO : récupérer la liste des faces et la trier par nombre de votes
    }
    
    public static String redirectToView(String viewName) {
        return viewName + REDIRECT_SUFFIX;
    }
}
