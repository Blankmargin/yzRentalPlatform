package com.fhh.domain;

import com.fhh.pojo.Item;

import java.io.Serializable;

public class BoxItem extends Item implements Serializable{
    public BoxItem(Item item) {
        this.setId(item.getId());
        this.setTitle(item.getTitle());
        this.setSellPoint(item.getSellPoint());
        this.setPrice(item.getPrice());
        this.setNum(item.getNum());
        this.setCid(item.getCid());
        this.setImage(item.getImage());
        this.setStatus(item.getStatus());
        this.setCreatetime(item.getCreatetime());
        this.setUpdatetime(item.getUpdatetime());
    }

    public String[] getImages(){
        String image=this.getImage();
        if(image!=null&&!"".equals(image)){
            String[] strings = image.split(",");
            return strings;
        }
        return null;
    }
}
