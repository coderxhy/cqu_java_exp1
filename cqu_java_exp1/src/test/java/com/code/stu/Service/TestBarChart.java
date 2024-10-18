package com.code.stu.Service;

import com.code.stu.Service.impl.BarChart;
import org.jfree.chart.ChartPanel;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TestBarChart {
    @Test
    public void DrawBarChart(){
        JFrame j = new JFrame();
        JDialog jd = new JDialog(j, "成绩分布图", true);
        jd.setBounds(50, 50, 800, 600);
        jd.setLayout(new BorderLayout());

        // 创建柱状图
        Map<String, Integer> scoreDistribution=new HashMap<>();
        scoreDistribution.put("100~90",10);
        scoreDistribution.put("90~80",64);
        scoreDistribution.put("80~70",42);
        BarChart barChart = new BarChart(scoreDistribution);
        ChartPanel chartPanel = barChart.getPanel();

        // 将图表面板添加到对话框
        jd.add(chartPanel, BorderLayout.CENTER);

        // 显示对话框
        jd.setVisible(true);
    }
}
