package dao;

public abstract class AbstractDAOFactory {

    public static final int XML = 1;

    public abstract PartieDAO getPartieDAO();

    public static AbstractDAOFactory getAbstractDAOFactory(int id){
        switch (id){
            case XML :
                return new ConcreteXMLFactory();
            default :
                return new ConcreteXMLFactory();
        }
    }
}
