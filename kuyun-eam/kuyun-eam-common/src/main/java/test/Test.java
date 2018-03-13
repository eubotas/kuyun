package test;

import com.google.gson.Gson;
import com.kuyun.eam.pojo.Position;
import com.kuyun.eam.pojo.Positions;

import javax.swing.text.html.HTML;
import java.math.BigDecimal;

/**
 * Created by user on 2018-03-13.
 */
public class Test {

    public static void main(String[] args) {
        Positions pList = new Positions();
        Position p = new Position();
        p.setEquipmentId("111");
        p.setLeft(BigDecimal.valueOf(100));
        p.setTop(BigDecimal.valueOf(200));

        pList.addPosition(p);

        p.setEquipmentId("222");
        p.setLeft(BigDecimal.valueOf(100));
        p.setTop(BigDecimal.valueOf(200));
        pList.addPosition(p);

        p.setEquipmentId("333");
        p.setLeft(BigDecimal.valueOf(100));
        p.setTop(BigDecimal.valueOf(200));
        pList.addPosition(p);

        Gson gson = new Gson();
        System.out.println(gson.toJson(pList));





    }
}
