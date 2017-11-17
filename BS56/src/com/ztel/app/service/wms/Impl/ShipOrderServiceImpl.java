package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.ShipOrderVoMapper;
import com.ztel.app.service.wms.ShipOrderService;
import com.ztel.app.vo.wms.ShipOrderVo;
import com.ztel.framework.vo.Pagination;

@Service
public class ShipOrderServiceImpl implements ShipOrderService {

	@Autowired
	private ShipOrderVoMapper shipOrderVoMapper = null;
	
	private Map<String, String> sortKeyMapping = new HashMap<>();
	
	public ShipOrderServiceImpl() {
		//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//		sortKeyMapping.put(key, value)
		sortKeyMapping.put("totalqty", "totalqty");
		sortKeyMapping.put("totalamount", "totalamount");
		sortKeyMapping.put("routecode", "routecode");
	}
	
	public List<ShipOrderVo> selectShiporderPageList(Pagination<?> page)
	{
		page.sortKeyToColumn(sortKeyMapping);
		return shipOrderVoMapper.selectShiporderPageList(page);
	}
	@Override
	public List<ShipOrderVo> selectRoutecodeList(ShipOrderVo shipOrderVo) {
		// TODO Auto-generated method stub
		return shipOrderVoMapper.selectRoutecodeList(shipOrderVo);
	}
	@Override
	public int doUpdateShipOrder(ShipOrderVo shipOrderVo) {
		// TODO Auto-generated method stub
		return shipOrderVoMapper.updateByPrimaryKeySelective(shipOrderVo);
	}
	@Override
	public ShipOrderVo getShipOrderByOrderNo(String orderNo) {
		// TODO Auto-generated method stub
		return shipOrderVoMapper.selectByPrimaryKey(orderNo);
	}
	/**
	 * 工商协同：品牌销售汇总
	 * @param shipOrderVo
	 * @return
	 */
	public List<ShipOrderVo>  selectShiporderBrandSaleReport(ShipOrderVo shipOrderVo){
		return shipOrderVoMapper.selectShiporderBrandSaleReport(shipOrderVo);
	}
	/**
	 * 订单分析，需要分两次获取订单信息
	 */
	public List<ShipOrderVo> selectShiporderAnalyzeList(ShipOrderVo shipOrderVo){
		List<ShipOrderVo> shipOrderVoList = new ArrayList<ShipOrderVo>();
	 
		//1、获取指定日期下的车组户数、订单总数等
		ShipOrderVo shipOrderVo1 = new ShipOrderVo();
		shipOrderVo1.setBegintime(shipOrderVo.getBegintime());
		shipOrderVo1.setEndtime(shipOrderVo.getEndtime());
		List<ShipOrderVo> allList = shipOrderVoMapper.selectShiporderAnalyzeList(shipOrderVo1);
		//2、获取代扣户数和代扣金额
		shipOrderVo.setPaymentflag(new BigDecimal("10"));
		List<ShipOrderVo> paymentList = shipOrderVoMapper.selectShiporderAnalyzeList(shipOrderVo);
		if(allList!=null&&allList.size()>0){
			for(int i=0;i<allList.size();i++){
				ShipOrderVo shipOrderVo_all = allList.get(i);
				if(paymentList!=null&&paymentList.size()>0){
					for(int j=0;j<paymentList.size();j++){
						ShipOrderVo shipOrderVo_pay = paymentList.get(j);
						if(shipOrderVo_all.getRoutecode().equals(shipOrderVo_pay.getRoutecode())){
							shipOrderVo_all.setPaymentcount(shipOrderVo_pay.getOrdercount());
							shipOrderVo_all.setPaymentamount(shipOrderVo_pay.getTotalamount());
						}
					}
				}
				shipOrderVoList.add(shipOrderVo_all);
			}
		}
		return shipOrderVoList;
	}
	
