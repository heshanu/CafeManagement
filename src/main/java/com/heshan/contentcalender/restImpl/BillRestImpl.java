package com.heshan.contentcalender.restImpl;

import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.rest.BillRest;
import com.heshan.contentcalender.service.BillService;
import com.heshan.contentcalender.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
