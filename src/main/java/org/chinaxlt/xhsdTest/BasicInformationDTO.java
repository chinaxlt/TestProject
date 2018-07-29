package org.chinaxlt.xhsdTest;

import lombok.Data;

import java.util.Date;

@Data
public class BasicInformationDTO {

    private Integer bi_id;//商品基本信息id，自增长

    private Integer goods_id;//商品id

    private String goods_name;//商品名称

    private String goods_subtitle;//商品副标题

    private String goods_image;//商品默认封面图片

    private String goods_image_more;//商品多图

    private Float goods_store_price;//商品店铺价格

    private String goods_serial;//商品货号

    private Long create_time;//

    private String goods_keywords;//商品关键字

    private String goods_description;//商品描述

    private String goods_body;//商品详细内容

    private String goods_attr;//商品属性

    private Long update_time;//更新时间

    private Float goods_market_price;//市场价

    private Float goods_cost_price;//成本价

    private Integer goods_is_suit;//是否套装。0：否，1：是

    private String comment_tags;//评价标签，逗号分隔的字符串，最多20个。新增商品时，继承分类的评价标签，可以修改。

    private Integer book_medium;//媒质。0：图书，1：电子书

    private String book_isbn;//ISBN号

    private String book_author;//作者

    private String book_publisher;//出版社

    private String book_publish_date;//出版时间

    private String book_publish_place;//版地

    private String book_publish_count;//版次

    private String book_print_count;//印次

    private String book_print_date;//印刷时间

    private String book_first_print_date;//首版时间

    private String book_paper_size;//开本

    private Integer book_paper_type;//用纸。0：普通纸，1：胶版纸，2：铜版纸

    private Integer book_package_type;//包装。0：平装，1：精装，2：盒装

    private String book_language;//正文语种。

    private Integer book_edition;//版本。0：影印版，1：原版

    private Integer book_is_phonetic;//是否注音。0：否，1：是

    private String book_page_count;//页数

    private String book_print_papger_count;//印张

    private String book_word_count;//字数

    private String book_print_number;//印数

    private String book_size_length;//长

    private String book_size_width;//宽

    private String book_size_height;//高

    private String book_weight;//重量

    private String book_cip_number;//中图法分类号

    private String book_serial_name;//丛书名

    private String book_attachment;//附赠及数量

    private String book_author_country;//作者国别/朝代

    private Integer book_has_epub;//是否有电子书。0：否，1：是

    private Long book_epub_time;//电子书上线时间

    private String book_cip_code;//CIP核字

    private String book_editor;//编者

    private String book_translator;//译者

    private String book_painter;//绘者

    private String book_editor_recommend;//编辑推荐

    private String book_synopsis;//内容推荐

    private String book_author_desc;//作者简介

    private String book_catalog;//目录

    private String book_sample_chapter;//试读章节

    private String book_preface;//序言

    private String book_lead;//导语

    private String book_postscript;//后记

    private String book_review;//书评(媒体评论)

    private String book_subject;//主题词

    private String book_img_face;//封面图

    private String book_img_copyright;//版权图

    private String book_img_back;//封底图

    private String book_img_wonderful;//精彩页图

    private String book_img_title;//书名页

    private String book_publish_target;//发行范围

    private String sell_class;//营销分类

    private String book_adaptor;//改编

    private String book_proofreader;//校注

    private String book_photographer;//摄影

    private String book_dictation;//口述

    private String book_organizer;//整理

    private String book_publisher_country;//出版商国别

    private String book_copyright_provider;//版权提供者

    private String book_copyright_number;//著作权合同登记号

    private String book_planner;//策划人

    private String book_ad_word;//广告语

    private String reader_target;//读者对象

    private Date as_import_time;//导入的时间

    private Integer as_copy_state;//合并到主表的状态。0：未合并

    private String book_barcode;//条形码

    private Integer staus;//状态。用于标注是否已同步

}