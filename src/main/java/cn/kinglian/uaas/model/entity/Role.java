package cn.kinglian.uaas.model.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenhy
 * @since 2019-07-02
 */
@Data
@Accessors(chain = true)
@TableName("t_role")
public class Role extends Model<Role> {

private static final long serialVersionUID = 1L;

                                            private String id;
        	/**
	 * 角色名称
	 */
                private String roleName;
        	/**
	 * 备注
	 */
                private String remark;


    	public static final String ID = "id";

    	public static final String ROLE_NAME = "role_name";

    	public static final String REMARK = "remark";

    @Override
protected Serializable pkVal() {
    			return this.id;
    		}

		}
