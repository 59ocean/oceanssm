package com.ocean.controller;

import org.springframework.stereotype.Controller;
import com.ocean.controller.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSON;
import com.ocean.utils.PageResult;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ocean.utils.AjaxResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ocean.service.IMenuService;
import com.ocean.entity.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.ocean.query.MenuQuery;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author chenhy
 * @since 2019-07-14
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
private final Logger logger = LoggerFactory.getLogger(MenuController.class);

@Autowired
public IMenuService menuService;

	/**
	 * 跳转列表页面
	 * @param request
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET,value = "/toList")
	public ModelAndView tomenuList(HttpServletRequest request) {
		return new ModelAndView("menu/menu_list");
	}

	/**
	 * 分页查询数据
	 *
	 * @param query   查询条件
	 * @return
	 */
	@ResponseBody
	@PostMapping("/list")
	public PageResult getMenuList(MenuQuery query) {
        Page<Menu> page = menuService.selectPage(query);
        return PageResult.ok(page.getTotal(),page.getRecords());
	}

	/**
	 * 跳转添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,value="/toAdd")
	public String menuAdd(HttpServletRequest request,HttpServletResponse response) {
		try {


		}catch (Exception ex){
		logger.error("menuAdd -=- {}",ex.toString());
		}
		return "menu/menu_add";
	}
	/**
	 * 保存
	 * @param menu  传递的实体
	 * @return  0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/save")
	public AjaxResponse menuSave(Menu menu) {
		AjaxResponse result = null;
		try {
			saveBaseEntity(menu);
			menuService.save(menu);
			result = AjaxResponse.ok();
		} catch (Exception e) {
			logger.error("menuSave -=- {}",e.toString());
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
	public String tomenuEdit(HttpServletRequest request,String id) {
		try {
			Menu menu = menuService.getById(id);
			request.setAttribute("menu",menu);
		}catch (Exception ex){
			logger.error("menuUpdate -=- {}",ex.toString());
		}
		return "menu/menu_edit";
	}

	/**
	* 更新
	* @param menu  传递的实体
	* @return  0 失败  1 成功
	*/
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST,value="/update")
	public AjaxResponse menuUpdate(Menu menu) {
        AjaxResponse result = null;
		try {
			updateBaseEntity(menu);
			menuService.updateById(menu);
			result = AjaxResponse.ok();
		} catch (Exception e) {
			logger.error("menuSave -=- {}",e.toString());
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
			Menu menu = menuService.getById(id);
			request.setAttribute("menu",menu);
		}catch (Exception ex){
			logger.error("menuUpdate -=- {}",ex.toString());
		}
		return "menu/menu_view";
	}

	/**
	 * 根据id删除对象
	 * @param id  实体ID
	 * @return 0 失败  1 成功
	 */
	@ResponseBody
	@RequestMapping(method= RequestMethod.POST,value="/delete")
	public AjaxResponse menuDelete(String id){
        AjaxResponse result = null;
		try {
	        menuService.removeById(id);
			result = AjaxResponse.ok();
		}catch (Exception e){
			logger.error("menuDelete -=- {}",e.toString());
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
			menuService.removeByIds(ids);
			result = AjaxResponse.ok();
        }catch (Exception e){
			e.printStackTrace();
			logger.error("menuDelete -=- {}",e.toString());
			result = AjaxResponse.errorMsg(e.getMessage());
        }
        return result;
	}


}