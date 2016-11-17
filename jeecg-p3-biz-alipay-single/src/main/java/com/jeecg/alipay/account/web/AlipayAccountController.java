package com.jeecg.alipay.account.web;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.jeecgframework.minidao.pojo.MiniDaoPage;
import org.jeecgframework.p3.core.common.utils.AjaxJson;
import org.jeecgframework.p3.core.page.SystemTools;
import org.jeecgframework.p3.core.util.plugin.ViewVelocity;
import org.jeecgframework.p3.core.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeecg.alipay.account.dao.AlipayAccountDao;
import com.jeecg.alipay.account.entity.AlipayAccount;

 /**
 * 描述：</b>alipayAccountController<br>账号信息表
 * @author Alex
 * @since：2016年03月24日 14时55分37秒 星期四 
 * @version:1.0
 */
@Controller
@RequestMapping("/alipay/alipayAccount")
public class AlipayAccountController extends BaseController{
  @Autowired
  private AlipayAccountDao alipayAccountDao;
  
	/**
	  * 列表页面
	  * @return
	  */
	@RequestMapping(params = "list",method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute AlipayAccount query,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false, value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(required = false, value = "pageSize", defaultValue = "10") int pageSize) throws Exception{
			try {
			 	LOG.info(request, " back list");
			 	//分页数据
				MiniDaoPage<AlipayAccount> list =  alipayAccountDao.getAll(query,pageNo,pageSize);
				VelocityContext velocityContext = new VelocityContext();
				velocityContext.put("alipayAccount",query);
				velocityContext.put("pageInfos",SystemTools.convertPaginatedList(list));
				velocityContext.put("num", list.getResults().size());
				String viewName = "alipay/account/alipayAccount-list.vm";
				ViewVelocity.view(request,response,viewName,velocityContext);
			} catch (Exception e) {
			e.printStackTrace();
			}
}

	 /**
	  * 详情
	  * @return
	  */
	@RequestMapping(params="toDetail",method = RequestMethod.GET)
	public void alipayAccountDetail(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request)throws Exception{
			VelocityContext velocityContext = new VelocityContext();
			String viewName = "alipay/account/alipayAccount-detail.vm";
			AlipayAccount alipayAccount = alipayAccountDao.get(id);
			velocityContext.put("alipayAccount",alipayAccount);
			ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(params = "toAdd",method ={RequestMethod.GET, RequestMethod.POST})
	public void toAddDialog(HttpServletRequest request,HttpServletResponse response)throws Exception{
		 VelocityContext velocityContext = new VelocityContext();
		 String viewName = "alipay/account/alipayAccount-add.vm";
		 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 保存信息
	 * @return
	 */
	@RequestMapping(params = "doAdd",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doAdd(@ModelAttribute AlipayAccount alipayAccount){
		AjaxJson j = new AjaxJson();
		try {
			//update-begin--author:zhangjiaqiang Date:20161110 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
			List<AlipayAccount> allAlipayAccount = alipayAccountDao.getAllAlipayAccount();
			if(allAlipayAccount.size() > 0){
				j.setSuccess(false);
				j.setMsg("只支持添加唯一的账号");
			}else{
				String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
				alipayAccount.setId(randomSeed);
				alipayAccountDao.insert(alipayAccount);
				j.setMsg("保存成功");
			}
			//update-end--author:zhangjiaqiang Date:20161110 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("保存失败");
		}
		return j;
	}

	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping(params="toEdit",method = RequestMethod.GET)
	public void toEdit(@RequestParam(required = true, value = "id" ) String id,HttpServletResponse response,HttpServletRequest request) throws Exception{
			 VelocityContext velocityContext = new VelocityContext();
			 AlipayAccount alipayAccount = alipayAccountDao.get(id);
			 velocityContext.put("alipayAccount",alipayAccount);
			 String viewName = "alipay/account/alipayAccount-edit.vm";
			 ViewVelocity.view(request,response,viewName,velocityContext);
	}

	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping(params = "doEdit",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson doEdit(@ModelAttribute AlipayAccount alipayAccount){
		AjaxJson j = new AjaxJson();
		try {
			alipayAccountDao.update(alipayAccount);
			j.setMsg("编辑成功");
		} catch (Exception e) {
		    log.info(e.getMessage());
			j.setSuccess(false);
			j.setMsg("编辑失败");
		}
		return j;
	}


	/**
	 * 删除
	 * @return
	 */
	@RequestMapping(params="doDelete",method = RequestMethod.GET)
	@ResponseBody
	public AjaxJson doDelete(@RequestParam(required = true, value = "id" ) String id){
			AjaxJson j = new AjaxJson();
			try {
			    AlipayAccount alipayAccount = new AlipayAccount();
				alipayAccount.setId(id);
				alipayAccountDao.delete(alipayAccount);
				j.setMsg("删除成功");
			} catch (Exception e) {
			    log.info(e.getMessage());
				j.setSuccess(false);
				j.setMsg("删除失败");
			}
			return j;
	}


}

