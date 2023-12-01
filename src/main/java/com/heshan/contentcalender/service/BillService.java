package com.heshan.contentcalender.service;

import com.heshan.contentcalender.POJO.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface BillService {
    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<Bill>> getBills();

    ResponseEntity<Bill> getBillById(Integer id);

    ResponseEntity<List<Bill>> getBillsByUsername(String username);

    ResponseEntity<List<Bill>> getBillsByUUID(String uuid);

    ResponseEntity<String> updateBill(Map<String, Object> requestMap);

    ResponseEntity<String> deleteBill(Integer id);

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);


}
