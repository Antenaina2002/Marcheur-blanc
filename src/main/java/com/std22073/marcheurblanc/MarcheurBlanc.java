package com.std22073.marcheurblanc;

import java.util.List;
import java.util.Random;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MarcheurBlanc {
    private String nom;

    private Lieu lieuActuel;

    private Lieu destination;

    private List<Lieu> lieuxConnus;

    public void marcher(){
        if (this.lieuActuel.equals(this.destination)) {
            System.out.println("Le MarcheurBlanc a atteint sa destination.");
            return;
        }
        List<Lieu> voisins = Carte.getVoisins(this.lieuActuel);
        if (!voisins.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(voisins.size());
            Lieu prochaineDestination = voisins.get(randomIndex);
            this.lieuActuel = prochaineDestination;
            System.out.println("Le MarcheurBlanc se déplace vers " + prochaineDestination.getNom());
        } else {
            System.out.println("Le MarcheurBlanc est bloqué, aucune destination disponible.");
        }
    }
}
