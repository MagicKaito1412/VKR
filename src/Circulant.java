public class Circulant extends Network {

    private int k, s1, s2;

    public Circulant(int k) {
        this.k = k;
        //диаметр предельно оптимального циркулянта
        int d = (int) Math.round((Math.sqrt(2*k - 1) - 1)/2);
        this.s1 = d;
        this.s2 = d + 1;

    }

    private void setNetlist(int[][] netlist) {
        this.netlist = netlist;
    }

    private void setRouting(int[][] routing) {
        this.routing = routing;
    }

    public int[][] getNetlist() {
        return this.netlist;
    }

    public int[][] getRouting() {
        return this.routing;
    }

    public void createNetlist() {
        int[][] netlist = new int[k][PORT_NUMBER];
        for (int id = 0; id < k; id++) {
            int zero, one, zeroPlus, onePlus;
            // изначально задано, что s1 < s2, поэтому:
            zero = id + s1;
            one = id + s2;
            onePlus = one - k;
            zeroPlus = zero - k;
            if ((id + s1) > (k - 1)) {
                netlist[id][0] = zeroPlus;
                netlist[id][1] = onePlus;
                netlist[zeroPlus][3] = id;
                netlist[onePlus][2] = id;

            } else if ((id + s2) > (k - 1)) {
                netlist[id][0] = zero;
                netlist[id][1] = onePlus;
                netlist[zero][3] = id;
                netlist[onePlus][2] = id;

            } else {
                netlist[id][0] = zero;
                netlist[id][1] = one;
                netlist[zero][3] = id;
                netlist[one][2] = id;
            }
        }

        setNetlist(netlist);
    }

    public void createRouting() {
        int[][] routing = new int[k][k];

        setRouting(routing);
    }
}
