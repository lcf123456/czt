package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.StorageAreaInOutVoMapper;
import com.ztel.app.service.PubService;
import com.ztel.app.service.account.OperateVoService;
import com.ztel.app.service.wms.InBoundLineService;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.service.wms.InventoryLineVoService;
import com.ztel.app.service.wms.ShipOrderLineService;
import com.ztel.app.service.wms.ShipOrderService;
import com.ztel.app.service.wms.StorageAreaInOutService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.account.OperateVo;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.app.vo.wms.InventoryLineVo;
import com.ztel.app.vo.wms.ShipOrderLineVo;
import com.ztel.app.vo.wms.ShipOrderVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
@Service
public class StorageAreaInOutServiceImpl implements StorageAreaInOutService {

	@Autowired
	private StorageAreaInOutVoMapper storageAreaInOutVoMapper = null;
	
	@Autowired
	private InBoundService inBoundService = null;
	
	@Autowired
	private InBoundLineService inBoundLineService = null;
	
	@Autowired
	private ShipOrderService shipOrderService = null;
	
	@Autowired
	private ShipOrderLineService shipOrderLineService = null;
	
	@Autowired
	private InventoryLineVoService inventoryLineVoService = null;
	
	@Autowired
	private PubService pubService = null;
	
	@Autowired
	private OperateVoService operateVoService = null;
	 
	private Map<String, String> sortKeyMapping = new HashMap<>();
		
