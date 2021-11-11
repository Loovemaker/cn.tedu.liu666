package com.jt.service;

import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findItemCatList(Integer level);

    Boolean updateStatus(Integer id, Boolean status);

    Boolean saveItemCat(ItemCat itemCat);

    Boolean updateItemCat(ItemCat itemCat);

    Boolean deleteItemCat(ItemCat itemCat);
}
