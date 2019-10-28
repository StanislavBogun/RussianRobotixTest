public class Report {
    private String nomenclature;
    private String brand;
    private String article;
    private String description;
    private String weight;
    private String multiplicity;
    private String price;
    private String basePrice;
    private String presence;
    private String deliveryTime;
    private String catalogueNumber;
    private String OEM;
    private String applicability;
    private String VendorCode;

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(String basePrice) {
        this.basePrice = basePrice;
    }

    public String getPresence() {
        return presence;
    }

    public void setPresence(String presence) {
        if(presence.contains("<") || presence.contains(">")) {
            presence.replace("<", "");
            presence.replace(">", "");
            this.presence = presence;
        } else if(presence.contains("-")) {
            String[] s = presence.split("-");
            int l = s.length-1;
            this.presence = s[l];
        } else {
            this.presence = presence;
        }
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getCatalogueNumber() {
        return catalogueNumber;
    }

    public void setCatalogueNumber(String catalogueNumber) {
        this.catalogueNumber = catalogueNumber.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }

    public String getOEM() {
        return OEM;
    }

    public void setOEM(String OEM) {
        this.OEM = OEM;
    }

    public String getApplicability() {
        return applicability;
    }

    public void setApplicability(String applicability) {
        this.applicability = applicability;
    }

    public String getVendorCode() {
        return VendorCode;
    }

    public void setVendorCode(String vendorCode) {
        VendorCode =vendorCode.replaceAll("[^a-zA-Z0-9]", "").toUpperCase();
    }
}
