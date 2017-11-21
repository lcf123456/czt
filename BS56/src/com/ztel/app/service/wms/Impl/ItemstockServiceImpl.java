package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.InBoundLineVoMapper;
import com.ztel.app.persist.mybatis.wms.ItemstockLineVoMapper;
import com.ztel.app.persist.mybatis.wms.ItemstockVoMapper;
import com.ztel.app.persist.mybatis.wms.OutBoundLineVoMapper;
import com.ztel.app.service.wms.ItemstockService;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.app.vo.wms.ItemstockLineVo;
import com.ztel.app.vo.wms.ItemstockVo;
import com.ztel.app.vo.wms.OutBoundLineVo;
import com.ztel.app.vo.wms.OutBoundVo;

@Service
public class ItemstockServiceImpl implements ItemstockService {
	
	@Autowired
	private ItemstockVoMapper itemstockVoMapper = null;
	
	@Autowired
	private ItemstockLineVoMapper itemstockLineVoMapper = null;

	@Autowired
	private InBoundLineVoMapper inBoundLineVoMapper = null;
	
	@Autowired
	private OutBoundLineVoMapper outBoundLineVoMapper = null;
	
	/**
	 * 插入卷烟库存账面量表
	 * @param inventortime 盘点时间
	 */
	@Override
	public void insertItemstock(ItemstockVo itemstockVo) {
		// TODO Auto-generated method stub
		Date time = itemstockVo.getCreatetime();
		BigDecimal parentid = itemstockVo.getId();
		
		List<ItemstockLineVo> itemstockLineVolist = getStock(time,"");
		for(int i=0;i<itemstockLineVolist.size();i++){
			ItemstockLineVo itemstockLineVo = itemstockLineVolist.get(i);
			itemstockLineVo.setParentid(parentid);
			itemstockLineVoMapper.insertSelective(itemstockLineVo);//插入库存账面量尾数从表
		}
		
		itemstockVoMapper.insertSelective(itemstockVo);//插入库存账面量尾数主表
	}

	/**
	 * 取盘点时间为止的库存账面量,用于前台界面各货主账面量的显示
	 * @param inventortime 盘点的当前时间
	 * @param consignsor 货主
	 * @return
	 */
	@Override
	public List<ItemstockLineVo> selectItemstockLineList(Date inventortime,String consignsor) {
		// TODO Auto-generated method stub
		BigDecimal inboxqty_hj = new BigDecimal("0");
		BigDecimal outboxqty_hj = new BigDecimal("0");
		BigDecimal sumboxqty_hj = new BigDecimal("0");
		BigDecimal initemqty_hj = new BigDecimal("0");
		BigDecimal outitemqty_hj = new BigDecimal("0");
		BigDecimal sumitemqty_hj = new BigDecimal("0");
		List<ItemstockLineVo> itemstockLineVoList = getStock(inventortime,consignsor);
		if(itemstockLineVoList!=null&&itemstockLineVoList.size()>0){
			for(int i=0;i<itemstockLineVoList.size();i++){
				ItemstockLineVo itemstockLineVo = itemstockLineVoList.get(i);
				inboxqty_hj = inboxqty_hj.add(itemstockLineVo.getInboxqty());
				outboxqty_hj = outboxqty_hj.add(itemstockLineVo.getOutboxqty());
				sumboxqty_hj = sumboxqty_hj.add(itemstockLineVo.getBoxqty());
				initemqty_hj = initemqty_hj.add(itemstockLineVo.getInitemqty());
				outitemqty_hj = outitemqty_hj.add(itemstockLineVo.getOutitemqty());
				sumitemqty_hj = sumitemqty_hj.add(itemstockLineVo.getItemqty());
			}
			ItemstockLineVo itemStockVo2 = new ItemstockLineVo();
			itemStockVo2.setCigarettename("合计");
			itemStockVo2.setCigarettecode("");
			itemStockVo2.setInboxqty(inboxqty_hj);
			itemStockVo2.setOutboxqty(outboxqty_hj);
			itemStockVo2.setBoxqty(sumboxqty_hj);
			itemStockVo2.setInitemqty(initemqty_hj);
			itemStockVo2.setOutitemqty(outitemqty_hj);
			itemStockVo2.setItemqty(sumitemqty_hj);
			itemstockLineVoList.add(itemStockVo2);
		}
		return itemstockLineVoList;
	}
	
