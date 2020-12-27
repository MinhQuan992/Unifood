//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mvc.controller;

import com.mvc.dao.DependDao;
import com.mvc.dao.GroupItemDao;
import com.mvc.dao.ItemDao;
import com.mvc.entities.AnkemEntity;
import com.mvc.entities.ListItemEntity;
import com.mvc.entities.NhomsanphamEntity;
import com.mvc.entities.SanphamEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        name = "ItemController"
)
public class ItemController extends HttpServlet {
    public ItemController() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("This servlet: " + this.getServletName() + " is called");
        HttpSession session = request.getSession();
        String itemCode = request.getParameter("ItemCode");
        System.out.println(itemCode);

        ItemDao itemDao = new ItemDao();
        GroupItemDao groupItemDao = new GroupItemDao();
        SanphamEntity itemBean = itemDao.GetItemData(itemCode);
        System.out.println("Item was completely loaded" + itemBean.getTenSanPham());

        DependDao denpendDao = new DependDao();
        List<AnkemEntity> list = denpendDao.getDependItemData(itemBean.getMaSanPham());
        System.out.println(list.size());

        NhomsanphamEntity groupItem = groupItemDao.getGroupItemData(Short.parseShort("10"));
        System.out.println("Group Item was loaded completely: " + groupItem.getTenNhom());
        List<SanphamEntity> listItem1 = itemDao.getListDependenceItems(itemBean, groupItem);
        System.out.println(listItem1.size());
        ListItemEntity listDepend1 = new ListItemEntity(groupItem.getTenNhom(), itemBean, groupItem);
        listDepend1.setItemList(listItem1);

        groupItem = groupItemDao.getGroupItemData(Short.parseShort("11"));
        List<SanphamEntity> listItem2 = itemDao.getListDependenceItems(itemBean, groupItem);
        System.out.println(listItem2.size());
        ListItemEntity listDepend2 = new ListItemEntity(groupItem.getTenNhom(), itemBean, groupItem);
        listDepend2.setItemList(listItem2);

        groupItem = groupItemDao.getGroupItemData(Short.parseShort("12"));
        List<SanphamEntity> listItem3 = itemDao.getListDependenceItems(itemBean, groupItem);
        System.out.println(listItem3.size());
        ListItemEntity listDepend3 = new ListItemEntity(groupItem.getTenNhom(), itemBean, groupItem);
        listDepend3.setItemList(listItem3);

        request.setAttribute("Item", itemBean);
        request.setAttribute("ListDependenceItems1", listDepend1);
        request.setAttribute("ListDependenceItems2", listDepend2);
        request.setAttribute("ListDependenceItems3", listDepend3);

        /*session.setAttribute("Item", itemBean);
        session.setAttribute("ListDependenceItems1", listDepend1);
        session.setAttribute("ListDependenceItems2", listDepend2);
        session.setAttribute("ListDependenceItems3", listDepend3);
        session.setAttribute(itemBean.getMaSanPham(),1);*/

        HashMap<String,Integer> map = (HashMap<String,Integer>) request.getAttribute("ProductQuantity");
        if (map==null) map = new HashMap<String,Integer>();
        request.setAttribute(itemBean.getMaSanPham(),1);
        map.put(itemBean.getMaSanPham(),1);
        for (SanphamEntity sp: listItem1)
        {
            map.putIfAbsent(sp.getMaSanPham(), 1);
        }
        for (SanphamEntity sp: listItem2)
        {
            map.putIfAbsent(sp.getMaSanPham(), 1);
        }
        for (SanphamEntity sp: listItem3)
        {
            map.putIfAbsent(sp.getMaSanPham(), 1);
        }
        request.setAttribute("ProductQuantity",map);
        String url = "/Page/ProductDetail.jsp";
        System.out.println("Serlet forward to : " + url);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
