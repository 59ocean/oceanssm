package com.ocean.controller;

import com.ocean.query.RoleQuery;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ocean.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSON;
import com.ocean.utils.PageResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.ocean.utils.AjaxResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ocean.service.IRoleService;
import com.ocean.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author chenhy
 * @since 2019-07-13
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
private final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    public IRoleService roleService;

	/**
	 * 跳转列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET,value = "/toList")
    public ModelAndView index(HttpServletRequest request) {
        System.out.println("============");
	    return new ModelAndView("role/role_list");
	}

	/**
	 * 分页查询数据
	 *
	 * @param query   查询条件
	 * @return
	 */
	@ResponseBody
	@GetMapping("/list")
	public PageResult getRoleList(RoleQuery query) {
        Page<Role> page = roleService.selectPage(query);
        return PageResult.ok(page.getTotal(),page.getRecords());
	}

	/**
	 * 跳转添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/toAdd")
	public String roleAdd(HttpServletRequest request,HttpServletResponse response) {
		try {


		}catch (Exception ex){
		logger.error("roleAdd -=- {}",ex.toString());
		}
		return "role/role_add";
	}
	/**
	 * 保存
	 * @param role  传递的实体
	 * @return  0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/save")
	public AjaxResponse roleSave(Role role) {
		AjaxResponse result = null;
		try {
			saveBaseEntity(role);
			roleService.save(role);
			result = AjaxResponse.ok();
		} catch (Exception e) {
			logger.error("roleSave -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.toString());
		}
		return result;
	}

	/**
	 * 跳转修改页面
	 * @param request
	 * @param id  实体ID
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/toEdit")
	public String toroleEdit(HttpServletRequest request,String id) {
		try {
			Role role = roleService.getById(id);
			request.setAttribute("role",role);
		}catch (Exception ex){
			logger.error("roleUpdate -=- {}",ex.toString());
		}
		return "role/role_edit";
	}

	/**
	* 更新
	* @param role  传递的实体
	* @return  0 失败  1 成功
	*/
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/update")
	public AjaxResponse roleUpdate(Role role) {
        AjaxResponse result = null;
		try {
			updateBaseEntity(role);
			roleService.updateById(role);
			result = AjaxResponse.ok();
		} catch (Exception e) {
			logger.error("roleSave -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.toString());
		}
		return result;
	}
    /**
     * 跳转詳情页面
     * @param request
     * @param id  实体ID
     * @return
     */
    @RequestMapping(method=RequestMethod.GET,value="/view")
    public String view(HttpServletRequest request,String id) {
        try {
            Role role = roleService.getById(id);
            request.setAttribute("role",role);
        }catch (Exception ex){
            logger.error("roleUpdate -=- {}",ex.toString());
        }
        return "role/role_view";
    }

	/**
	 * 根据id删除对象
	 * @param id  实体ID
	 * @return 0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/delete")
	public AjaxResponse roleDelete(String id){
        AjaxResponse result = null;
		try {
	        roleService.removeById(id);
			result = AjaxResponse.ok();
		}catch (Exception e){
			logger.error("roleDelete -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.toString());
		}
		return result;
	}

	/**
	 * 批量删除对象
	 * @param item 实体集合ID
	 * @return  0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/batchDelete")
	public AjaxResponse deleteBatchIds(String item){
        AjaxResponse result = null;
        try {
			List<String> ids = (List<String>) JSON.parse(item);
			roleService.removeByIds(ids);
			result = AjaxResponse.ok();
        }catch (Exception e){
			e.printStackTrace();
			logger.error("roleDelete -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.getMessage());
        }
        return result;
	}


}