package dao;

public class ConcreteXMLFactory extends AbstractDAOFactory {

    public ConcreteXMLFactory(){
        super();
    }


    @Override
    public PartieDAO getPartieDAO() {
        return PartieXMLFactory.getInstance();
    }
}

