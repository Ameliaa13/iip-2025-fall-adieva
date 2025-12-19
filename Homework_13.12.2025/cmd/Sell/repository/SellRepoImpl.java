package cmd.Sell.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import cmd.Sell.Entity.SellEntity;

public class SellRepoImpl implements SellRepo {

    private ArrayList<SellEntity> sells = new ArrayList<>();
    private int nextId = 0;

    @Override
    public ArrayList<SellEntity> findAll() {
        return new ArrayList<>(sells);
    }

    @Override
    public void addSell(SellEntity sell) {
        sell.setId(nextId++);
        sells.add(sell);
    }

    @Override
    public SellEntity findById(long id) {
        for (SellEntity s : sells) {
            if (id == s.getId()) return s;
        }
        return null;
    }

    @Override
    public ArrayList<SellEntity> getSalesByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        ArrayList<SellEntity> filteredSells = new ArrayList<>();
        for (SellEntity sell : sells) {
            if ((sell.getDate().isAfter(startDate) || sell.getDate().isEqual(startDate)) 
                && (sell.getDate().isBefore(endDate) || sell.getDate().isEqual(endDate))) {
                filteredSells.add(sell);
            }
        }
        return filteredSells;
    }
    
}
