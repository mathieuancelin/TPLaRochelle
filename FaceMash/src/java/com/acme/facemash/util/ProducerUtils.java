package com.acme.facemash.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * CDI producer for current entity manager.
 * 
 * @author mathieuancelin
 */
@ApplicationScoped
public class ProducerUtils {
    
    @PersistenceContext(unitName="FaceMashPU")
    @Produces
    private EntityManager em;
    
}
