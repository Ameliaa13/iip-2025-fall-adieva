package cmd.Sell.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import cmd.Sell.Entity.SellEntity;

public interface SellRepo {
    
    ArrayList<SellEntity> findAll();

    void addSell(SellEntity sell);

    SellEntity findById(long id);

    ArrayList<SellEntity> getSalesByPeriod(LocalDateTime startDate, LocalDateTime endDate);
}
