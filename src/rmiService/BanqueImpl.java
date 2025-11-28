package rmiService;

import metier.Compte;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {
    private Map<Integer, Compte> comptes = new HashMap<>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public String creerCompte(Compte c) throws RemoteException {
        if (comptes.containsKey(c.getCode())) {
            return "Erreur : Un compte avec le code " + c.getCode() + " existe déjà.";
        }

        if (c.getDateCreation() == null) {
            c.setDateCreation(new Date());
        }

        comptes.put(c.getCode(), c);
        return "Compte créé avec succès - Code: " + c.getCode() +
                ", Solde: " + c.getSolde() +
                ", Date: " + c.getDateCreation();
    }

    @Override
    public String getInfoCompte(int code) throws RemoteException {
        Compte compte = comptes.get(code);
        if (compte == null) {
            return "Aucun compte trouvé avec le code: " + code;
        }
        return "Compte trouvé - Code: " + compte.getCode() +
                ", Solde: " + compte.getSolde() +
                ", Date de création: " + compte.getDateCreation();
    }
}