	/**
	 * 取预付款客户订单 ,用于货款核算预付款客户车组查看
	 * @param shipOrderVo
	 * @return
	 */
	public List<ShipOrderVo> selectShiporderPerpayList(ShipOrderVo shipOrderVo){
		List<ShipOrderVo> resultList = new ArrayList<ShipOrderVo>();
		//需要添加每个车组的合计后返回
		List<ShipOrderVo> shipOrderList = shipOrderVoMapper.selectShiporderPerpayList(shipOrderVo);
		if(shipOrderList!=null&&shipOrderList.size()>0){
			String routecodeTmp = "";
			BigDecimal amountAll = new BigDecimal("0");//合计金额
			BigDecimal qtyAll = new BigDecimal("0");//合计条数
			ShipOrderVo shipOrderVo3 = new ShipOrderVo();
			
			for(int i=0;i<shipOrderList.size();i++){
				ShipOrderVo shipOrderVo2 = shipOrderList.get(i);
				
				String routecode = shipOrderVo2.getRoutecode();
				if(i==0){
					amountAll =  amountAll.add(shipOrderVo2.getTotalamount());
					qtyAll = qtyAll.add(shipOrderVo2.getTotalqty());
					if(shipOrderList.size()==1){//如果所有数据只有一条记录
						resultList.add(shipOrderVo2);
						 shipOrderVo3 = new ShipOrderVo();
						shipOrderVo3.setRoutecode("小计");
						shipOrderVo3.setCustomername("");
						shipOrderVo3.setTotalamount(amountAll);
						shipOrderVo3.setTotalqty(qtyAll);
						resultList.add(shipOrderVo3);
					}
				}
				if(i!=0 ){
						if(!routecode.equals(routecodeTmp))
						{
							shipOrderVo3 = new ShipOrderVo();
							shipOrderVo3.setRoutecode("小计");
							shipOrderVo3.setCustomername("");
							shipOrderVo3.setTotalamount(amountAll);
							shipOrderVo3.setTotalqty(qtyAll);
							
							resultList.add(shipOrderVo3);
							amountAll = new BigDecimal("0");//合计金额
							qtyAll = new BigDecimal("0");//合计条数
						}
							amountAll =  amountAll.add(shipOrderVo2.getTotalamount());
							qtyAll = qtyAll.add(shipOrderVo2.getTotalqty());
					
				}
				if(shipOrderList.size()!=1)//所有记录不止1条，1条的情况前面已经考虑
				{
					resultList.add(shipOrderVo2);
					if( i==shipOrderList.size()-1  )//最后一项加上合计
					{
						shipOrderVo3 = new ShipOrderVo();
						shipOrderVo3.setRoutecode("小计");
						shipOrderVo3.setCustomername("");
//						amountAll =  amountAll.add(shipOrderVo2.getTotalamount());
//						qtyAll = qtyAll.add(shipOrderVo2.getTotalqty());
						shipOrderVo3.setTotalamount(amountAll);
						shipOrderVo3.setTotalqty(qtyAll);
						resultList.add(shipOrderVo3);
					}
				}
				
				routecodeTmp = routecode;
			}
		}
		return resultList;
	}

	public List<ShipOrderVo> selectShiporderPerpayCountList(ShipOrderVo shipOrderVo){
		List<ShipOrderVo> resultList = new ArrayList<ShipOrderVo>();
		List<ShipOrderVo> shipOrderVoList = shipOrderVoMapper.selectShiporderPerpayCountList(shipOrderVo);
		BigDecimal amountAll = new BigDecimal("0");
		BigDecimal qtyAll = new BigDecimal("0");
		if(shipOrderVoList!=null&&shipOrderVoList.size()>0){
			for(int i=0;i<shipOrderVoList.size();i++){
				ShipOrderVo shipOrderVo1 = shipOrderVoList.get(i);
				resultList.add(shipOrderVo1);
				amountAll = amountAll.add(shipOrderVo1.getTotalamount());
				qtyAll = qtyAll.add(shipOrderVo1.getTotalqty());
			}
			ShipOrderVo shipOrderVo2 = new ShipOrderVo();
			shipOrderVo2.setRoutecode("合计");
			shipOrderVo2.setTotalamount(amountAll);
			shipOrderVo2.setTotalqty(qtyAll);
			resultList.add(shipOrderVo2);
		}
		return resultList;
	}
	