	/**
	 * 取盘点时间为止的库存账面量,用于盘点时账面量核对
	 * 数据来源于2个部分：1、上次盘点后的账面量尾数 2、出入库
	 * @param inventortime 盘点的当前时间
	 * @param consignsor 货主
	 * @return
	 */
	public List<ItemstockLineVo> getStock(Date inventortime,String consignsor) {
		List<ItemstockLineVo> resultList = new ArrayList<ItemstockLineVo>();
		ItemstockVo itemstockVo=itemstockVoMapper.selectItemstockVoLast();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		Date begintime =null;
		BigDecimal parentId=null;
		if(itemstockVo==null){
			try {
				begintime=sdf.parse("1900-01-01");
				parentId=new BigDecimal(0);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			begintime = itemstockVo.getCreatetime();//查询出入库账面量的开始时间，已上次库存账面量的尾数为准
			parentId=itemstockVo.getId();
		}
		Date endtime = inventortime;//查询出入库账面量的截止时间
		
		//1.取上次盘点后库存账面量的尾数
		ItemstockLineVo itemstockLineVoCond = new ItemstockLineVo();
		itemstockLineVoCond.setParentid(parentId);
		if(consignsor!=null && !consignsor.equals(""))itemstockLineVoCond.setConsignsor(consignsor);
		List<ItemstockLineVo> itemstockLineVoList=itemstockLineVoMapper.selectSumListByParentid(itemstockLineVoCond);
		
		//2.取上次盘点后到本次盘点时间截止的入库和出库账面量
		InBoundVo inBoundVoCond= new InBoundVo();
		inBoundVoCond.setBegintime(begintime);
		inBoundVoCond.setEndtime(endtime);
		inBoundVoCond.setConsignsor(consignsor);
		List<ItemstockLineVo> inOutStockList = getInOutStock(inBoundVoCond);
		
		
		//合并盘点尾数和出入库卷烟数量，即为现在最新的库存账面量
		boolean addAll = inOutStockList.addAll(itemstockLineVoList);//尾数和出入库先简单合并到集合inOutStockList里面
			if(inOutStockList!=null&&inOutStockList.size()>0){
				for(int i=0;i<inOutStockList.size();i++){//重复的货主和品牌的账面量数据需要合并
					ItemstockLineVo itemstockLineVo1 = inOutStockList.get(i);
					String consignsor1 = itemstockLineVo1.getConsignsor()+"";
					String cigarettecode1 = itemstockLineVo1.getCigarettecode();
					for(int j=0;j<inOutStockList.size();j++){
						ItemstockLineVo itemstockLineVo2 = inOutStockList.get(j);
						String consignsor2 = itemstockLineVo2.getConsignsor()+"";
						String cigarettecode2 = itemstockLineVo2.getCigarettecode();
						if(itemstockLineVo1!=itemstockLineVo2&&consignsor1.equals(consignsor2)&&cigarettecode1.equals(cigarettecode2)){
							itemstockLineVo1.setBoxqty(itemstockLineVo1.getBoxqty().add(itemstockLineVo2.getBoxqty()));
							itemstockLineVo1.setItemqty(itemstockLineVo1.getItemqty().add(itemstockLineVo2.getItemqty()));
							itemstockLineVo1.setOutboxqty(itemstockLineVo1.getOutboxqty().add(itemstockLineVo2.getOutboxqty()));
							itemstockLineVo1.setOutitemqty(itemstockLineVo1.getOutitemqty().add(itemstockLineVo2.getOutitemqty()));
							itemstockLineVo1.setInboxqty(itemstockLineVo1.getInboxqty().add(itemstockLineVo2.getInboxqty()));
							itemstockLineVo1.setInitemqty(itemstockLineVo1.getInitemqty().add(itemstockLineVo2.getInitemqty()));
						}
						
					}
					resultList.add(itemstockLineVo1);
				}
			}
		return resultList;
	}
	/**
	 * 取上次盘点的库存账面量的尾数
	 * @return
	 */
	public List<ItemstockLineVo> getStockListLast(){
		ItemstockVo itemstockVo=itemstockVoMapper.selectItemstockVoLast();
		List<ItemstockLineVo> itemstockLineVoList=itemstockLineVoMapper.selectDetailListByParentid(itemstockVo.getId());//selectSumListByParentid
		return itemstockLineVoList;
	}
	/**
	 * 取出入库的库存账面量
	 * 设计原则考虑有入才有出。以InBoundVo实体为主，返回ItemStockVo列表
	 */
	public List<ItemstockLineVo> getInOutStock(InBoundVo inBoundVo) {
		// TODO Auto-generated method stub
		List<ItemstockLineVo> itemStockVoList = new ArrayList<ItemstockLineVo>();
		
		OutBoundVo  outBoundVo = new OutBoundVo();
		if(inBoundVo!=null&&inBoundVo.getConsignsor()!=null){
			outBoundVo.setConsignsor(inBoundVo.getConsignsor());
		}
		if(inBoundVo!=null&&inBoundVo.getBegintime()!=null&&inBoundVo.getEndtime()!=null){
			outBoundVo.setBegintime(inBoundVo.getBegintime());
			outBoundVo.setEndtime(inBoundVo.getEndtime());
		}
		
		List<InBoundLineVo> inBoundLineVoList = this.inBoundLineVoMapper.selectInboundListForStock(inBoundVo);
		List<OutBoundLineVo> outBoundLineVoList = this.outBoundLineVoMapper.selectOutboundListForStock(outBoundVo);
//		BigDecimal inboxqty_hj = new BigDecimal("0");
//		BigDecimal outboxqty_hj = new BigDecimal("0");
//		BigDecimal sumboxqty_hj = new BigDecimal("0");
//		BigDecimal initemqty_hj = new BigDecimal("0");
//		BigDecimal outitemqty_hj = new BigDecimal("0");
//		BigDecimal sumitemqty_hj = new BigDecimal("0");
		if(inBoundLineVoList!=null&&inBoundLineVoList.size()>0){
			for(int i=0;i<inBoundLineVoList.size();i++){
				ItemstockLineVo ItemStockVo = new ItemstockLineVo();
				ItemStockVo.setId(new BigDecimal("2"));//id赋值为2，用于标识计算账面量时几个list合并后的处理
				InBoundLineVo inBoundLineVo = inBoundLineVoList.get(i);
				String cigarettecode_in= inBoundLineVo.getCigarettecode();
				ItemStockVo.setCigarettecode(cigarettecode_in);
				ItemStockVo.setCigarettename(inBoundLineVo.getCigarettename());
			
				//总入库量=实际入库量+缓存区otherqty的数量，其中otherqty单位为条，需要转换为件
				BigDecimal aboxqty = inBoundLineVo.getAboxqty();//实际入库量
				BigDecimal otherqty = inBoundLineVo.getOtherqty();//其它数量(包括缓存区、罚没烟、退货暂存等)单位条
				BigDecimal jtsize = inBoundLineVo.getJtsize();
				BigDecimal inboxqty = aboxqty.add(otherqty.divide(jtsize));//总入库量(件)
				//System.out.println(otherqty+","+aboxqty+","+jtsize+","+otherqty.add(aboxqty.multiply(jtsize)));
				BigDecimal initemqty = otherqty.add(aboxqty.multiply(jtsize));//总入库量(条)
				ItemStockVo.setInboxqty(inboxqty);//总入库量(件)
				ItemStockVo.setInitemqty(initemqty);//总入库量(条)
				
				BigDecimal outboxqty = new BigDecimal("0");
				BigDecimal outitemqty = new BigDecimal("0");
				if(outBoundLineVoList!=null&&outBoundLineVoList.size()>0){
					for(int j=0;j<outBoundLineVoList.size();j++){
						OutBoundLineVo outBoundLineVo = outBoundLineVoList.get(j);
						String cigarettecode_out = outBoundLineVo.getCigarettecode();
						if(cigarettecode_in.equals(cigarettecode_out)){//出库单中有同品牌的，则将出库数量加入库存实体
							outboxqty=outBoundLineVo.getBoxqty();
							outitemqty=outBoundLineVo.getItemqty();
						}
					}
				}
				ItemStockVo.setOutboxqty(outboxqty);
				ItemStockVo.setOutitemqty(outitemqty);
				ItemStockVo.setBoxqty(inboxqty.subtract(outboxqty));
				ItemStockVo.setItemqty(initemqty.subtract(outitemqty));
				ItemStockVo.setConsignsor(inBoundLineVo.getConsignsor());
				itemStockVoList.add(ItemStockVo);
				
//				inboxqty_hj = inboxqty_hj.add(inboxqty);
//				outboxqty_hj = outboxqty_hj.add(outboxqty);
//				sumboxqty_hj = sumboxqty_hj.add(inboxqty.subtract(outboxqty));
//				initemqty_hj = initemqty_hj.add(initemqty);
//				outitemqty_hj = outitemqty_hj.add(outitemqty);
//				sumitemqty_hj = sumitemqty_hj.add(initemqty.subtract(outitemqty));
			}
//			ItemstockLineVo itemStockVo2 = new ItemstockLineVo();
//			itemStockVo2.setCigarettename("合计");
//			itemStockVo2.setCigarettecode("");
//			itemStockVo2.setInboxqty(inboxqty_hj);
//			itemStockVo2.setOutboxqty(outboxqty_hj);
//			itemStockVo2.setBoxqty(sumboxqty_hj);
//			itemStockVo2.setInitemqty(initemqty_hj);
//			itemStockVo2.setOutitemqty(outitemqty_hj);
//			itemStockVo2.setInitemqty(sumitemqty_hj);
//			itemStockVoList.add(itemStockVo2);
		}
		return itemStockVoList;
	}


}
