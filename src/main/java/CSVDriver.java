import au.com.bytecode.opencsv.CSVReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class CSVDriver {

    ArrayList<Report> getReportList(String vendor) throws IOException, ParseException {
        // TODO() Проверят всю папку и доставать из нее все CSV файлы
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\koytt\\IdeaProjects\\Robotics\\src\\main\\resources\\files\\10953.csv"), ';', '"', 1);
        List<String[]> allRows = reader.readAll();
        JSONObject jsonCompanyMap = Config.getCompanyMap(vendor);
        ArrayList<Report> reportList = new ArrayList<>();
        allRows.forEach(it -> {
            Report r = new Report();
                r.setApplicability(it[Integer.parseInt((jsonCompanyMap.get("applicability").toString()))]);
                r.setArticle(it[Integer.parseInt(jsonCompanyMap.get("article").toString())]);
                r.setBasePrice(it[Integer.parseInt(jsonCompanyMap.get("basePrice").toString())]);
                r.setBrand(it[Integer.parseInt(jsonCompanyMap.get("brand").toString())]);
                r.setCatalogueNumber(it[Integer.parseInt(jsonCompanyMap.get("catalogueNumber").toString())]);
                r.setDeliveryTime(it[Integer.parseInt(jsonCompanyMap.get("deliveryTime").toString())]);
                r.setDescription(it[Integer.parseInt(jsonCompanyMap.get("description").toString())]);
                r.setMultiplicity(it[Integer.parseInt(jsonCompanyMap.get("multiplicity").toString())]);
                r.setNomenclature(it[Integer.parseInt(jsonCompanyMap.get("nomenclature").toString())]);
                r.setOEM(it[Integer.parseInt(jsonCompanyMap.get("OEM").toString())]);
                r.setWeight(it[Integer.parseInt(jsonCompanyMap.get("weight").toString())]);
                System.out.println(jsonCompanyMap.get("vendorCode").toString());
                r.setVendorCode(it[Integer.parseInt(jsonCompanyMap.get("vendorCode").toString())]);
                r.setPresence(it[Integer.parseInt(jsonCompanyMap.get("presence").toString())]);
                r.setPrice(it[Integer.parseInt(jsonCompanyMap.get("price").toString())]);

                reportList.add(r);
        });
        return reportList;
    }

}
