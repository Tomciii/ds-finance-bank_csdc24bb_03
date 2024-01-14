package net.froihofer.util.jboss.persistance.mapper;

import common.bankingInterface.BankingInterface;
import common.dto.SharesDTO;
import net.froihofer.util.jboss.BankingInterfaceImpl;
import net.froihofer.util.jboss.persistance.entity.Shares;

public class SharesMapper {

    public SharesDTO toStockDTO(Shares shares) {
        if (shares == null) {
            return new SharesDTO();
        }

        SharesDTO dto = new SharesDTO();

        dto.setStockShares(shares.getStockShares());
        dto.setStockName(shares.getStockName());
        dto.setStockValue(23);

        return dto;
    }

    public SharesDTO toStockDTOWithPrice(Shares shares, Double price) {
        if (shares == null) {
            return new SharesDTO();
        }

        SharesDTO dto = new SharesDTO();

        dto.setStockShares(shares.getStockShares());
        dto.setStockName(shares.getStockName());
        dto.setStockValue(price);
        dto.setFullName(shares.getstockname_realName());

        return dto;
    }
}
