package common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DepotDTO implements Serializable {

    private int id;


    private List<SharesDTO> shares = new ArrayList<>();

    public double depotVolume;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShares(List<SharesDTO> shares) {
        this.shares = shares;
    }

    public List<SharesDTO> getShares() {
        return shares;
    }

    public DepotDTO(int id, int customer, List<SharesDTO> stocks) {
        this.id = id;
        this.shares = stocks;
    }

    public DepotDTO() {

    }


    public void setDepotVolume(double depotVolume) {
        this.depotVolume = depotVolume;
    }

    public double getDepotVolume() {
        return depotVolume;
    }

    @Override
    public String toString() {
        return "Depot Details:" + System.lineSeparator()
                + shares;
    }

}
