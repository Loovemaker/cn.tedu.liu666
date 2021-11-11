package com.jt.service;

import com.jt.pojo.ItemCat;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findItemCatList(Integer level);

    Boolean updateStatus(Integer id, Boolean status);
}