	/**
	 * 货款核算中 ：预付款订单查看
	 * @param shipOrderVo
	 * @return
	 */
	public List<ShipOrderVo> selectShiporderPerpayOrderList(ShipOrderVo shipOrderVo){
		List<ShipOrderVo> resultList = new ArrayList<ShipOrderVo>();
		//需要添加每个车组的合计后返回
		List<ShipOrderVo> shipOrderList = shipOrderVoMapper.selectShiporderPerpayOrderList(shipOrderVo);
		if(shipOrderList!=null&&shipOrderList.size()>0){
			String fidTmp = "";
			String fnameTmp = "";
			BigDecimal amountAll = new BigDecimal("0");//合计金额
			BigDecimal qtyAll = new BigDecimal("0");//合计条数
			
			BigDecimal amountSum = new BigDecimal("0");//所有总合计金额
			BigDecimal qtySum = new BigDecimal("0");//所有总合计条数
			ShipOrderVo shipOrderVo3 = new ShipOrderVo();
			
			ShipOrderVo shipOrderVoSum = new ShipOrderVo();
			
			
			for(int i=0;i<shipOrderList.size();i++){
				ShipOrderVo shipOrderVo2 = shipOrderList.get(i);
				
				
				
				amountSum = amountSum.add(shipOrderVo2.getTotalamount());
				qtySum = qtySum.add(shipOrderVo2.getTotalqty());
				
				String fid = shipOrderVo2.getParentcustid();
				String fname = shipOrderVo2.getParentcustname();
				if(i==0){
					shipOrderVoSum.setCustomername(shipOrderVo2.getOrderdatestr()+"小计：");//总合计名称
					
					amountAll =  amountAll.add(shipOrderVo2.getTotalamount());
					qtyAll = qtyAll.add(shipOrderVo2.getTotalqty());
					if(shipOrderList.size()==1){//如果所有数据只有一条记录
						resultList.add(shipOrderVo2);
						 shipOrderVo3 = new ShipOrderVo();
						shipOrderVo3.setCustomername(fname+"小计：");
						shipOrderVo3.setTotalamount(amountAll);
						shipOrderVo3.setTotalqty(qtyAll);
						resultList.add(shipOrderVo3);
					}
				}
				if(i!=0 ){
						if(!fid.equals(fidTmp))
						{
							shipOrderVo3 = new ShipOrderVo();
							shipOrderVo3.setCustomername(fnameTmp+"小计：");
							shipOrderVo3.setTotalamount(amountAll);
							shipOrderVo3.setTotalqty(qtyAll);
							
							resultList.add(shipOrderVo3);
							amountAll = new BigDecimal("0");//合计金额
							qtyAll = new BigDecimal("0");//合计条数
						}
							amountAll =  amountAll.add(shipOrderVo2.getTotalamount());
							qtyAll = qtyAll.add(shipOrderVo2.getTotalqty());
					
				}
				if(shipOrderList.size()!=1)//所有记录不止1条，1条的情况前面已经考虑
				{
					resultList.add(shipOrderVo2);
					if( i==shipOrderList.size()-1  )//最后一项加上合计
					{
						shipOrderVo3 = new ShipOrderVo();
						shipOrderVo3.setCustomername(shipOrderVo2.getParentcustname()+"小计：");
//						amountAll =  amountAll.add(shipOrderVo2.getTotalamount());
//						qtyAll = qtyAll.add(shipOrderVo2.getTotalqty());
						shipOrderVo3.setTotalamount(amountAll);
						shipOrderVo3.setTotalqty(qtyAll);
						resultList.add(shipOrderVo3);
					}
				}
				
				fidTmp = fid;
				fnameTmp = fname;
			}
			shipOrderVoSum.setTotalamount(amountSum);
			shipOrderVoSum.setTotalqty(qtySum);
			resultList.add(shipOrderVoSum);
		}
		return resultList;
	}
	@Override
	public List<ShipOrderVo> selectShiporderByCondition(ShipOrderVo shipOrderVo) {
		// TODO Auto-generated method stub
		return shipOrderVoMapper.selectShiporderByCondition(shipOrderVo);
	}
}
