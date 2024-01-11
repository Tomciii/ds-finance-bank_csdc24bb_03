package common.dto;

import java.io.Serializable;
import java.util.List;

public class ListStockDTO implements Serializable {

    private List<StockDTO> list;

    public ListStockDTO() {

    }

    public ListStockDTO(List<StockDTO> list) {
        this.list = list;
    }

    public List<StockDTO> getList() {
        return list;
    }

    public void setList(List<StockDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ListStockDTO{" +
                "list=" + list +
                '}';
    }

    public String cleanString(){
        StringBuilder returnvalue = new StringBuilder();

        for (StockDTO stock : list) {
            returnvalue.append("_____________________");
            returnvalue.append(stock.cleanString());
            returnvalue.append("_____________________");
            returnvalue.append(System.lineSeparator());
        }

        return returnvalue.toString();
    }
}
