package umn.ac.id.projekuts_00000015928;

import java.util.Comparator;

public class Product {
    private String asin;
    private String grup;
    private String format;
    private String title;
    private String author;
    private String publisher;

    public Product(String asin, String grup, String format, String title, String author, String publisher) {
        this.asin = asin;
        this.grup = grup;
        this.format = format;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    public Product(String title, String author){
        this.title = title;
        this.author = author;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public String getGrup() {
        return grup;
    }

    public void setGrup(String grup) {
        this.grup = grup;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public static final Comparator<Product> BY_TITLE_ASCENDING = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return product.getTitle().compareTo(t1.getTitle());
        }
    };

    public static final Comparator<Product> BY_TITLE_DESCENDING = new Comparator<Product>() {
        @Override
        public int compare(Product product, Product t1) {
            return t1.getTitle().compareTo(product.getTitle());
        }
    };

}
