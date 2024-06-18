package org.ko.mybatis.mapper;

import org.ko.mybatis.domain.Country;

import java.util.List;

public interface CountryMapper extends tk.mybatis.mapper.common.Mapper<Country> {

    List<Country> findAll();
}
