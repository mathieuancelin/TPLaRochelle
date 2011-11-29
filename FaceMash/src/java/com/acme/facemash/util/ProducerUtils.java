package com.acme.facemash.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ProducerUtils {
    
    @PersistenceContext(unitName="FaceMashPU")
    @Produces
    private EntityManager em;
    
}
