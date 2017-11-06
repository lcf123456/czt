package com.ztel.app.service.wms.Impl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztel.app.persist.mybatis.wms.InBoundLineVoMapper;
import com.ztel.app.persist.mybatis.wms.InBoundVoMapper;
import com.ztel.app.persist.mybatis.wms.OutBoundLineVoMapper;
import com.ztel.app.persist.mybatis.wms.OutBoundVoMapper;
import com.ztel.app.service.PubService;
import com.ztel.app.service.sys.OperationlogService;
import com.ztel.app.service.wms.InBoundLineService;
import com.ztel.app.service.wms.InBoundService;
import com.ztel.app.service.wms.ItemService;
import com.ztel.app.service.wms.OutBoundLineService;
import com.ztel.app.service.wms.StorageAreaInOutService;
import com.ztel.app.util.Constant;
import com.ztel.app.vo.sys.UserVo;
import com.ztel.app.vo.wms.InBoundLineVo;
import com.ztel.app.vo.wms.InBoundVo;
import com.ztel.app.vo.wms.ItemVo;
import com.ztel.app.vo.wms.OutBoundLineVo;
import com.ztel.app.vo.wms.OutBoundVo;
import com.ztel.app.vo.wms.StorageAreaInOutVo;
import com.ztel.framework.vo.Pagination;
import com.ztel.webservice.wms.vo.WMSBillscanLineVo;
import com.ztel.webservice.wms.vo.WMSBillscanVo;
@Service
public class InBoundServiceImpl implements InBoundService {

	@Autowired
	private InBoundVoMapper inBoundVoMapper = null;
	
	@Autowired
	private InBoundLineVoMapper inBoundLineVoMapper = null;
	
	@Autowired
	private InBoundLineService inBoundLineService = null;
	
	@Autowired
	private OutBoundVoMapper outBoundVoMapper = null;
	
	@Autowired
	private OutBoundLineVoMapper outBoundLineVoMapper = null;
	
	@Autowired
	private OutBoundLineService outBoundLineService = null;
	
	@Autowired
	private StorageAreaInOutService storageAreaInOutService = null;
	
	@Autowired
	private OperationlogService operationlogService = null;
	
	@Autowired
	private PubService pubService = null;
	
	@Autowired
	private ItemService  itemService = null;
	
	 @Autowired 
	 DataSource ds = null;
	 JdbcTemplate template;
	 
	 private Map<String, String> sortKeyMapping = new HashMap<>();
		
		public InBoundServiceImpl() {
			//TODO 请在这里填入排序的key转换为列名, 防止SQL注入;每个Service业务域用自己的mapping,在BaseCtrl容易重复
//			sortKeyMapping.put(key, value)
			sortKeyMapping.put("createtime", "createtime");
			sortKeyMapping.put("consignsor", "consignsor");
		}
		
	@Override
	public void InsertInBound(InBoundVo vo) {
		initJdbcTemplate();
		// TODO Auto-generated method stub
		BigDecimal id =  template.execute("select S_WMS_INOUTBOUND.nextval from dual",new PreparedStatementCallback() {  
			   public BigDecimal doInPreparedStatement(PreparedStatement pstmt) throws SQLException, DataAccessException {  
				       pstmt.execute();  
			        ResultSet rs = pstmt.getResultSet();  
			        rs.next();  
			        return rs.getBigDecimal(1);  
				    }  
				});  
		vo.setInboundid(id);
		inBoundVoMapper.insertSelective(vo);
	}
	public void initJdbcTemplate()
	{
		template=new JdbcTemplate(ds);
	}
	
	public List<InBoundVo> selectInBoundPageList(Pagination<?> page) {
			page.sortKeyToColumn(sortKeyMapping);
			return this.inBoundVoMapper.selectInBoundPageList(page);
	}

