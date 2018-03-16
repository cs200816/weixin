package com.cs.entity.menu;

import com.alibaba.fastjson.JSONObject;
import com.cs.util.CommonUtil;
import com.cs.util.HttpUtils;

public class MenuMain {
	
	public Menu createMenu(){
		ClickButton cbt=new ClickButton();
        cbt.setKey("image");
        cbt.setName("点击按钮");
        cbt.setType("click");
        ViewButton vbt=new ViewButton();
        vbt.setUrl("http://www.baidu.com");
        vbt.setName("查看按钮");
        vbt.setType("view");
        ClickButton c1=new ClickButton();
        c1.setKey("c1");
        c1.setName("c1按钮");
        c1.setType("click");
        ClickButton c2=new ClickButton();
        c2.setKey("c2");
        c2.setName("c2按钮");
        c2.setType("click");
        
        ViewButton v1=new ViewButton();
        v1.setUrl("http://www.baidu.com");
        v1.setName("v1按钮");
        v1.setType("view");
        ViewButton v2=new ViewButton();
        v2.setUrl("http://www.sogou.com");
        v2.setName("v2按钮");
        v2.setType("view");
        
        ParentButton p1=new ParentButton();
        p1.setName("父1");
        p1.setSub_button(new Button[]{cbt,vbt});
        ParentButton p2=new ParentButton();
        p2.setName("父2");
        p2.setSub_button(new Button[]{c1,c2,v1});
        
        Menu menu=new Menu();
        menu.setButton(new Button[]{p1,p2,v2});
		return menu;
	}
	

	public static void main(String[] args) {
		MenuMain m=new MenuMain();
        //这里为请求接口的 url   +号后面的是 token，这里就不做过多对 token 获取的方法解释
        String access_token="7_EnGxE-uRBZkabFR1lpxooF0Msq6ARDXDXojJgX0Q-d8QvuScmqtYtBSr2Uu2hGkG0bfvkYcCOoLYQ8_AlwMUQKYZC6IpwK5AbXlzw4mK5GG0pZIyyE75TsubIawCTZeABATKQ";
        int res = CommonUtil.createMenu(m.createMenu(), access_token);
        if(res==0){
        	System.out.println("创建菜单成功！");
        }else{
        	System.out.println("创建菜单失败！");
        }
	}
}
