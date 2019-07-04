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
@TableName("t_user")
public class User extends Model<User> {

private static final long serialVersionUID = 1L;

                                            private String id;
        	/**
	 * 账号
	 */
                private String account;
        	/**
	 * 用户名称
	 */
                private String username;
        	/**
	 * 密码
	 */
                private String password;
        	/**
	 * 手机号码
	 */
                private String phone;
        	/**
	 * 邮箱
	 */
                private String email;
        	/**
	 * 状态：1启用 0 禁用
	 */
                private String status;
        	/**
	 * 部门id
	 */
                private String deptId;
        	/**
	 * 备注
	 */
                private String remark;


    	public static final String ID = "id";

    	public static final String ACCOUNT = "account";

    	public static final String USERNAME = "username";

    	public static final String PASSWORD = "password";

    	public static final String PHONE = "phone";

    	public static final String EMAIL = "email";

    	public static final String STATUS = "status";

    	public static final String DEPT_ID = "dept_id";

    	public static final String REMARK = "remark";

    @Override
protected Serializable pkVal() {
    			return this.id;
    		}

		}
