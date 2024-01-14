package net.froihofer.util.jboss.persistance.mapper;

import common.dto.DepotDTO;
import common.dto.SharesDTO;
import net.froihofer.util.jboss.persistance.entity.Depot;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class DepotMapper {

    @Inject
    SharesMapper sharesMapper;

    public DepotDTO toDepotDTO(Depot depot) {

        if (depot == null) {
            return null;
        }

        DepotDTO depotDTO = new DepotDTO();

        depotDTO.setId(depot.getId());

        List<SharesDTO> sharesDTOS = new ArrayList<>();

        for (var stock : depot.getShares()) {
            sharesDTOS.add(sharesMapper.toStockDTO(stock));
        }

        depotDTO.setShares(sharesDTOS);

        return depotDTO;
    }

    public DepotDTO toDepotDTOwithPrice(Depot depot, ArrayList<Double> stockValues) {

        if (depot == null) {
            return null;
        }

        DepotDTO depotDTO = new DepotDTO();

        depotDTO.setId(depot.getId());

        List<SharesDTO> sharesDTOS = new ArrayList<>();

        double depotVolume = 0;

        for(int i=0; i<stockValues.size(); i++){
            sharesDTOS.add(sharesMapper.toStockDTOWithPrice(depot.getShares().get(i), depot.getShares().get(i).getStockShares()*stockValues.get(i)));

            depotVolume+=depot.getShares().get(i).getStockShares()*stockValues.get(i);

        }

        depotDTO.setShares(sharesDTOS);
        depotDTO.setDepotVolume(depotVolume);

        return depotDTO;
    }

}
