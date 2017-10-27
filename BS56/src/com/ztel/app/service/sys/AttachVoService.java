package com.ztel.app.service.sys;

import java.util.List;
import java.util.Map;

import com.ztel.app.vo.sys.AttachVo;
import com.ztel.app.vo.sys.UserVo;


/**
 * 
 * @author lcf
 *
 */
public interface AttachVoService {
	
	public void insertAttachVO(AttachVo entity);
	
	public void deleteAttachVo(int id);
}
