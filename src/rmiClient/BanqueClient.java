package rmiClient;

import metier.Compte;
import rmiService.IBanque;
import javax.naming.InitialContext;
import java.util.Date;

public class BanqueClient {
    public static void main(String[] args) {
        try {
            // Utilisation du fichier jndi.properties pour la configuration
            InitialContext context = new InitialContext();

            // Lookup de l'objet distant via JNDI
            IBanque banqueService = (IBanque) context.lookup("BanqueService");

            System.out.println("=== Test de création de comptes ===");

            // Test 1 : Création d'un premier compte
            Compte compte1 = new Compte(1001, 1500.0, new Date());
            String result1 = banqueService.creerCompte(compte1);
            System.out.println("Résultat création compte 1: " + result1);

            // Test 2 : Création d'un deuxième compte
            Compte compte2 = new Compte(1002, 2500.0, new Date());
            String result2 = banqueService.creerCompte(compte2);
            System.out.println("Résultat création compte 2: " + result2);

            // Test 3 : Tentative de création d'un compte avec code existant
            Compte compte3 = new Compte(1001, 3000.0, new Date());
            String result3 = banqueService.creerCompte(compte3);
            System.out.println("Résultat création compte 3 (code existant): " + result3);

            System.out.println("\n=== Test de consultation de comptes ===");

            // Consultation des comptes
            String info1 = banqueService.getInfoCompte(1001);
            System.out.println("Consultation compte 1001: " + info1);

            String info2 = banqueService.getInfoCompte(1002);
            System.out.println("Consultation compte 1002: " + info2);

            String info3 = banqueService.getInfoCompte(9999);
            System.out.println("Consultation compte 9999 (inexistant): " + info3);

        } catch (Exception e) {
            System.err.println("Erreur client: " + e.getMessage());
            e.printStackTrace();
        }
    }
}