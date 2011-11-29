package com.acme.facemash.models;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Face implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String url;
    
    private Integer picked;

    public void picked(EntityManager em) {
        if (picked == null) {
            picked = 0;
        }
        picked++;
        save(em);
    }

    public static Face randomFace(EntityManager em) {
        return randomFace(em, null);
    }

    public static Face randomFace(EntityManager em, Long id) {
        long count = Face.count(em);
        Random random = new Random();
        int number = random.nextInt((int) count + 1);
        if(id != null){
            while (number == id.intValue()) {
                number = random.nextInt((int) count) + 1;
            }
        }
        if (number == 0) {
            number = 1;
        }
        Face face = Face.findById(em, new Long(number));
        if (face == null) {
            return randomFace(em, id);
        }
        return face;
    }
    
    public static List<Face> all(EntityManager em) {
        return em.createQuery("select o from Face o").getResultList();
    }

    public static long count(EntityManager em) {
        Long l = Long.parseLong(em.createQuery("select count(e) from Face e").getSingleResult().toString());
        if (l == null || l < 0) {
            return 0L;
        }
        return l;
    }

    public static int deleteAll(EntityManager em) {
        return em.createQuery("delete from Face").executeUpdate();
    }

    public void delete(EntityManager em) {
        em.remove(this);
    }
    
    public static Face delete(EntityManager em, Face o) {
        em.remove(o);
        return o;
    }

    public static Face findById(EntityManager em, Long primaryKey) {
        return em.find(Face.class, primaryKey);
    }
    
    public Face save(EntityManager em) {
        if (em.contains(this)) {
            return em.merge(this);
        }
        em.persist(this);
        return this;
    }
    
    //////////////////////

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPicked(Integer picked) {
        this.picked = picked;
    }

    public String getUrl() {
        return url;
    }

    public Integer getPicked() {
        return picked;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Face)) {
            return false;
        }
        Face other = (Face) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.acme.facemash.models.Face[ id=" + id + " ]";
    }
}
