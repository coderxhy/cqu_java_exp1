package com.code.stu.Service.impl;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;
import java.awt.*;
import java.util.Map;

public class BarChart {
    //1-写一个ChartPanel变量
    ChartPanel jframe;

    //2-BarChart的无参数的构造方法
    public BarChart(Map<String, Integer> scoreDistribution) {
        DefaultCategoryDataset data=(DefaultCategoryDataset) getDataSet(scoreDistribution);
        JFreeChart chart = ChartFactory.createBarChart(
                "成绩分布图",  // 图表标题
                "成绩分布",   // 目录轴的显示标签
                "数量",       // 数值轴的显示标签
                data,
                PlotOrientation.VERTICAL,  // 图表方向 水平 垂直
                true,  // 是否显示图例(对于简单的图表建议显示图例)
                false, // 是否生成工具
                false  // 是否生成网址链接
        );
        //字体设置
        //获得图表区域对象
        CategoryPlot plot=chart.getCategoryPlot();
        //水平底部列表
        CategoryAxis domain =plot.getDomainAxis();
        //垂直标题字体设置
        domain.setTickLabelFont(new Font("黑体", Font.BOLD, 16));
        //水平底部标题设置
        domain.setLabelFont(new Font("黑体", Font.BOLD, 20));
        //获取柱状体
        ValueAxis rangeAxis=plot.getRangeAxis();
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 16));
        //设置lengend字体
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 16));
        chart.getTitle().setFont(new Font("黑体", Font.BOLD, 16));
        //初始化Jframe
        jframe=new ChartPanel(chart);


    }

    //3-图表数据设置
    public static CategoryDataset getDataSet(Map<String, Integer> scoreDistribution) {
        DefaultCategoryDataset data=new DefaultCategoryDataset();
        //设置数据
        for (Map.Entry<String, Integer> entry : scoreDistribution.entrySet()) {
            data.addValue(entry.getValue(), "成绩分布", entry.getKey());
        }
        return data;
    }

    //4-返回一个ChartPanel
    public ChartPanel getPanel() {
        return jframe;
    }

}
