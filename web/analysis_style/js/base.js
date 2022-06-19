//==========不同算法结果比较折线图 开始==============
var five_line = echarts.init(document.getElementById("five-line"), 'infographic');
option = {
    title: {
        text: ''
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        top: 0,
        right: 0,
        textStyle: {
            color: 'white'
        },
        itemWidth: 10,
        itemHeight: 10,
        orient: 'vertical',
        data: [
            { name: '活跃度算法', icon: 'circle' },
            { name: '度中心法', icon: 'circle' },
            { name: '聚集系数算法', icon: 'circle' },
            { name: '活跃度-聚集系数算法', icon: 'circle' },
            { name: '随机算法', icon: 'circle' },
            { name: '介数中心性算法', icon: 'circle' }
        ]
    },
    grid: {
        left: '3%',
        right: '16%',
        bottom: '5%',
        top: '5%',
        containLabel: true
    },
    xAxis: {
        type: 'category',
        name: '移除节点比例',
        nameLocation: 'middle',
        nameTextStyle: {
            fontSize: 10,
            padding: 3,
            color:"white",
        },
        boundaryGap: false,
        axisTick: { show: false },
        axisLabel: {
            textStyle: {
                color: "white", //刻度颜色
                fontSize: 8  //刻度大小
            }
        },
        axisLine: {
            show: true,
            lineStyle: {
                color: '#0B3148',
                width: 1,
                type: 'solid'
            }
        },
        data: [1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5]
    },
    yAxis: {
        type: 'value',
        name: "平均共识达成时间",
        nameLocation: 'middle',
        nameTextStyle: {
            fontSize: 10,
            padding: 10,
            color:"white",
        },
        axisTick: { show: false },
        axisLabel: {
            textStyle: {
                color: "white", //刻度颜色
                fontSize: 8  //刻度大小
            }
        },
        axisLine: {
            show: true,
            lineStyle: {
                color: '#0B3148',
                width: 1,
                type: 'solid'
            }
        },
        splitLine: {
            show: false
        }
    },
    series: [
        {
            name: '活跃度算法',
            type: 'line',
            data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
            name: '度中心法',
            type: 'line',
            data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
            name: '聚集系数算法',
            type: 'line',
            data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
            name: '活跃度-聚集系数算法',
            type: 'line',
            data: [120, 244, 321, 174, 390, 230, 310]
        },
        {
            name: '随机算法',
            type: 'line',
            data: [123, 144, 221, 174, 240, 230, 320]
        },
        {
            name: '介数中心性算法',
            type: 'line',
            data: [125, 149, 121, 284, 260, 290, 301]
        }

    ]
}
five_line.setOption(option);
//========== 结束 ==============

//========= 度分布开始 =======================
var dufenbu_data = echarts.init(document.getElementById("dufenbu_data"), 'infographic');
option = {
    color: ['#FADB71'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        containLabel: true,
        x: 30,
        y: 10,
        x2: 15,
        y2: 20
    },
    xAxis: [
        {
            type: 'category',
            name: '度 数',
            nameLocation: 'middle',
            nameTextStyle: {
                fontSize: 12,
                padding: 10,
                color:"white",
            },
            axisTick: {
                alignWithLabel: true
            },
            axisLabel: {
                textStyle: {
                    color: "white", //刻度颜色
                    fontSize: 8  //刻度大小
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#0B3148',
                    width: 1,
                    type: 'solid'
                }
            },
            data: [10, 30, 50, 70, 90],
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: '节 点 数 量',
            nameLocation: 'middle',
            nameTextStyle: {
                fontSize: 12,
                padding: 20,
                color:"white",
            },
            axisTick: { show: false },
            axisLabel: {
                textStyle: {
                    color: "white", //刻度颜色
                    fontSize: 8  //刻度大小
                }
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: '#0B3148',
                    width: 1,
                    type: 'solid'
                }
            },            
        }
    ],
    series: [
        {
            name: '度数分布',
            type: 'bar',
            barWidth: '100%',
            data: [10, 52, 200, 334, 390],
            
        }
    ]
};

dufenbu_data.setOption(option);
//========= 度分布结束 =======================


//时间选择器








