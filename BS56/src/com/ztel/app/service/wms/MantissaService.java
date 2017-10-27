package com.ztel.app.service.wms;

import com.ztel.app.vo.wms.MantissaVo;

public interface MantissaService {
	
	public void insertRecord(MantissaVo vo);
    public MantissaVo getMantissa(MantissaVo vo);
}
