abstract class Network {

    public static final int PORT_NUMBER = 4;

    // таблицы соединений и маршрутизации
    protected int [][] netlist, routing;

    abstract void createNetlist();
    abstract void createRouting();
}
