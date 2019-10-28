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
            r.setApplicability(it[(int)jsonCompanyMap.get("applicability")]);
            r.setArticle(it[(int)jsonCompanyMap.get("article")]);
            r.setBasePrice(it[(int)jsonCompanyMap.get("basePrice")]);
            r.setBrand(it[(int)jsonCompanyMap.get("brand")]);
            r.setCatalogueNumber(it[(int)jsonCompanyMap.get("catalogueNumber")]);
            r.setDeliveryTime(it[(int)jsonCompanyMap.get("deliveryTime")]);
            r.setDescription(it[(int)jsonCompanyMap.get("description")]);
            r.setMultiplicity(it[(int)jsonCompanyMap.get("multiplicity")]);
            r.setNomenclature(it[(int)jsonCompanyMap.get("nomenclature")]);
            r.setOEM(it[(int)jsonCompanyMap.get("OEM")]);
            r.setWeight(it[(int)jsonCompanyMap.get("weight")]);
            r.setVendorCode(it[(int)jsonCompanyMap.get("vendorCode")]);
            r.setPresence(it[(int)jsonCompanyMap.get("presence")]);
            r.setPrice(it[(int)jsonCompanyMap.get("price")]);
            reportList.add(r);
        });
        return reportList;
    }

}
