package com.heshan.contentcalender.restImpl;

import com.heshan.contentcalender.POJO.Bill;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.rest.BillRest;
import com.heshan.contentcalender.service.BillService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BillRestImpl implements BillRest {
    @Autowired
    BillService billService;
    @Override
    public ResponseEntity<String> generateReport(Map<String,Object> requestMap) {
        try{
            return billService.generateReport(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return CafeUtils.getResponseEntity(CafeConstant.Something_Went_Wrong,HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Bill>> getBills() {
        try{
            return billService.getBills();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Bill> getByBillId(Integer id) {
        try{
            return billService.getBillById(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Bill(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Bill>> getByUsername(String username) {
        try{
            return billService.getBillsByUsername(username);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Bill>> getByUUID(String uuid) {
        try{
            return billService.getBillsByUUID(uuid);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateBill(Map<String, Object> requestMap) {
        try{
            return billService.updateBill(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try{
            return billService.deleteBill(id);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("unable to delete using this id",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        try{
            return billService.getPdf(requestMap);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new byte[0],HttpStatus.BAD_REQUEST);
    }




}
