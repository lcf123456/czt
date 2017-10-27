package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.wms.InBoundLineVoMapper;
import com.ztel.app.persist.mybatis.wms.OutBoundLineVoMapper;
import com.ztel.app.service.wms.WmsPubServcie;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.app.vo.wms.ItemStockVo;
import com.ztel.app.vo.wms.OutBoundLineVo;
import com.ztel.app.vo.wms.OutBoundVo;

@Service
public class WmsPubServcieImpl implements WmsPubServcie {

	@Autowired
	private InBoundLineVoMapper inBoundLineVoMapper = null;
	
	@Autowired
	private OutBoundLineVoMapper outBoundLineVoMapper = null;
	
	/**
	 * 取库存账面量
	 * 设计原则考虑有入才有出。以InBoundVo实体为主，返回ItemStockVo列表
	 */
	@Override
	public List<ItemStockVo> getStock(InBoundVo inBoundVo) {
		// TODO Auto-generated method stub
		List<ItemStockVo> itemStockVoList = new ArrayList<ItemStockVo>();
		
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
		BigDecimal inboxqty_hj = new BigDecimal("0");
		BigDecimal outboxqty_hj = new BigDecimal("0");
		BigDecimal sumboxqty_hj = new BigDecimal("0");
		BigDecimal initemqty_hj = new BigDecimal("0");
		BigDecimal outitemqty_hj = new BigDecimal("0");
		BigDecimal sumitemqty_hj = new BigDecimal("0");
		if(inBoundLineVoList!=null&&inBoundLineVoList.size()>0){
			for(int i=0;i<inBoundLineVoList.size();i++){
				ItemStockVo ItemStockVo = new ItemStockVo();
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
				ItemStockVo.setSumboxqty(inboxqty.subtract(outboxqty));
				ItemStockVo.setSumitemqty(initemqty.subtract(outitemqty));
				itemStockVoList.add(ItemStockVo);
				
				inboxqty_hj = inboxqty_hj.add(inboxqty);
				outboxqty_hj = outboxqty_hj.add(outboxqty);
				sumboxqty_hj = sumboxqty_hj.add(inboxqty.subtract(outboxqty));
				initemqty_hj = initemqty_hj.add(initemqty);
				outitemqty_hj = outitemqty_hj.add(outitemqty);
				sumitemqty_hj = sumitemqty_hj.add(initemqty.subtract(outitemqty));
			}
			ItemStockVo itemStockVo2 = new ItemStockVo();
			itemStockVo2.setCigarettename("合计");
			itemStockVo2.setCigarettecode("");
			itemStockVo2.setInboxqty(inboxqty_hj);
			itemStockVo2.setOutboxqty(outboxqty_hj);
			itemStockVo2.setSumboxqty(sumboxqty_hj);
			itemStockVo2.setInitemqty(initemqty_hj);
			itemStockVo2.setOutitemqty(outitemqty_hj);
			itemStockVo2.setSumitemqty(sumitemqty_hj);
			itemStockVoList.add(itemStockVo2);
		}
		return itemStockVoList;
	}

}
