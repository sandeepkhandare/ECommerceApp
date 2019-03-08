package com.ecommerceapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductModel {

    @SerializedName("data")
    @Expose
    private List<Product> data = null;

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public static class Product {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("category_id")
        @Expose
        private String categoryId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("category_name")
        @Expose
        private String categoryName;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("qty")
        @Expose
        private String qty;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("sort_props")
        @Expose
        private SortProps sortProps;

        private String size;

        @SerializedName("size")

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public SortProps getSortProps() {
            return sortProps;
        }

        public void setSortProps(SortProps sortProps) {
            this.sortProps = sortProps;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public class SortProps {

            @SerializedName("A")
            @Expose
            private Integer a;
            @SerializedName("B")
            @Expose
            private Integer b;
            @SerializedName("C")
            @Expose
            private Integer c;

            public Integer getA() {
                return a;
            }

            public void setA(Integer a) {
                this.a = a;
            }

            public Integer getB() {
                return b;
            }

            public void setB(Integer b) {
                this.b = b;
            }

            public Integer getC() {
                return c;
            }

            public void setC(Integer c) {
                this.c = c;
            }

        }
    }

}
