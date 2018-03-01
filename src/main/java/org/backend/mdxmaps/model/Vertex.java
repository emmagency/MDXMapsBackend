package org.backend.mdxmaps.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by Emmanuel Keboh on 25/02/2018.
 */
public class Vertex {
    private double g, h; //g: distance from start vertex to current vertex. h: straight line distance from current vertex to goal vertex.
    private LatLng latLng;
    private boolean isWheelChairAccessible;
    private String name, from;
    private String[] neighbors;

    public Vertex(String name, LatLng latLng, String[] neighbors, boolean isWheelChairAccessible) {
        this.name = name;
        this.latLng = latLng;
        this.neighbors = neighbors;
        this.isWheelChairAccessible = isWheelChairAccessible;
        g = h = Double.MAX_VALUE;
    }

    public void setG(double g) {
        this.g = g;
    }

    public void setH(double h) {
        this.h = h;
    }


    public void setFrom(String from) {
        this.from = from;
    }

    public double getG() {
        return g;
    }

    public Double getF() {
        return g + h;
    }

    public String getFrom() {
        return from;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public boolean isWheelChairAccessible() {
        return isWheelChairAccessible;
    }

    public String getName() {
        return name;
    }

    public void setNeighbors(String[] neighbors) {
        this.neighbors = neighbors;
    }

    public String[] getNeighbors() {
        return neighbors;
    }

    public static ArrayList<Vertex> getOutsideVertices() {
        ArrayList<Vertex> list = new ArrayList<>();

        list.add(new Vertex("A", new LatLng(51.590097, -0.228139), new String[]{"B"}, true));
        list.add(new Vertex("B", new LatLng(51.590315, -0.228194), new String[]{"A", "C", "V1"}, true));
        list.add(new Vertex("C", new LatLng(51.590350, -0.228599), new String[]{"B", "E", "I1"}, true));
        list.add(new Vertex("E", new LatLng(51.590379, -0.228964), new String[]{"C", "F", "K"}, true));
        list.add(new Vertex("F", new LatLng(51.590401, -0.229140), new String[]{"E", "G", "N1"}, true));
        list.add(new Vertex("G", new LatLng(51.590435, -0.229140), new String[]{"F", "H"}, true));
        list.add(new Vertex("H", new LatLng(51.590471, -0.229376), new String[]{"G", "I"}, true));
        list.add(new Vertex("I", new LatLng(51.590657, -0.229313), new String[]{"H", "A3", "P1"}, true));
        list.add(new Vertex("J", new LatLng(51.590830, -0.229251), new String[]{"P1", "H1"}, true));
        list.add(new Vertex("K", new LatLng(51.590168, -0.229023), new String[]{"E", "L", "R"}, true));
        list.add(new Vertex("L", new LatLng(51.590187, -0.229187), new String[]{"F", "K", "M"}, true));
        list.add(new Vertex("M", new LatLng(51.590159, -0.229616), new String[]{"L", "N", "Z"}, true));
        list.add(new Vertex("N", new LatLng(51.590096, -0.229637), new String[]{"M", "Q"}, true));
        list.add(new Vertex("O", new LatLng(51.589733, -0.229727), new String[]{"A15", "T", "Q"}, true));
        list.add(new Vertex("P", new LatLng(51.589749, -0.229884), new String[]{"A15", "A16"}, true));
        list.add(new Vertex("Q", new LatLng(51.589979, -0.229679), new String[]{"O", "N"}, true));
        list.add(new Vertex("R", new LatLng(51.589575, -0.229187), new String[]{"S", "K"}, true));
        list.add(new Vertex("S", new LatLng(51.589382, -0.229221), new String[]{"R", "U", "T"}, true));
        list.add(new Vertex("T", new LatLng(51.589445, -0.229749), new String[]{"O", "S", "A11", "W"}, true));
        list.add(new Vertex("U", new LatLng(51.588938, -0.229297), new String[]{"S", "V"}, true));
        list.add(new Vertex("V", new LatLng(51.589014, -0.229861), new String[]{"A11", "U", "A12", "K1"}, true));
        list.add(new Vertex("W", new LatLng(51.589479, -0.230074), new String[]{"T", "E1", "A9"}, true));
        list.add(new Vertex("X", new LatLng(51.589761, -0.230020), new String[]{"A16", "Y"}, true));
        list.add(new Vertex("Y", new LatLng(51.589967, -0.229947), new String[]{"X", "Z", "B1"}, true));
        list.add(new Vertex("Z", new LatLng(51.590184, -0.229870), new String[]{"M", "Y", "D1", "A1"}, false));
        list.add(new Vertex("A1", new LatLng(51.590377, -0.229911), new String[]{"Z", "F1", "O1"}, true));
        list.add(new Vertex("B1", new LatLng(51.589979, -0.230105), new String[]{"Y", "C1"}, false));
        list.add(new Vertex("C1", new LatLng(51.589993, -0.230266), new String[]{"D1", "A14", "B1"}, true));
        list.add(new Vertex("D1", new LatLng(51.590105, -0.230258), new String[]{"Z", "C1", "F1"}, true));
        list.add(new Vertex("E1", new LatLng(51.589616, -0.230412), new String[]{"W", "A13"}, true));
        list.add(new Vertex("F1", new LatLng(51.590471, -0.230036), new String[]{"A1", "D1", "G1"}, true));
        list.add(new Vertex("G1", new LatLng(51.590642, -0.229904), new String[]{"F1", "H1", "O1", "A5"}, true));
        list.add(new Vertex("H1", new LatLng(51.590891, -0.229726), new String[]{"J", "G1"}, true));
        list.add(new Vertex("I1", new LatLng(51.590564, -0.228539), new String[]{"C", "A3", "S1", "U1"}, false));
        list.add(new Vertex("J1", new LatLng(51.589134, -0.230500), new String[]{"A12", "A9", "A10"}, true));
        list.add(new Vertex("K1", new LatLng(51.588787, -0.229967), new String[]{"V", "L1", "Q1"}, true));
        list.add(new Vertex("L1", new LatLng(51.588917, -0.230597), new String[]{"A10", "K1"}, true));
        list.add(new Vertex("N1", new LatLng(51.590443, -0.229521), new String[]{"F", "O1"}, true));
        list.add(new Vertex("O1", new LatLng(51.590540, -0.229722), new String[]{"G1", "N1", "A1"}, true));
        list.add(new Vertex("P1", new LatLng(51.590736, -0.229285), new String[]{"I", "J"}, false));
        list.add(new Vertex("Q1", new LatLng(51.588700, -0.229998), new String[]{"K1", "X1", "Y1"}, true));
        list.add(new Vertex("R1", new LatLng(51.588579, -0.229606), new String[]{"Y1", "Z1"}, true));
        list.add(new Vertex("T1", new LatLng(51.590758, -0.228237), new String[]{"U1", "W1"}, false));
        list.add(new Vertex("S1", new LatLng(51.590540, -0.228306), new String[]{"I1", "W1", "A2"}, true));
        list.add(new Vertex("U1", new LatLng(51.590801, -0.228478), new String[]{"I1", "T1", "A2"}, true));
        list.add(new Vertex("V1", new LatLng(51.590650, -0.228098), new String[]{"B", "W1"}, true));
        list.add(new Vertex("W1", new LatLng(51.590664, -0.228257), new String[]{"S1", "T1", "V1"}, true));
        list.add(new Vertex("X1", new LatLng(51.588455, -0.230132), new String[]{"Q1"}, true));
        list.add(new Vertex("Z1", new LatLng(51.588526, -0.229428), new String[]{"R1"}, true));
        list.add(new Vertex("Y1", new LatLng(51.588643, -0.229776), new String[]{"Q1", "R1"}, true));
        list.add(new Vertex("A2", new LatLng(51.590578, -0.228537), new String[]{"S1", "U1"}, true));
        list.add(new Vertex("A3", new LatLng(51.590646, -0.229174), new String[]{"I", "A4", "I1"}, true));
        list.add(new Vertex("A4", new LatLng(51.590736, -0.229152), new String[]{"A3"}, true));
        list.add(new Vertex("A5", new LatLng(51.590669, -0.230289), new String[]{"G1", "A6"}, true));
        list.add(new Vertex("A6", new LatLng(51.590508, -0.230537), new String[]{"A8", "A5"}, true));
        list.add(new Vertex("A7", new LatLng(51.590537, -0.230941), new String[]{"A8"}, true));
        list.add(new Vertex("A8", new LatLng(51.590508, -0.230666), new String[]{"A6", "A7"}, true));
        list.add(new Vertex("A9", new LatLng(51.589365, -0.230148), new String[]{"W", "J1", "A11"}, true));
        list.add(new Vertex("A10", new LatLng(51.588977, -0.230572), new String[]{"L1", "J1"}, true));
        list.add(new Vertex("A11", new LatLng(51.589358, -0.2297769), new String[]{"T", "V", "A9"}, true));
        list.add(new Vertex("A12", new LatLng(51.589093, -0.230278), new String[]{"J1", "V"}, true));
        list.add(new Vertex("A13", new LatLng(51.589778, -0.230348), new String[]{"E1", "A14"}, true));
        list.add(new Vertex("A14", new LatLng(51.589878, -0.230309), new String[]{"A13", "C1"}, true));
        list.add(new Vertex("A15", new LatLng(51.589739, -0.229810), new String[]{"O", "P"}, true));
        list.add(new Vertex("A16", new LatLng(51.589754, -0.229946), new String[]{"P", "X"}, true));

        return list;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vertex)) return false;
        if (obj == this) return true;

        Vertex that = (Vertex) obj;
        return name.equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + ": " + getF();
    }
}
