package test;

import com.google.gson.Gson;
import com.kuyun.eam.pojo.Position;
import com.kuyun.eam.pojo.Positions;
import com.kuyun.eam.pojo.map.*;

import javax.swing.text.html.HTML;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-13.
 */
public class Test {

    public static void main(String[] args) {
//        Positions pList = new Positions();
//        Position p = new Position();
//        p.setEquipmentId("111");
//        p.setLeft(BigDecimal.valueOf(100));
//        p.setTop(BigDecimal.valueOf(200));
//
//        pList.addPosition(p);
//
//        p.setEquipmentId("222");
//        p.setLeft(BigDecimal.valueOf(100));
//        p.setTop(BigDecimal.valueOf(200));
//        pList.addPosition(p);
//
//        p.setEquipmentId("333");
//        p.setLeft(BigDecimal.valueOf(100));
//        p.setTop(BigDecimal.valueOf(200));
//        pList.addPosition(p);

        Gson gson = new Gson();
//        System.out.println(gson.toJson(pList));



        MapData data = new MapData();
        List<City> cities = new ArrayList<>();
        City city = new City();
        city.setName("南通市");
        city.setSymboleSize(25);
        List<String> values = new ArrayList<>(2);
        values.add("121");
        values.add("32");

        city.setValue(values);
        ItemStyle itemStyle = new ItemStyle();
        Normal normal = new Normal();
        normal.setColor("#49bcf8");
        itemStyle.setNormal(normal);
        city.setItemStyle(itemStyle);
        cities.add(city);



        city = new City();
        city.setName("上海市");
        city.setSymboleSize(21);
        values = new ArrayList<>(2);
        values.add("100");
        values.add("321");

        city.setValue(values);
        itemStyle = new ItemStyle();
        normal = new Normal();
        normal.setColor("#49bcf8");
        itemStyle.setNormal(normal);
        city.setItemStyle(itemStyle);

        cities.add(city);

        data.setCitys(cities);

        List<MoveLine> moveLines = new ArrayList<>();
        MoveLine moveLine = new MoveLine();
        moveLine.setFromName("南通市");
        moveLine.setToName("上海市");
        List<String> from = new ArrayList<>();
        from.add("121");
        from.add("32");

        List<String> to = new ArrayList<>();
        to.add("100");
        to.add("321");
        List<List<String>> coords = new ArrayList<>();
        coords.add(from);
        coords.add(to);

        moveLine.setCoords(coords);
        moveLines.add(moveLine);

        data.setMoveLines(moveLines);



        System.out.println(gson.toJson(data));
    }
}
