package com.heshan.contentcalender.serviceImpl;

import com.heshan.contentcalender.POJO.Bill;
import com.heshan.contentcalender.constant.CafeConstant;
import com.heshan.contentcalender.dao.BillDAO;
import com.heshan.contentcalender.service.BillService;
import com.heshan.contentcalender.utils.CafeUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
@Service
public class BillServiceImpl implements BillService {
    @Autowired
    BillDAO billDAO;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("generateReport");
        try{
            String fileName;
            if(validateRequestMap(requestMap)){
                System.out.println(requestMap.get("name"));
                if(requestMap.containsKey("isGenerate") && !(Boolean) requestMap.get("isGenerate") ){
                    fileName=(String) requestMap.get("uuid");
                }else {
                    fileName=CafeUtils.getUUID();
                    requestMap.put("uuid",fileName);
                    insertBill(requestMap);
                }
                String data="Name"+requestMap.get("name")+"\n"+"Email"+requestMap.get("email")+"\n"+"Contact Number"+requestMap.get("contactNumber")+"\n"+"Payment Method"
                        +requestMap.get("paymentMethod");


                Document document=new Document();
                PdfWriter.getInstance(document,new FileOutputStream(CafeConstant.STORE_LOCATION+fileName+".pdf"));

                document.open();
                setRectangleInPDF(document);

                Paragraph chunk=new Paragraph("Cafe Management System",getFont("Header"));
                chunk.setAlignment(Element.ALIGN_CENTER);
                document.add(chunk);

                Paragraph paragraph=new Paragraph(data+"\n\n",getFont("Data"));
                document.add(paragraph);

                PdfPTable table=new PdfPTable(5);
                table.setWidthPercentage(100);
                addTableHeader(table);

                JSONArray jsonArray=CafeUtils.getJSONArrayFromString((String)requestMap.get("productDetails"));
                for(int i=0;i<jsonArray.length();i++){
                    addRows(table,CafeUtils.getMapFromJSON(jsonArray.getString(i)));
                }
                document.add(table);
                Paragraph footer=new Paragraph("Total"+requestMap.get("total")+"\n\n"+"Thank you for visiting,Please visit again",getFont("Data"));
                document.add(footer);
                document.close();
                //billDAO.save(new Bill((Integer) requestMap.get("id"),(String) requestMap.get("name"),(String) requestMap.get("email"),(String) requestMap.get("contactNumber"),(String) requestMap.get("paymentMethod"),(String) requestMap.get("productDetails"),(String) requestMap.get("total"),(String) requestMap.get("uuid"),(String) requestMap.get("createdBy")));
                return new ResponseEntity<>("Report Generated Successfully"+"{\"uuid\"}"+fileName,HttpStatus.OK);
            }else {return CafeUtils.getResponseEntity("Required data not found", org.springframework.http.HttpStatus.BAD_REQUEST);}
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("unable to generate bill", org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Bill>> getBills() {
        log.info("inside getBills");
        try{
            List<Bill> bills=billDAO.findAll();
            return new ResponseEntity<>(bills, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Bill> getBillById(Integer id) {
        try{
            Bill bill=billDAO.getBillById(id);
            System.out.println(bill);
            return new ResponseEntity<>(bill,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new Bill(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Bill>> getBillsByUsername(String username) {
        try{
            List<Bill> bills=billDAO.getBillsByUsername(username);
            return new ResponseEntity<>(bills,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Bill>> getBillsByUUID(String uuid) {
        try{
            List<Bill> bills=billDAO.getBillsByUUID(uuid);
            return new ResponseEntity<>(bills,HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> updateBill(Map<String, Object> requestMap) {
        try{
            Object id=requestMap.get("id");
            if(id!=null){
                Bill bill=billDAO.getBillById((Integer) id);
                if(bill!=null){
                    bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
                    bill.setContactNumber((String) requestMap.get("contactNumber"));
                    bill.setEmail((String) requestMap.get("email"));
                    bill.setName((String) requestMap.get("name"));
                    bill.setCreatedBy((String) requestMap.get("createdBy"));
                    bill.setTotal((String) requestMap.get("total"));
                    bill.setProductDetails((String) requestMap.get("productDetails"));
                    bill.setTotal((String) requestMap.get("total"));
                    bill.setUuid((String) requestMap.get("uuid"));

                    billDAO.save(bill);
                    return new ResponseEntity<>("Bill updated successfully",HttpStatus.OK);
                }
                else {
                    return new ResponseEntity<>("Bill not found",HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return new ResponseEntity<>("Bill id not found",HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Unable to update bill",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try {
               Bill bill=billDAO.getBillById(id);
               if(bill!=null){
                   billDAO.delete(bill);
                   return new ResponseEntity<>("Bill deleted successfully",HttpStatus.OK);
               }
               else {
                   return new ResponseEntity<>("Bill not found",HttpStatus.BAD_REQUEST);
               }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("unable to delete this bill",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        log.info("InSide getPdf");
        try{
            byte[] byteArray=new byte[0];
            if(!requestMap.containsKey("uuid") && !validateRequestMap(requestMap)) return new ResponseEntity<>(byteArray,HttpStatus.BAD_REQUEST);
            else {
                String filePath = CafeConstant.STORE_LOCATION + "/" + (String) requestMap.get("uuid") + ".pdf";
                if(CafeUtils.isFileExist(filePath)){
                    byteArray=getByteArray(filePath);
                    return new ResponseEntity<>(byteArray,HttpStatus.OK);
                }

                else if(!CafeUtils.fileExitsInPC(filePath, (String) requestMap.get("uuid"))){
                    String fileName="Recovery"+(String) requestMap.get("uuid");
                    /*---------------------------------------------------------*/
                    String data="Name"+requestMap.get("name")+"\n"+"Email"+requestMap.get("email")+"\n"+"Contact Number"+requestMap.get("contactNumber")+"\n"+"Payment Method"
                            +requestMap.get("paymentMethod");

                    Document document=new Document();
                    PdfWriter.getInstance(document,new FileOutputStream(CafeConstant.STORE_LOCATION+fileName+".pdf"));

                    document.open();
                    setRectangleInPDF(document);

                    Paragraph chunk=new Paragraph("Cafe Management System",getFont("Header"));
                    chunk.setAlignment(Element.ALIGN_CENTER);
                    document.add(chunk);

                    Paragraph paragraph=new Paragraph(data+"\n\n",getFont("Data"));
                    document.add(paragraph);

                    PdfPTable table=new PdfPTable(5);
                    table.setWidthPercentage(100);
                    addTableHeader(table);

                    JSONArray jsonArray=CafeUtils.getJSONArrayFromString((String)requestMap.get("productDetails"));
                    for(int i=0;i<jsonArray.length();i++){
                        addRows(table,CafeUtils.getMapFromJSON(jsonArray.getString(i)));
                    }
                    document.add(table);
                    Paragraph footer=new Paragraph("Total"+requestMap.get("total")+"\n\n"+"Thank you for visiting,Please visit again",getFont("Data"));
                    document.add(footer);
                    document.close();
                    /*--------------------------------------------------------------*/

                }

                else {
                    requestMap.put("isGenerate",false);
                    generateReport(requestMap);
                    byteArray=getByteArray(filePath);
                    return new ResponseEntity<>(byteArray,HttpStatus.OK);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new byte[0],HttpStatus.BAD_REQUEST);
    }



    private byte[] getByteArray(String filePath) throws Exception{
        File initialFile = new File(filePath);
        InputStream targetStream = new FileInputStream(initialFile);
        byte[] byteArray= IOUtils.toByteArray(targetStream);
        targetStream.close();
        return byteArray;
    }

    private void addRows(PdfPTable table, Map<String, Object> mapFromJSON) {
        log.info("InSide addRows");
        table.addCell((String) mapFromJSON.get("name"));
        table.addCell((String) mapFromJSON.get("category"));
        table.addCell((String) mapFromJSON.get("quantity"));
        table.addCell((Double.toString((Double) mapFromJSON.get("price"))));
        table.addCell((String) mapFromJSON.get("subTotal"));
    }

    private void addTableHeader(PdfPTable table) {
        log.info("InSide addTableHeader");
        Stream.of("Name","Category","Quantity","Price","Sub Total")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle,getFont("Data")));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setVerticalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });
    }

    private Font getFont(String type) {
        log.info("InSide getFont");
        switch (type) {
            case "Header":
                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 18, Font.BOLDITALIC, BaseColor.BLACK);
                headerFont.setStyle(Font.BOLD);
                return headerFont;
            case "Data":
                Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLDITALIC, BaseColor.BLACK);
                dataFont.setStyle(Font.BOLD);
                return dataFont;
            default:
                return new Font(Font.FontFamily.HELVETICA, 12, Font.BOLDITALIC, BaseColor.BLACK);
        }
    }

    private void setRectangleInPDF(Document document) throws DocumentException {
        log.info("InSide setRectangleInPDF");
        Rectangle rect=new Rectangle(577,825,18,15);
        rect.enableBorderSide(1);
        rect.enableBorderSide(2);
        rect.enableBorderSide(4);
        rect.enableBorderSide(8);
        rect.setBackgroundColor(BaseColor.WHITE);
        rect.setBorderWidth(1);
        document.add(rect);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try{
         Bill bill=new Bill();
         bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setProductDetails((String) requestMap.get("productDetails"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setTotal((String)requestMap.get("total"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setCreatedBy((String) requestMap.get("createdBy"));
           billDAO.save(bill);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, Object> requestMap) {
        return  requestMap.containsKey("email")
                && requestMap.containsKey("contactNumber") && requestMap.containsKey("name")
                && requestMap.containsKey("paymentMethod") && requestMap.containsKey("productDetails") && requestMap.containsKey("total");
    }
}
