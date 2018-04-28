package dao;

import model.Partie;

public interface PartieDAO {

    public void load(String chemin, Partie partie);
    public void save(Partie partie);
}
