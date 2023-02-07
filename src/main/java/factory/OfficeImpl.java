package factory;

public class OfficeImpl implements Office {
    @InjectByType
    private Worker worker;

    public OfficeImpl() {
        System.out.println("Можно начинать работу");
    }

    public void startWork() {
        worker.check();
    }
}
