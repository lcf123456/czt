package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.CustomerVoMapper;
import com.ztel.app.service.wms.CustomerService;
import com.ztel.app.vo.cost.SuppliesImpVo;
import com.ztel.app.vo.gis.GroupinfoVo;
import com.ztel.app.vo.sys.PostInfoVo;
import com.ztel.app.vo.wms.CustomerVo;
import com.ztel.framework.vo.Pagination;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerVoMapper customerVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public CustomerServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
        //sortKeyMapping.put(key, value)
		//sortKeyMapping.put("deliveryseq", "deliveryseq");
	}

	/**
	 * 取零售户列表信息
	 */
	@Override
	public List<CustomerVo> getCustomersPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		//page.sortKeyToColumn(sortKeyMapping);
		List<CustomerVo> custList = customerVoMapper.getCustomersPageList(page);
		return custList;
	}
	
	/**
	 * 根据条件查询零售户，暂时传入了routecode参数，用于投诉受理零售户查询
	 */
	public List<CustomerVo>  getListByCond(CustomerVo customerVo){
		List<CustomerVo> custList = null;
		custList = customerVoMapper.selectListByCond(customerVo);
		return custList;
	}

	@Override
	public void viewCustomerVo(CustomerVo customervo) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public int doCustomerAdd(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		return customerVoMapper.insertSelective(customerVo);
	}

	@Override
	public List<CustomerVo> getOneLevelPrepayCustomers(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		List<CustomerVo> custList = customerVoMapper.getOneLevelPrepayCustomersIdStr(customerVo);
		int len=custList.size();
		CustomerVo vo=null;
		String customertype="",id="",prepayparentid="";
		//根据查询条件查询符合条件的一级预付款客户的id串
		List<String>lst=new ArrayList<>();
		for(int i=0;i<len;i++){
			vo=custList.get(i);
			id=vo.getId().toString();
			customertype=vo.getCustomertype();
			prepayparentid=vo.getPrepayparentid();
			if(prepayparentid==null||"".equals(prepayparentid))prepayparentid="0";
     		if("2".equals(customertype)){
     			if(!lst.contains(id)){
     				lst.add(id);
     			}
     		}else{
     			if(!"0".equals(prepayparentid)){
	     			if(!lst.contains(prepayparentid)){
	     				lst.add(prepayparentid);
	     			}
     			}
     		} 
     	}
		int blen=lst.size();
		int idarr[]=new int[blen];
		for(int i=0;i<blen;i++){
			idarr[i]=Integer.parseInt(lst.get(i));
		}
		
		Map<String,Object> map=new HashMap<>();
		map.put("ids", idarr);
		
		//查询一级预付款客户
		ArrayList<CustomerVo> oneLevelList = ( ArrayList<CustomerVo>)customerVoMapper.getOneLevelPrepayCustomers(map);
		
		return oneLevelList;
	}

	@Override
	public List<CustomerVo> getPrepayCustomers(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		List<CustomerVo> resultList = new ArrayList<CustomerVo>();
		List<CustomerVo> oneLevelList = getOneLevelPrepayCustomers(customerVo);
	    //判断一级预付款客户是否有值
		if(oneLevelList!=null&&oneLevelList.size()>0){
			for (int i = 0; i < oneLevelList.size(); i++) {
				CustomerVo oneLevelVo =oneLevelList.get(i);
				BigDecimal oneparentId = oneLevelVo.getId();
			    resultList.add(oneLevelVo);
			    
			    //取预付款客户子客户
			    customerVo.setPrepayparentid(oneparentId.toString());
			    List<CustomerVo> twoLevelList = this.customerVoMapper.getPrepayCustomers(customerVo);
			    if (twoLevelList!=null&&twoLevelList.size()>0) {
					  for (int j = 0; j < twoLevelList.size(); j++) {
						  CustomerVo twoLevelVo =twoLevelList.get(j);
						  resultList.add(twoLevelVo);
					  }
			    }
			}
		}
		return resultList;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int doCustomerPLAdd(List<String>  ids) {
		// TODO Auto-generated method stub
		CustomerVo customerVo=null;
		String prepayparentid=ids.get(0).toString();
		//id串的第一个存放的是一级客户id
		for(int i=1;i<ids.size();i++){
			customerVo=new CustomerVo();
			customerVo.setId(new BigDecimal(ids.get(i)));
			customerVo.setPrepayflag("1");
			customerVo.setPrepayparentid(prepayparentid);
			this.customerVoMapper.updateByPrimaryKeySelective(customerVo);
		}
		return ids.size();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int doDelPrepayCustomer(List<String> ids) {
		// TODO Auto-generated method stub
		CustomerVo customerVo=null;
		CustomerVo searchCustomerVo=null;
		BigDecimal id=null;String customertype="";
		for(int i=0;i<ids.size();i++){
			id=new BigDecimal(ids.get(i));
			customerVo=new CustomerVo();
			customerVo.setId(id);
			//判断客户类别
			searchCustomerVo=customerVoMapper.selectByPrimaryKey(id);
			customertype=searchCustomerVo.getCustomertype();
			//一级预付款客户删除
			if("2".equals(customertype)){
				customerVo.setDelstatus(new BigDecimal(0));
				//删除一级目录下的所有二级----------------------
				this.customerVoMapper.delAllTwoLevelPrepayCustomerByParentid(id.toString());
			}else{
				//二级预付款客户删除-----------------------------
				customerVo.setPrepayflag("0");
				customerVo.setPrepayparentid("0");
			}
			this.customerVoMapper.updateByPrimaryKeySelective(customerVo);
		}
		return ids.size();
	}

	@Override
	public List<CustomerVo> getBillCustomersPageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		List<CustomerVo> custList = customerVoMapper.getBillCustomersPageList(page);
		return custList;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int doBillCustomerPLAdd(String billtype, List<String> ids) {
		// TODO Auto-generated method stub
		CustomerVo customerVo=null;
		for(int i=0;i<ids.size();i++){
			customerVo=new CustomerVo();
			customerVo.setId(new BigDecimal(ids.get(i)));
			customerVo.setInvoiceflag("1");
			customerVo.setBilltype(billtype);
			this.customerVoMapper.updateByPrimaryKeySelective(customerVo);
		}
		return ids.size();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int doDelBillCustomer(List<String> ids) {
		// TODO Auto-generated method stub
		CustomerVo customerVo=null;
		BigDecimal id=null;
		for(int i=0;i<ids.size();i++){
			id=new BigDecimal(ids.get(i));
			customerVo=new CustomerVo();
			customerVo.setId(id);
			customerVo.setInvoiceflag("0");
			customerVo.setBilltype("");
			this.customerVoMapper.updateByPrimaryKeySelective(customerVo);
		}
		return ids.size();
	}
	
	/**
	 * 预付款一级客户下拉框
	 * @return
	 */
	@Override
	public List<HashMap<String, String>> getPrepaycustomerCombobox() {
		// TODO 自动生成的方法存根
		CustomerVo vo = new CustomerVo();
		vo.setCustomertype("2");
		List<CustomerVo> treeList=this.customerVoMapper.selectListByCond(vo);
	   	 List<HashMap<String, String>> boxList=new ArrayList<>();
	   	 if (treeList!=null&&treeList.size()>0) {
	   		 for(int i=0;i<treeList.size();i++){
	   			CustomerVo customerVo=treeList.get(i);
	   			 HashMap<String, String> map=new HashMap<String, String>();
	   			 map.put("id", customerVo.getId()+"");
	   			 map.put("name", customerVo.getName()+"");
	   			 boxList.add(map);
	   		 }
	   	 }
	   	 return boxList;
	}
}
