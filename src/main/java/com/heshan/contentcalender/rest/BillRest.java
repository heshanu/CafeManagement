package com.heshan.contentcalender.rest;

import com.heshan.contentcalender.POJO.Bill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/bill")
public interface BillRest {
    @PostMapping("/generateReport")
    ResponseEntity<String> generateReport(@RequestBody Map<String,Object> requestMap);
    @GetMapping("/getBills")
    ResponseEntity<List<Bill>> getBills();
    @GetMapping("/getBillByBillId/{id}")
    ResponseEntity <Bill> getByBillId(@PathVariable Integer id);
    @GetMapping("/getBillByUsername/{username}")
   ResponseEntity <List<Bill>> getByUsername(@PathVariable String username);
    @GetMapping("/getBillByUUID/{uuid}")
    ResponseEntity <List<Bill>> getByUUID(@PathVariable String uuid);
    @PutMapping("/updateBill")
    ResponseEntity<String> updateBill(@RequestBody Map<String,Object> requestMap);
    @DeleteMapping("/deleteBill/{id}")
    ResponseEntity<String> deleteBill(@PathVariable Integer id);
    @PostMapping("/getPdf")
    ResponseEntity<byte[]> getPdf(@RequestBody Map<String,Object> requestMap);


}
