<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 67675
  Date: 2017/11/9
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <%@include file="../include/css.jsp" %>

    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@include file="../include/header.jsp" %>

    <!-- =============================================== -->

    <jsp:include page="../include/left-meau.jsp">
        <jsp:param name="menu" value="charts" />
    </jsp:include>

    <!-- =============================================== -->
        <div class="content-wrapper">
            <section class="content">
                <div class="box">
                     <div id="customerTime" style="width: 100%;height: 400px;"></div>
                </div>
                <div class="box">
                    <div id="funnelChart" style="width: 100%;height: 600px;"></div>
                </div>
            </section>
        </div>
    <%@include file="../include/footer.jsp" %>

</div>
<%@include file="../include/js.jsp" %>
<script src="/static/plugins/echarts/echarts.min.js" ></script>
<script>
    $(function () {

        $(window).resize(function () {
            history.go(0);
        })
        /*app.title = '力引导布局';

myChart.showLoading();
$.get('data/asset/data/les-miserables.gexf', function (xml) {
    myChart.hideLoading();

    var graph = echarts.dataTool.gexf.parse(xml);
    var categories = [];
    for (var i = 0; i < 9; i++) {
        categories[i] = {
            name: '类目' + i
        };
    }
    graph.nodes.forEach(function (node) {
        node.itemStyle = null;
        node.symbolSize = 10;
        node.value = node.symbolSize;
        node.category = node.attributes.modularity_class;
        // Use random x, y
        node.x = node.y = null;
        node.draggable = true;
    });
    option = {
        title: {
            text: 'Les Miserables',
            subtext: 'Default layout',
            top: 'bottom',
            left: 'right'
        },
        tooltip: {},
        legend: [{
            // selectedMode: 'single',
            data: categories.map(function (a) {
                return a.name;
            })
        }],
        animation: false,
        series : [
            {
                name: 'Les Miserables',
                type: 'graph',
                layout: 'force',
                data: graph.nodes,
                links: graph.links,
                categories: categories,
                roam: true,
                label: {
                    normal: {
                        position: 'right'
                    }
                },
                force: {
                    repulsion: 100
                }
            }
        ]
    };

    myChart.setOption(option);
}, 'xml');*/

        var customerTimeChart = echarts.init($("#customerTime")[0]);
        var funnelChart = echarts.init($("#funnelChart")[0]);

        var customerTimeOption = {
            title : {
                text : "每月客户增加数量",
                left : "center"
            },
            tooltip : {},
            legend : {
                data : ["数量"]
            },
            xAxis : {
                data : []
            },
            yAxis : {},

            series : [{
                name : "数量",
                type : "line",
                data : []
            }]
        };
        customerTimeChart.setOption(customerTimeOption);

        $.get("/chart/customerTime").done(function (json) {
            if(json.state == 'success') {

                var xDatas = [];
                var yData = [];
                var data = json.data;

                for(var i = 0; i < data.length; i ++) {
                    var newData = data[i];
                    xDatas.push(newData.month + '月');
                    yData.push(newData.count);
                }

                customerTimeChart.setOption({
                    xAxis : {
                        data : xDatas
                    },
                    series : [{
                        data :yData
                    }]
                });

            } else {

                layer.msg("获取数据失败");
            }
        }).error(function () {
            
        });
/*======================================漏斗图 ======================================================================*/
        var funnerlOption = {
            title: {
                text: '销售',
                subtext: '漏斗图'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            legend: {
                data: ['访问','意向','报价','成交','暂时搁置']
            },
            calculable: true,
            series: [
                {
                    name:'漏斗图',
                    type:'funnel',
                    left: '10%',
                    top: 60,
                    //x2: 80,
                    bottom: 60,
                    width: '80%',
                    // height: {totalHeight} - y - y2,
                    min: 0,
                    max: 5,
                    minSize: '0%',
                    maxSize: '100%',
                    sort: 'descending',
                    gap: 2,
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        },
                        emphasis: {
                            textStyle: {
                                fontSize: 20
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            length: 10,
                            lineStyle: {
                                width: 1,
                                type: 'solid'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            borderColor: '#fff',
                            borderWidth: 1
                        }
                    },
                    data: []
                }
            ]
        }
        funnelChart.setOption(funnerlOption)

        $.get("/chart/funnel").done(function (json) {

            if(json.state == 'success') {
                var newData = json.data;
                funnelChart.setOption({
                    series: {
                        data :newData
                    }
                })

            } else {
                layer.msg("数据加载失败");
            }

        }).error(function () {


        });

        funnelChart.setOption(funnerlOption)

    });
</script>
</body>
</html>
