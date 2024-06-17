package com.std22073.marcheurblanc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter

public class Carte {
    private static List<Lieu> lieux;
    private static List<Rue> rues;
    public static List<Lieu> getVoisins(Lieu lieu){
        List<Lieu> voisins = new ArrayList<>();
        for(Rue rue : rues) {
            if(rue.getOrigine().equals(lieu) || rue.getDestination().equals(lieu)) {
                voisins.add(rue.getDestination().equals(lieu) ? rue.getOrigine() : rue.getDestination());
            }
        }
        return voisins;
    }
}
