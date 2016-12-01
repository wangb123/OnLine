package org.wang.online.model;

import android.text.TextUtils;
import android.view.View;

import org.wang.online.util.LogUtils;

/**
 * 栏目
 * Created by 王冰 on 2016/11/29.
 */

public class RoomGroup implements View.OnClickListener {

    /**
     * slug : app-index
     * name : 首页轮播
     * category_slug :
     */
    private int id;
    private String title;
    private String link;
    private String create_at;
    private String subtitle;
    private String name;
    private String slug;
    private String category_slug;
    private String first_letter;
    private int status;
    private int prompt;
    private String image;
    private String thumb;
    private Ext ext;
    private int slot_id;
    private int priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
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

    public String getCategory_slug() {
        if (TextUtils.isEmpty(category_slug) && getExt() != null) return getExt().classification;
        return category_slug;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public Ext getExt() {
        return ext;
    }

    public void setExt(Ext ext) {
        this.ext = ext;
    }

    public String getFirst_letter() {
        return first_letter;
    }

    public void setFirst_letter(String first_letter) {
        this.first_letter = first_letter;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrompt() {
        return prompt;
    }

    public void setPrompt(int prompt) {
        this.prompt = prompt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void onClick(View v) {
        LogUtils.e("RoomGroup " + (TextUtils.isEmpty(getCategory_slug()) ? getSlug() : getCategory_slug()));
    }

    public static class Ext {
        private String classification;

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }
    }

    @Override
    public String toString() {
        return "RoomGroup{" +
                "slug='" + slug + '\'' +
                ", category_slug='" + category_slug + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
