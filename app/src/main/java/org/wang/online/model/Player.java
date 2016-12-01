package org.wang.online.model;

/**
 * 主播
 * Created by 王冰 on 2016/11/29.
 */

public class Player {
    /**
     * recommend_image :
     * announcement :
     * title : 黄金世俱杯小组赛Day2（英文）
     * create_at : 2016-11-28 22:21:34
     * intro : 0点频道1直播炉石世锦赛败者组淘汰赛。3:00频道4将直播守望先锋世界杯小组赛A-D组第一、二轮的比赛
     * video : http://thumb.quanmin.tv/2616172.mp4?t=1480469400
     * screen : 0
     * love_cover :
     * category_id : 8
     * video_quality : 234
     * like : 0
     * default_image :
     * slug : blizzard2016
     * weight : 723360
     * status : 1
     * avatar : http://image.quanmin.tv/avatar/b3b272790826d2c1345a914258afb7d6?imageView2/2/w/300/
     * level : 0
     * uid : 2616172
     * play_at : 2016-11-28 22:21:35
     * view : 25003
     * category_slug : blizzard
     * nick : 暴雪游戏频道
     * beauty_cover :
     * app_shuffling_image :
     * start_time : 2016-11-30 05:44:01
     * follow : 12623
     * category_name : 暴雪经典
     * grade :
     * thumb : http://snap.quanmin.tv/2616172-1480469582-587.jpg?imageView2/2/w/390/
     * hidden : false
     */

    private String recommend_image;
    private String announcement;
    private String title;
    private String create_at;
    private String intro;
    private String video;
    private int screen;
    private String love_cover;
    private String category_id;
    private String video_quality;
    private String like;
    private String default_image;
    private String slug;
    private String weight;
    private String status;
    private String avatar;
    private String level;
    private String uid;
    private String play_at;
    private String view;
    private String category_slug;
    private String nick;
    private String beauty_cover;
    private String app_shuffling_image;
    private String start_time;
    private String follow;
    private String category_name;
    private String grade;
    private String thumb;
    private boolean hidden;

    public String getRecommend_image() {
        return recommend_image;
    }

    public void setRecommend_image(String recommend_image) {
        this.recommend_image = recommend_image;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public String getLove_cover() {
        return love_cover;
    }

    public void setLove_cover(String love_cover) {
        this.love_cover = love_cover;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getVideo_quality() {
        return video_quality;
    }

    public void setVideo_quality(String video_quality) {
        this.video_quality = video_quality;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getDefault_image() {
        return default_image;
    }

    public void setDefault_image(String default_image) {
        this.default_image = default_image;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPlay_at() {
        return play_at;
    }

    public void setPlay_at(String play_at) {
        this.play_at = play_at;
    }

    public String getView() {
        if (view.length() < 5) return view;
        StringBuilder sb = new StringBuilder(view.substring(0, view.length() - 4));
        sb.append('.');
        sb.append(view.substring(view.length() - 4, view.length() - 3));
        sb.append("万");
        return sb.toString();
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getCategory_slug() {
        return category_slug;
    }

    public void setCategory_slug(String category_slug) {
        this.category_slug = category_slug;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBeauty_cover() {
        return beauty_cover;
    }

    public void setBeauty_cover(String beauty_cover) {
        this.beauty_cover = beauty_cover;
    }

    public String getApp_shuffling_image() {
        return app_shuffling_image;
    }

    public void setApp_shuffling_image(String app_shuffling_image) {
        this.app_shuffling_image = app_shuffling_image;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