	public List<InBoundVo> selectInBoundList(InBoundVo inBoundVo){
		return this.inBoundVoMapper.selectInBoundList(inBoundVo);
	}

//	@Override
//	public int getIdFromSequence() {
//		// TODO Auto-generated method stub
//		return inBoundVoMapper.getIdFromSequence();
//	}

	@Override
	public void doInsertInBound(InBoundVo vo) {
		// TODO Auto-generated method stub
		inBoundVoMapper.insertSelective(vo);
	}

	@Override
	public void doUpdateInBound(InBoundVo vo) {
		// TODO Auto-generated method stub
		inBoundVoMapper.updateByPrimaryKeySelective(vo);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doConfiscationImp(List<Integer> inBoundIdList) {
		//更新入库单状态
		int inBoundlen=inBoundIdList.size();
		for(int i=0;i<inBoundlen;i++){
			//更新入库单状态
			InBoundVo inBoundVo=new InBoundVo();
			int inBoundId=inBoundIdList.get(i);
			inBoundVo.setInboundid(new BigDecimal(inBoundId));
			inBoundVo.setStatus("30");
			doUpdateInBound(inBoundVo);
			
			//更新入库单明细信息------------------------------------------------------
			//取入库单明细
			InBoundLineVo inBoundLineVo=new InBoundLineVo();
			inBoundLineVo.setInboundid(new BigDecimal(inBoundId));
			List<InBoundLineVo> lineList=inBoundLineService.selectListByCond(inBoundLineVo);
			//遍历入库单明细
			int lineLen=lineList.size();
			for(int j=0;j<lineLen;j++){
				//更新入库单明细的状态和数量
				InBoundLineVo lineVo=new InBoundLineVo();
				lineVo=lineList.get(j);
				lineVo.setStatus("30");
				lineVo.setOtherqty(lineVo.getBoxqty());
				inBoundLineService.updateInBoundLine(lineVo);
				
				//插入一条入散烟区的调拨数据
				StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
				storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
				storageAreaInOutVo.setQty(lineVo.getBoxqty());
				storageAreaInOutVo.setInouttype(new BigDecimal(20));
				storageAreaInOutVo.setCigarettecode(lineVo.getCigarettecode());
				storageAreaInOutVo.setCigarettename(lineVo.getCigarettename());
				storageAreaInOutVo.setStatus(new BigDecimal(20));
				storageAreaInOutVo.setCigarattetype(new BigDecimal(40));
				storageAreaInOutVo.setBarcode(lineVo.getBarcode());
				storageAreaInOutService.doInsert(storageAreaInOutVo);
			}
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doAddInboundAndLine(InBoundVo inBoundVo, InBoundLineVo inBoundLineVo, String addType) {
		// TODO Auto-generated method stub
		String status=inBoundVo.getStatus();
		//第一次保存,需要保存入库单
		if("1".equals(addType)){
			//inBoundVo.setQty(inBoundLineVo.getBoxqty());
			this.doInsertInBound(inBoundVo);
		}else{
			this.doUpdateInBound(inBoundVo);
		}
		
		//当状态是完成的时候插入调拨表
		if("30".equals(status)){
			//设置入散烟区数量
			inBoundLineVo.setOtherqty(inBoundLineVo.getBoxqty());
			//插入调拨表
			StorageAreaInOutVo storageAreaInOutVo=new StorageAreaInOutVo();
			storageAreaInOutVo.setAreaid(new BigDecimal(Constant.storagearea_sy));
			storageAreaInOutVo.setQty(inBoundLineVo.getBoxqty());
			storageAreaInOutVo.setInouttype(new BigDecimal(20));
			storageAreaInOutVo.setCigarettecode(inBoundLineVo.getCigarettecode());
			storageAreaInOutVo.setCigarettename(inBoundLineVo.getCigarettename());
			storageAreaInOutVo.setStatus(new BigDecimal(20));
			storageAreaInOutVo.setBarcode(inBoundLineVo.getBarcode());
			storageAreaInOutVo.setCigarattetype(new BigDecimal(40));
			storageAreaInOutService.doInsert(storageAreaInOutVo);
		}
		
		inBoundLineService.InsertInBoundLine(inBoundLineVo);
	}
	
	/**
	 * 从一号工程接收到的数据插入入库单以及明细表
	 * @param vo
	 * @param lineist
	 * return 1:成功 0：失败
	 */
	@Transactional(rollbackFor=Exception.class)
	public int doInsertInBoundAndLineList(WMSBillscanVo mainVo,List<WMSBillscanLineVo> lineist){
		int result = 1;
		try
		{
			UserVo userVo = new UserVo();
			userVo.setId(1L);
			userVo.setUsername("系统管理员");
			
			boolean hasDone=true;
			//插入之前先检查该数据是否已经入库，如果已经入库，则跳过，否则插入
			InBoundVo inBoundVo0 = new InBoundVo();
			inBoundVo0.setBbUuid(mainVo.getBbuuid());//bbuuid一号工程的唯一标识
			inBoundVo0.setNavicert(mainVo.getBbscanno());//准运证号
			List<InBoundVo> inboundVoList = inBoundVoMapper.selectInBoundList(inBoundVo0);
			if(inboundVoList!=null&&inboundVoList.size()>0){
				hasDone = false;
				operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据", "3、"+mainVo.getBbuuid()+"的数据已经存在！", "");
			}
		if(hasDone){
			//此处添加日志：从一号工程接收数据开始插入入库单及明细----------------------
			operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据", "3、"+mainVo.getBbuuid()+"的数据开始入库", "");
			
			Long id = pubService.getSequence("S_WMS_INOUTBOUND");
			//插入入库单主表
			InBoundVo inBoundVo = new InBoundVo();
			inBoundVo.setNavicert(mainVo.getBbscanno());//准运证
			inBoundVo.setContractno(mainVo.getBbcontactno());//合同号
			inBoundVo.setCreatetime(new Date());//记录时间
			inBoundVo.setSupplier(mainVo.getBbflowname());//供应商
			inBoundVo.setConsignsor(mainVo.getHeadcommercecode());//货主
			inBoundVo.setIntype(new BigDecimal("10"));//入库类型（工业来烟（订货）10、调拨入库 20、退货入库 30、罚没烟 40
			inBoundVo.setStatus("10");//新增
			inBoundVo.setBbUuid(mainVo.getBbuuid());//一号工程接口单据唯一标识号
			inBoundVo.setInboundid(new BigDecimal(id));//id
			String bbtotapnum = mainVo.getBbtotapnum();
			if(bbtotapnum==null||bbtotapnum.equals(""))bbtotapnum="0";
			inBoundVo.setQty(new BigDecimal(bbtotapnum));//数量
			inBoundVoMapper.insertSelective(inBoundVo);
			
			//插入入库单明细表
			if(lineist!=null&&lineist.size()>0){
				for(int i=0;i<lineist.size();i++){
					WMSBillscanLineVo wMSBillscanLineVo = lineist.get(i);
					InBoundLineVo inBoundLineVo = new InBoundLineVo();
					inBoundLineVo.setCigarettename(wMSBillscanLineVo.getBdpcigname());
					String bdbillpnum = wMSBillscanLineVo.getBdbillpnum();//应出/入货量（件）
					//七匹狼(软灰)件码6901028138567  编码规则：前7位是定值，6901028，后6位是它的代码
					String bdpcigcode = wMSBillscanLineVo.getBdpcigcode();//标准件烟卷烟代码(卷烟件码)  接口过来数据：七匹狼(软灰)件码6901028138567 
					String bdbcigcode = wMSBillscanLineVo.getBdbcigcode();//标准件烟卷烟代码(卷烟条码)  接口过来数据：七匹狼(软灰)条码6901028138536
					//处理件码，取卷烟编码
					String barcode = bdpcigcode;
					if(bdpcigcode.trim().length()==13){
						 barcode = bdpcigcode.trim().substring(7);
					}
					String cigaretteCode = getCigaretteCode(barcode);
					inBoundLineVo.setCigarettecode(cigaretteCode);
					inBoundLineVo.setBarcode(barcode);
					String boxqty = wMSBillscanLineVo.getBdbillpnum();
					if(boxqty==null||boxqty.equals(""))boxqty="0";
					inBoundLineVo.setBoxqty(new BigDecimal(boxqty));
					inBoundLineVo.setInboundid(new BigDecimal(id));
					inBoundLineVo.setConsignsor(mainVo.getHeadcommercecode());
					inBoundLineVoMapper.insertSelective(inBoundLineVo);
				}
			}
			//此处添加日志：从一号工程接收数据插入入库单及明细结束----------------------
			operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据", "4、"+mainVo.getBbuuid()+"的数据入库成功", "");
			}
			}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据一号工程过来的件码取卷烟代码，一号工程卷烟编码规则：七匹狼(软灰)件码6901028138567  编码规则：前7位是定值，6901028，后6位是它的代码
	 * @param bdpcigcode
	 * @return
	 */
	private String getCigaretteCode(String barcode)
	{
		String cigaretteCode = "";
			ItemVo itemVo = new ItemVo();
			itemVo.setBigboxBar(barcode);
			List<ItemVo> list = itemService.selectListByCond(itemVo);
			if(list!=null&&list.size()>0){
				ItemVo itemVo1 = list.get(0);
				cigaretteCode = itemVo1.getItemno();
		}
		return cigaretteCode;
	}
<<<<<<< HEAD

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void doDestroyInbound(List<String> inboundidLst) {
		// TODO Auto-generated method stub
		for(int i=0;i<inboundidLst.size();i++){
			InBoundVo inBoundVo=new InBoundVo();
			inBoundVo.setStatus("0");
			inBoundVo.setInboundid(new BigDecimal(inboundidLst.get(i)));
			inBoundVoMapper.updateByPrimaryKeySelective(inBoundVo);
			
			InBoundLineVo vo=new InBoundLineVo();
			vo.setStatus("0");
			vo.setInboundid(new BigDecimal(inboundidLst.get(i)));
			inBoundLineService.updateInBoundLineByInboundId(vo);
		}
	}

	@Override
	public void doUpdateInboundNumById(InBoundVo inBoundVo) {
		// TODO Auto-generated method stub
		inBoundVoMapper.updateInboundNumById(inBoundVo);
=======
	
	/**
	 * 从一号工程接收到的数据插入出库单以及明细表（主要针对商商调剂的出库）
	 * @param vo
	 * @param lineist
	 * return 1:成功 0：失败
	 */
	@Transactional(rollbackFor=Exception.class)
	public int doInsertOutBoundAndLineList(WMSBillscanVo mainVo,List<WMSBillscanLineVo> lineist){
		int result = 1;
		try
		{
			UserVo userVo = new UserVo();
			userVo.setId(1L);
			userVo.setUsername("系统管理员");
			
			boolean hasDone=true;
			//插入之前先检查该数据是否已经进入调拨出库表，如果已经进入，则跳过，否则插入
			OutBoundVo outBoundVo0 = new OutBoundVo();
			outBoundVo0.setRemarks(mainVo.getBbuuid());//bbuuid一号工程的唯一标识
			outBoundVo0.setOutboundtype(new BigDecimal("20"));//调拨出库
			List<OutBoundVo> outBoundVoList = outBoundVoMapper.selectListByCond(outBoundVo0);
			if(outBoundVoList!=null&&outBoundVoList.size()>0){
				hasDone = false;
				operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据调拨出库", "3、"+mainVo.getBbuuid()+"的数据已经存在！", "");
			}
		if(hasDone){
			//此处添加日志：从一号工程接收数据开始插入入库单及明细----------------------
			operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据调拨出库", "3、"+mainVo.getBbuuid()+"的数据开始入库", "");
			
			Long id = pubService.getSequence("S_WMS_OUTBOUND");
			//插入入库单主表
			OutBoundVo outBoundVo = new OutBoundVo();
			outBoundVo.setNavicert(mainVo.getBbscanno());//准运证
			outBoundVo.setContractno(mainVo.getBbcontactno());//合同号
			outBoundVo.setCreatetime(new Date());//记录时间
			//outBoundVo.setConsignsor(mainVo.getBbflowname());//供应商
			outBoundVo.setConsignsor(mainVo.getHeadcommercecode());//货主
			outBoundVo.setOutboundtype(new BigDecimal("20"));//出库类型(10订单出库 20 调拨出库)
			//outBoundVo.setStatus("10");//新增
			outBoundVo.setRemarks(mainVo.getBbuuid());//一号工程接口单据唯一标识号
			outBoundVo.setOutboundid(new BigDecimal(id));//id
			String bbtotapnum = mainVo.getBbtotapnum();
			if(bbtotapnum==null||bbtotapnum.equals(""))bbtotapnum="0";
			outBoundVo.setQty(new BigDecimal(bbtotapnum));//数量
			outBoundVo.setUserid(new BigDecimal(userVo.getId()));
			outBoundVo.setOuttime(new Date());
			outBoundVoMapper.insertSelective(outBoundVo);
			
			//插入调拨出库单明细表
			if(lineist!=null&&lineist.size()>0){
				for(int i=0;i<lineist.size();i++){
					WMSBillscanLineVo wMSBillscanLineVo = lineist.get(i);
					OutBoundLineVo outBoundLineVo1 = new OutBoundLineVo();
					outBoundLineVo1.setCigarettename(wMSBillscanLineVo.getBdpcigname());
					
					//七匹狼(软灰)件码6901028138567  编码规则：前7位是定值，6901028，后6位是它的代码
					String bdpcigcode = wMSBillscanLineVo.getBdpcigcode();//标准件烟卷烟代码(卷烟件码)  接口过来数据：七匹狼(软灰)件码6901028138567 
					String bdbcigcode = wMSBillscanLineVo.getBdbcigcode();//标准件烟卷烟代码(卷烟条码)  接口过来数据：七匹狼(软灰)条码6901028138536
					
					//处理件码，取卷烟编码
					String barcode = bdpcigcode;
					if(bdpcigcode.trim().length()==13){
						 barcode = bdpcigcode.trim().substring(7);
					}
					String cigaretteCode = getCigaretteCode(barcode);
					outBoundLineVo1.setCigarettecode(cigaretteCode);
					//outBoundLineVo1.setBarcode(barcode);
					String boxqty = wMSBillscanLineVo.getBdbillpnum();
					if(boxqty==null||boxqty.equals(""))boxqty="0";
					String bdbillallbnum =wMSBillscanLineVo.getBdbillallbnum();//应出/入货总量（条）
					if(bdbillallbnum==null||bdbillallbnum.equals(""))bdbillallbnum="0";
					
					outBoundLineVo1.setBoxqty(new BigDecimal(boxqty));
					outBoundLineVo1.setOutboundid(new BigDecimal(id));
					outBoundLineVo1.setItemqty(new BigDecimal(bdbillallbnum));//条烟数量-----待处理
					outBoundLineVoMapper.insertSelective(outBoundLineVo1);
				}
			}
			//此处添加日志：从一号工程接收数据插入入库单及明细结束----------------------
			operationlogService.insertLog(userVo, "/BS56/services/WMSBillService?wsdl", "一号工程接口接收数据调拨出库", "4、"+mainVo.getBbuuid()+"的数据入库成功", "");
			}
			}catch(Exception e){
			result = 0;
			e.printStackTrace();
		}
		return result;
>>>>>>> 0cb0e978fea0b0c859e898f6974d9693bf276f6e
	}
}
