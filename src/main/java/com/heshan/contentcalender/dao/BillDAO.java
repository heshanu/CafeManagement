package com.heshan.contentcalender.dao;

import com.heshan.contentcalender.POJO.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillDAO extends JpaRepository<Bill, Integer>{

    List<Bill> findByCreatedBy(String id);


    Bill getBillById(Integer id);

    List<Bill> getBillsByUsername(String username);

    List<Bill> getBillsByUUID(String uuid);

    Bill getBillByUID(String uuid);


}
