package factory;

public class Main {
    public static void main(String[] args) {
        Office office = ObjectFactory.getInstance().createObject(Office.class);
        office.startWork();
    }
}
