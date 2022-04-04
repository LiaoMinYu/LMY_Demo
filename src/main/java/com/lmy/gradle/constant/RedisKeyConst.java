package com.lmy.gradle.constant;

public class RedisKeyConst {
    public static final String ALIAS = "v3:";
    public static final String KEY_FOR_FORM_ID = ALIAS + "formId";
    public static final String FORM_ID_FOR_COLLECT_INFORM = ALIAS + "formIdForCollect";
    public static final String POSTER_KEY = ALIAS + "poster";
    public static final String VIRTUAL_VIEW_NUM = ALIAS + "virtualViewNum";
    public static final String KEY_FOR_IMAGE_INFO = ALIAS + "image:";
    public static final String TONY_PAGE = ALIAS + "tonyResult:";
    public static final String TAG_READ_NUM = ALIAS + "tagReadNum";

    public static final long EXPIRE_TIME_WEEK = 604800L;

    public static final long EXPIRE_TIME_DAY = 86400L;

    public static final long EXPIRE_TIME_HOUR = 3600L;

    /**
     * 首页作品key
     */
    public static final String KEY_CUSTOM_PALETTE = ALIAS + "customPalette";
    public static final String KEY_CUSTOM_PALETTE_HASH = ALIAS + "customPaletteHash";
    public static final String KEY_VIP_PALETTE = ALIAS + "vipPalette";
    public static final String KEY_VIP_PALETTE_HASH = ALIAS + "vipPaletteHash";
    public static final String KEY_NETWORK_PALETTE = ALIAS + "networkPalette";
    public static final String KEY_NETWORK_PALETTE_HASH = ALIAS + "networkPaletteHash";

    public static final String KEY_ALL_PALETTE_HASH = ALIAS + "allPaletteHash";

    public static final String KEY_ALL_DETAIL_PALETTE_HASH = ALIAS + "allDetailPaletteHash";

    /**
     * 首页作品查询版本key
     */
    public static final String KEY_PALETTE_VERSION = ALIAS + "paletteVersion";

    /**
     * 黑瞳答题通过信息,用于前端显示控制
     */
    public static final String KEY_OFFICIAL_EXAM = ALIAS + "officialExam";

    /**
     * ins作品定时更新Gif
     */
    public static final String KEY_WORKSINSGIF_HASH = ALIAS + "worksInsGifHash";
}
