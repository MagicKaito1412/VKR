public class Application {

    public static final String MESH_TOPOLOGY = "mesh";
    public static final String TORUS_TOPOLOGY = "tor";
    public static final String CIRCULANT_TOPOLOGY = "cir";

    public static void main(String[] args) {
        //NOTE 1: сейчас запуск программы осуществляется с консоли,
        // где входным параметром задаутся название топологии
        // и в зависимости от названия вызываются соответствующие
        // функции для построения таблиз связей и маршрутизации

        // NOTE 2: или добавить контроллер для обработки входных параметров
        if (MESH_TOPOLOGY.equals(args[0])) {
            //временный костылище
            int n, m;
            n = Integer.parseInt(args[1]);
            m = Integer.parseInt(args[2]);
            Mesh meshNetwork;
            if (n == m) {
                meshNetwork = new Mesh(n, n);
            } else {
                meshNetwork = new Mesh(n, m);
            }
            meshNetwork.createNetlist();
            meshNetwork.createRouting();
            printNetlist(m*n, meshNetwork.getNetlist());
            printRouting(m*n, meshNetwork.getRouting());
            //createXml(meshNetwork.getNetlist(), meshNetwork.getRouting());

        } else if (TORUS_TOPOLOGY.equals(args[0])) {
            int n, m;
            n = Integer.parseInt(args[1]);
            m = Integer.parseInt(args[2]);
            Torus torusNetwork;
            if (n == m) {
                torusNetwork = new Torus(n, n);
            } else {
                torusNetwork = new Torus(n, m);
            }
            torusNetwork.createNetlist();
            torusNetwork.createRouting();
            printNetlist(m*n, torusNetwork.getNetlist());
            printRouting(m*n, torusNetwork.getRouting());

        } else if (CIRCULANT_TOPOLOGY.equals(args[0])) {
            int k = Integer.parseInt(args[1]);

            if (k < 5) {
                System.out.println("Unexpected topology type!\nPlease check input args");
                return;
            } else {
                Circulant circulantNetwork = new Circulant(k);
                circulantNetwork.createNetlist();
                circulantNetwork.createRouting();
                printNetlist(k, circulantNetwork.getNetlist());
                printRouting(k, circulantNetwork.getRouting());
            }

        } else {
            System.out.println("Unexpected topology type!");
            return;
        }

    }

    public static void createXml(int[][] netlist, int[][] routing) {
        //TODO: implement xml creation
    }

    private static void printNetlist(int size, int[][] netlist) {
        System.out.println("====================================");
        System.out.println("Netlist:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(netlist[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printRouting(int size, int[][] routing) {
        System.out.println("====================================");
        System.out.println("Routing:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(routing[i][j] + "  ");
            }
            System.out.println();
        }
    }

    private static boolean wrongInputCirculant(int k, int s1, int s2) {
        int d = (int) Math.round((Math.sqrt(2*k - 1) - 1)/2); //диаметр предельно оптимального циркулянта
        //корректные параметры
        boolean right = s1 < s2
                && k > 4
                && s1 == d
                && s2 == d + 1
                && (double) k / 2 > 2;
        System.out.println((double) (k / 2));
        return !right;
    }
}
