package com.std22073.marcheurblanc;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MarcheurBlancApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testMarcher_DestinationAtteinte() {
        // Initialiser le MarcheurBlanc avec HEI comme lieu actuel et ESTI comme destination
        MarcheurBlanc marcheurBlanc = new MarcheurBlanc("Bjarni", new Lieu("HEI"), new Lieu("ESTI"), new ArrayList<>());

        // Faire marcher le MarcheurBlanc jusqu'à ce qu'il atteigne sa destination
        marcheurBlanc.marcher();

        // Vérifier que le MarcheurBlanc a atteint sa destination
        assertEquals("ESTI", marcheurBlanc.getLieuActuel().getNom());
    }

    @Test
    public void testMarcher_PasDeVoisins() {
        // Initialiser le MarcheurBlanc avec HEI comme lieu actuel et ESTI comme destination
        MarcheurBlanc marcheurBlanc = new MarcheurBlanc("Bjarni", new Lieu("HEI"), new Lieu("ESTI"), new ArrayList<>());

        // Mocker la méthode Carte.getVoisins pour retourner une liste vide
        Mockito.when(Carte.getVoisins(Mockito.any())).thenReturn(Collections.emptyList());

        // Faire marcher le MarcheurBlanc
        marcheurBlanc.marcher();

        // Vérifier que le message "Le MarcheurBlanc est bloqué" a été affiché
        verify(System.out).println("Le MarcheurBlanc est bloqué, aucune destination disponible.");
    }

    @Test
    public void testMarcher_ChoixAleatoireDeVoisin() {
        // Initialiser le MarcheurBlanc avec HEI comme lieu actuel et ESTI comme destination
        MarcheurBlanc marcheurBlanc = new MarcheurBlanc("Bjarni", new Lieu("HEI"), new Lieu("ESTI"), new ArrayList<>());

        // Mocker la méthode Carte.getVoisins pour retourner une liste de deux voisins
        List<Lieu> voisins = Arrays.asList(new Lieu("Balançoire"), new Lieu("Sekolintsika"));
        Mockito.when(Carte.getVoisins(Mockito.any())).thenReturn(voisins);

        // Spyer la classe Random pour observer le choix aléatoire
        Random randomSpy = Mockito.spy(new Random());
        Mockito.doReturn(0).when(randomSpy).nextInt(Mockito.anyInt());

        // Faire marcher le MarcheurBlanc
        marcheurBlanc.marcher();

        // Vérifier que le MarcheurBlanc s'est déplacé vers le premier voisin (Balançoire)
        assertEquals("Balançoire", marcheurBlanc.getLieuActuel().getNom());
    }

}
