package org.example;
import jakarta.persistence.EntityManager;

import java.io.Serializable;

public abstract class DataRepository<ID extends Serializable> {
    private EntityManager em;

    public DataRepository(EntityManager em) {
        this.em = em;
    }

}
