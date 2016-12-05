package com.tutor93.indraaguslesmana.actlikepro.model;

/**
 * Created by indraaguslesmana on 12/1/16.
 */

public class translator {
    /**
     * "id": 9,
     * "name": "I like bacon",
     * "slug": "i-like-bacon",
     * "context_url": "http://www.tolq.com",
     * "description": "This is a description",
     * "created_at": "2014-09-16T13:17:04.368Z",
     * "completed_at": "2014-09-16T13:17:04.368Z",
     * "quality": "standard",
     * "status": "pending"
     * <p>
     * from documetations
     */

    private String id;
    private String name;
    private String slug;
    private String context_url;
    private String description;
    private String created_at;
    private String completed_at;
    private String quality;
    private String status;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContext_url() {
        return context_url;
    }

    public void setContext_url(String context_url) {
        this.context_url = context_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(String completed_at) {
        this.completed_at = completed_at;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
