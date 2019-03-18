package com.kcy.system.dao;

import com.kcy.common.base.BaseDaoImpl;
import com.kcy.system.model.FSPerson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PersonMapper extends BaseDaoImpl<FSPerson> {
	
	@Select(value = { "select * from fish_sys_person" })
    public List<FSPerson> findAll();

	@Select(value = { "select * from fish_sys_person where 1 = 1 and id = #{id}" })
	public List<FSPerson> findOneById(long id);
}
