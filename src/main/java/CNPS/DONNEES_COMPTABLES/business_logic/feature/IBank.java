package CNPS.DONNEES_COMPTABLES.business_logic.feature;

import CNPS.DONNEES_COMPTABLES.dto.Action;
import CNPS.DONNEES_COMPTABLES.dto.BankDTO;
import CNPS.DONNEES_COMPTABLES.jpa.entity.Bank;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBank {
    Action<Bank> saveBank(BankDTO bankDTO);
    List<Bank> findAllBanks();
    List<Bank> filterBanks(String searchTerm);
    Action<Bank> updateBank(UUID bankId, BankDTO bankDTO);
    Action<Bank> deleteBank(UUID bankId);
}
