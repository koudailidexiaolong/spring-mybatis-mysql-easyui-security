package com.julongtech.system.service;

import java.util.List;

import com.julongtech.system.action.vo.SystemRoleButtonMappedVO;
import com.julongtech.system.service.dto.SystemRoleButtonMappedDTO;

/**
 * 角色对应的菜单按钮类
 * @author julong
 * @date 2022年8月21日 下午1:26:41
 * @desc 
 */
public interface SystemRoleButtonMappedService {
	
    /**
     * 根据编号删除角色按钮菜单关系
     * @param mappedId
     * @return
     * @throws Exception
     * @author julong
     * @date 2022年8月21日 下午1:26:21
     * @desc
     */
    public abstract int deleteSystemRoleButtonMapped(Integer mappedId) throws Exception;
    
    /**
     * 根据角色删除按钮菜单对应关系
     * @param roleId
     * @return
     * @throws Exception
     * @author julong
     * @date 2022年8月21日 下午1:26:03
     * @desc
     */
    public abstract int deleteSystemRoleButtonMappedByRoleId(Integer roleId) throws Exception;

    /**
     * 新增角色按钮关系
     * @param systemRoleButtonMappedVO
     * @return
     * @throws Exception
     * @author julong
     * @date 2022年8月21日 下午1:31:24
     * @desc
     */
    public abstract int insertSystemRoleButtonMapped(SystemRoleButtonMappedVO systemRoleButtonMappedVO) throws Exception;

    /**
     * 根据编号查询角色按钮关系
     * @param mappedId
     * @return
     * @throws Exception
     * @author julong
     * @date 2022年8月21日 下午3:19:57
     * @desc
     */
    public abstract SystemRoleButtonMappedDTO getSystemRoleButtonMapped(Integer mappedId) throws Exception;

    /**
     * 根据条件查询角色按钮关系列表
     * @param systemRoleButtonMappedVO
     * @return
     * @throws Exception
     * @author julong
     * @date 2022年8月21日 下午3:20:11
     * @desc
     */
    public abstract List<SystemRoleButtonMappedDTO> selectSystemRoleButtonMappedList(SystemRoleButtonMappedVO systemRoleButtonMappedVO) throws Exception;

    /**
     * 更新角色按钮关系
     * @param systemRoleButtonMappedVO
     * @return
     * @throws Exception
     * @author julong
     * @date 2022年8月21日 下午1:31:44
     * @desc
     */
    public abstract int updateSystemRoleButtonMapped(SystemRoleButtonMappedVO systemRoleButtonMappedVO) throws Exception;

}