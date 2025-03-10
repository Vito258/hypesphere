package com.elon.hypesphere.ware.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * <p>
 * 仓库信息
 * </p>
 *
 * @author elon
 * @since 2025-03-10
 */
@Getter
@Setter
@ToString
@TableName("wms_ware_info")
@ApiModel(value = "WareInfo对象", description = "仓库信息")
public class WareInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 仓库名
     */
    @TableField("name")
    @ApiModelProperty("仓库名")
    private String name;

    /**
     * 仓库地址
     */
    @TableField("address")
    @ApiModelProperty("仓库地址")
    private String address;

    /**
     * 区域编码
     */
    @TableField("areacode")
    @ApiModelProperty("区域编码")
    private String areacode;
}
