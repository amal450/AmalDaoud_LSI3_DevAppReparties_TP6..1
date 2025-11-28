package rmiServer;

import rmiService.BanqueImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

public class BanqueServer {
    public static void main(String[] args) {
        try {
            int port = 1098; // Port différent

            // Créer le registre RMI sur un port différent
            LocateRegistry.createRegistry(port);
            System.out.println("Registre RMI créé sur le port " + port);

            // Configuration des propriétés JNDI
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY,
                    "com.sun.jndi.rmi.registry.RegistryContextFactory");
            props.setProperty(Context.PROVIDER_URL, "rmi://localhost:" + port);

            // Création du contexte initial
            InitialContext context = new InitialContext(props);

            // Création et enregistrement de l'objet distant
            BanqueImpl banqueService = new BanqueImpl();

            // Binding de l'objet dans l'annuaire JNDI
            context.rebind("BanqueService", banqueService);

            System.out.println("Serveur RMI démarré sur le port " + port);
            System.out.println("Service Banque enregistré sous le nom: BanqueService");
            System.out.println("En attente des requêtes clients...");

        } catch (Exception e) {
            System.err.println("Erreur lors du démarrage du serveur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}