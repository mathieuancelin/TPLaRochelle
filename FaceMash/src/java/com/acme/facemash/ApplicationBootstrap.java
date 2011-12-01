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
 * Register any image from the img folder as a Face in database.
 * Use some trick with a servlet to find the img folder.
 * 
 * @author Mathieu ANCELIN
 */
@Startup
@Singleton
public class ApplicationBootstrap {
        
    public static final String EXTENSION = ".jpg";
    
    public static final String PREFIX = "/img/";
    
    @PersistenceContext EntityManager em;
        
    private String imgPath;
    
    private AtomicBoolean registered = new AtomicBoolean(false);
        
    private void findAndRegisterImages(String path) {
        File imgDir = new File(path);
        File[] imgs = imgDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(EXTENSION);
            }
        });
        Face.deleteAll(em);
        for (File img : imgs) {
            persistOne(PREFIX + img.getName());
            System.out.println("registering image : " + img.getName());
        }
        em.flush();
    }
    
    private void persistOne(String name) {
        Face face = new Face();
        face.setUrl(name);
        face.setPicked(0);
        em.persist(face);
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
