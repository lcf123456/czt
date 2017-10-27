package com.ztel.app.service.account.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ztel.app.persist.mybatis.account.TimebydmVoMapper;
import com.ztel.app.service.account.TimebydmVoService;
import com.ztel.app.vo.account.TimebydmVo;
import com.ztel.framework.vo.Pagination;

@Service
public class TimebydmVoServiceImpl implements TimebydmVoService{

	@Autowired
	TimebydmVoMapper timebydmVoMapper = null;
	/**
	 * 根据访配模式取日期
	 * @param date 订单日期或送货日期 
	 * @param type 0:取订单日期     1：取送货日期
	 * @return
	 */
	public String getTimebydm(String date,String type){
		Date dateD = new Date();
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		try{
			
			Date dateParam = sdf.parse(date);
			
			TimebydmVo timebydmVo = new TimebydmVo();
			
			if(type.equals("0")){//取订单日期
				timebydmVo = timebydmVoMapper.selectOrderdateByDDate(date);
				if(timebydmVo!=null && timebydmVo.getOrderdate()!=null)
				dateD = timebydmVo.getOrderdate();
			}
			else if(type.equals("1")){
				timebydmVo = timebydmVoMapper.selectDeliverydateByODate(date);
				if(timebydmVo!=null&&timebydmVo.getDeliverydate()!=null)
				dateD = timebydmVo.getDeliverydate();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return sdf.format(dateD);
	}
	@Override
	public List<TimebydmVo> getTimebydmdatePageList(Pagination<?> page) {
		// TODO Auto-generated method stub
		return timebydmVoMapper.selectTimebydmdatePageList(page);
	}
	@Override
	public int updateTimebydmdate(TimebydmVo timebydmVo) {
		// TODO Auto-generated method stub
		if (timebydmVo!=null&&!"".equals(timebydmVo.getId())) {
			return timebydmVoMapper.updateByPrimaryKeySelective(timebydmVo);
		}
		return 0;
	}
}
