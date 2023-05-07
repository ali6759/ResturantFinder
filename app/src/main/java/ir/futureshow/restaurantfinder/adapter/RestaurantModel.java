package ir.futureshow.restaurantfinder.adapter;

public class RestaurantModel {

    private int id;
    private String name;
    private String type;
    private String rate;
    private String tel;
    private String address;
    private String website;
    private String fav_status;
    private int image1;
    private int image2;

    public RestaurantModel() {
    }

    public RestaurantModel(int id, String name, String type, String rate, String tel, String address, String website, String fav_status, int image1, int image2) {
        this.name = name;
        this.type = type;
        this.rate = rate;
        this.tel = tel;
        this.address = address;
        this.website = website;
        this.id = id;
        this.fav_status = fav_status;
        this.image1 = image1;
        this.image2 = image2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFav_status() {
        return fav_status;
    }

    public void setFav_status(String fav_status) {
        this.fav_status = fav_status;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }
}
