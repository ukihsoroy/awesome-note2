package org.ko.rest.service.impl;

import org.ko.rest.dao.MenuMapper;
import org.ko.rest.domain.Menu;
import org.ko.rest.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class MenuServiceImpl implements MenuService {

    @Autowired private MenuMapper menuMapper;

    @Override
    public String hello() {
        return "Hello, World!";
    }

    @Override
    public List<Menu> getAll() {
        return menuMapper.getAll();
    }


}
