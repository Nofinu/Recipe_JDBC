package org.example.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Categorie extends BaseShowMethod {
    private int id;
    private String name;

    @Override
    public String toStringWithoutId() {
        return name;
    }

    @Override
    public String toString() {
        return name +" id :" + id ;
    }
}
