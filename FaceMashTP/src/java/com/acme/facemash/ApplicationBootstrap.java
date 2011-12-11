package com.acme.facemash;

import com.acme.facemash.models.Face;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Register any image from the img folder as a Face in the database.
 * Use some trick with a servlet to find the img folder.
 * 
 * @author Mathieu ANCELIN
 */
@Startup
@Singleton
public class ApplicationBootstrap {
            
    private String imgPath;
    
    private AtomicBoolean registered = new AtomicBoolean(false);
        
    private void findAndRegisterImages(String path) {
        // TODO : ici, rechercher toutes les images jpg dans le dossier img de l'application
		// TODO : enregistrer toutes les faces correspondantes
    }
    
    @Schedule(second="*", minute="*", hour="*")
    protected void scanImages() {
        if (imgPath != null && !registered.get()) {
            findAndRegisterImages(imgPath);
            registered.getAndSet(true);
        }
    }
    
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