	public StorageAreaInOutServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
			//sortKeyMapping.put(key, value)
	}

	@Override
	public int doInsert(StorageAreaInOutVo storageAreaInOutVo) {
		// TODO Auto-generated method stub
		return storageAreaInOutVoMapper.insertSelective(storageAreaInOutVo);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doRefund(List<Integer> operateIdList,List<String> ordernoList) {
		// TODO Auto-generated method stub
		//更新t_account_operate数据
		int idlen=operateIdList.size();
		for(int i=0;i<idlen;i++){
			OperateVo operateVo=new OperateVo();
			operateVo.setId(new BigDecimal(operateIdList.get(i)));
			operateVo.setStatus("40");
			operateVoService.doOperateUpdate(operateVo);
		}
		//取订单明细
		int orderLen=ordernoList.size();
		//System.out.println("orderLen=="+orderLen);
		int lineLen=0;
		for(int i=0;i<orderLen;i++){
			
			String orderNo=ordernoList.get(i);
			//更新订单主表状态位
			ShipOrderVo shipOrderVo=new ShipOrderVo();
			shipOrderVo.setOrderno(orderNo);
			shipOrderVo.setOrderstatus(new BigDecimal(0));
			shipOrderService.doUpdateShipOrder(shipOrderVo);
			
			//取订单明细
			List<ShipOrderLineVo>lineLst=shipOrderLineService.getShipOrderLineByOrderNo(orderNo);
			
			InBoundVo inBoundVo=new InBoundVo();
			//取入库单的id
			//int inBoundId=inBoundService.getIdFromSequence();
			long inBoundId=pubService.getSequence("S_WMS_INOUTBOUND");
			//货主
			String org_code=shipOrderService.getShipOrderByOrderNo(orderNo).getOrgCode();
			inBoundVo.setInboundid(new BigDecimal(inBoundId));
			inBoundVo.setIntype(new BigDecimal(30));
			inBoundVo.setConsignsor(org_code);
			inBoundVo.setStatus("30");
			
			//数量合计
			BigDecimal totalqty=new BigDecimal(0);
			//遍历订单明细
			lineLen=lineLst.size();
			//System.out.println("lineLen=="+lineLen);
			for(int j=0;j<lineLen;j++){
				ShipOrderLineVo shipOrderLineVo=new ShipOrderLineVo();
				shipOrderLineVo=lineLst.get(j);
				//存取数据  累加出总数存入qty中，入入库单
				//System.out.println("明细=="+shipOrderLineVo.getItemId()+"--"+shipOrderLineVo.getItemname()+"--"+shipOrderLineVo.getQty());
				//插入入库单明细
				InBoundLineVo inBoundLineVo=new InBoundLineVo();
				inBoundLineVo.setInboundid(new BigDecimal(inBoundId));
				//inBoundLineVo.setAboxqty(shipOrderLineVo.getQty());
				inBoundLineVo.setBoxqty(shipOrderLineVo.getQty());
				inBoundLineVo.setBarcode(shipOrderLineVo.getBarcode());
				inBoundLineVo.setOtherqty(shipOrderLineVo.getQty());
				totalqty=totalqty.add(shipOrderLineVo.getQty());
				inBoundLineVo.setCigarettecode(shipOrderLineVo.getItemId());
				inBoundLineVo.setCigarettename(shipOrderLineVo.getItemname());
				inBoundLineService.InsertInBoundLine(inBoundLineVo);
				
				//插入调拨表
				StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
				storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
				storageAreaInOutVo.setQty(shipOrderLineVo.getQty());
				storageAreaInOutVo.setInouttype(new BigDecimal(20));
				storageAreaInOutVo.setCigarettecode(shipOrderLineVo.getItemId());
				storageAreaInOutVo.setCigarettename(shipOrderLineVo.getItemname());
				storageAreaInOutVo.setStatus(new BigDecimal(20));
				storageAreaInOutVo.setBarcode(shipOrderLineVo.getBarcode());
				storageAreaInOutVo.setCigarattetype(new BigDecimal(30));
				doInsert(storageAreaInOutVo);
				
				//更新shipOrderLineVo的状态标志
				shipOrderLineVo.setOrderstatus(new BigDecimal(0));
				shipOrderLineService.doUpdateShipOrderLine(shipOrderLineVo);
			}
			//插入入库单头
			inBoundVo.setQty(totalqty);
			inBoundService.doInsertInBound(inBoundVo);
		}
	}

	@Override
	public List<StorageAreaInOutVo> getLastStorageAreaInOutInfoByCond(StorageAreaInOutVo storageAreaInOutVo) {
		// TODO Auto-generated method stub
		return storageAreaInOutVoMapper.selectLastStorageAreaInOutInfoByCond(storageAreaInOutVo);
	}

	@Override
	public List<InventoryLineVo> getInOutInfoSummaryByCond(StorageAreaInOutVo storageAreaInOutVo,
			InventoryLineVo inventoryLineVo) {
		// TODO Auto-generated method stub
		//取调拨数据
		List<StorageAreaInOutVo> inOutList=getLastStorageAreaInOutInfoByCond(storageAreaInOutVo);
		//取最近一次盘点尾数
		List<InventoryLineVo> inventoryLineList=inventoryLineVoService.getLastInventoryInfoByCond(inventoryLineVo);
		
		HashMap<String, InventoryLineVo>map=new HashMap<>();
		int inOutLen=inOutList.size();
		int lineLen=inventoryLineList.size();
		//遍历盘点尾数到map中，然后遍历调拨数据，匹配烟草品牌进行数据加减处理
		for(int i=0;i<lineLen;i++){
			InventoryLineVo lineVo=inventoryLineList.get(i);
			map.put(lineVo.getCigarettecode(), lineVo);
		}
		//若上期盘点尾数包含调拨汇总中的品牌，则已上期尾数为基准进行加减；
		//若上期盘点尾数不包含调拨汇总中的品牌，则直接将凋敝汇总数据加入到Map中
		String cigaretteCode="";
		BigDecimal lineQty=new BigDecimal(0);
		BigDecimal inoutQty=new BigDecimal(0);
		
		for(int i=0;i<inOutLen;i++){
			StorageAreaInOutVo areaInOutVo=inOutList.get(i);
			cigaretteCode=areaInOutVo.getCigarettecode();
			InventoryLineVo vo=new InventoryLineVo();
			//若上期盘点尾数包含调拨汇总中的品牌，则已上期尾数为基准进行加减；
			if(map.containsKey(cigaretteCode)){
				vo=map.get(cigaretteCode);
				lineQty=vo.getItemqty();
				inoutQty=areaInOutVo.getQty();
				vo.setInoutqty(inoutQty);
				vo.setTotalqty(lineQty.add(inoutQty));
				//map.put(cigaretteCode, vo);
			}else{
				//若上期盘点尾数不包含调拨汇总中的品牌，则直接将调拨汇总数据加入到Map中
				vo.setCigarettecode(cigaretteCode);
				vo.setCigarettename(areaInOutVo.getCigarettename());
				vo.setItemqty(new BigDecimal(0));
				vo.setInoutqty(areaInOutVo.getQty());
				vo.setTotalqty(areaInOutVo.getQty());
				//map.put(cigaretteCode, vo);
			}
			map.put(cigaretteCode, vo);
		}
		
		//List<InventoryLineVo> lineList=(List<InventoryLineVo>) map.values();
		List<InventoryLineVo> lineList=new ArrayList<>();
		Object[] keys=map.keySet().toArray();
		for(int i=0;i<keys.length;i++){
			lineList.add(map.get(keys[i].toString()));
		}
		return lineList;
	}

	@Override
	public List<StorageAreaInOutVo> getLastCellInOutInfoByCond(StorageAreaInOutVo storageAreaInOutVo) {
		// TODO Auto-generated method stub
		return storageAreaInOutVoMapper.selectLastCellInOutInfoByCond(storageAreaInOutVo);
	}

}
