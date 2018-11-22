package com.cecilia.framework.module.main.bean;

public class BoutiqueBean {

        private int id;
        private String brand_name;
        private String logo;
        public void setId(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }
        public String getBrand_name() {
            return brand_name;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }
        public String getLogo() {
            return logo;
        }
}
