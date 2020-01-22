package com.mssoftinc.jobcircular.ui.main;

class Product {
    String title,subtitle,slug;
    public Product() {
    }

    public Product(String title, String subtitle, String slug) {
        this.title = title;
        this.subtitle = subtitle;
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
