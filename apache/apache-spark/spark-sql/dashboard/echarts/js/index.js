const BASE_URL = "/rest/"

$(function(){

    //访问
    findDayAccessTopNStat()
    findDayCityTopNStat()
    findDayTrafficsTopNStat()

})

/**
 * 获取课程TopN统计
 */
function findDayAccessTopNStat () {
    // 基于准备好的dom，初始化echarts实例
    var dayAccessEcharts = echarts.init(document.getElementById('day_stat'));
    var dayAccessOption = {
        backgroundColor: '#2c343c',
        title: {
            text: '课程访问次数 TopN',
            left: 'center',
            top: 20,
            textStyle: {
                color: '#ccc'
            }
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        visualMap: {
            show: false,
            min: 0,
            max: 50,
            inRange: {
                colorLightness: [0, 1]
            }
        },
        series : [
            {
                name:'访问来源',
                type:'pie',
                radius : '55%',
                center: ['50%', '50%'],
                roseType: 'radius',
                label: {
                    normal: {
                        textStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        lineStyle: {
                            color: 'rgba(255, 255, 255, 0.3)'
                        },
                        smooth: 0.2,
                        length: 10,
                        length2: 20
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#c23531',
                        shadowBlur: 200,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                },
                animationType: 'scale', 
                animationEasing: 'elasticOut',
                animationDelay: function (idx) {
                    return Math.random() * 200;
                }
            }
        ]
    };
    $.ajax({
        url: BASE_URL + 'video',
        data: {
            day: "20161110" 
        },
        success: function (r) {
            if (r.success) {
                var courseData = []
                for (var i in r.data) {
                    courseData.push({
                        value: r.data[i].times,
                        name: r.data[i].courseName
                    })
                }
                dayAccessOption.series[0].data = courseData.sort(function (a, b) { return a.value - b.value; })
                // 使用刚指定的配置项和数据显示图表。
                dayAccessEcharts.setOption(dayAccessOption);
            }
        },
        error: function(e) {
            console.log(e)
        }
    })
}

var geoCoordMap = {
    '北京':[116.46,39.92]
};

var convertData = function (data) {
    var res = [];
    for (var i = 0; i < data.length; i++) {
        var geoCoord = geoCoordMap[data[i].name];
        if (geoCoord) {
            res.push({
                name: data[i].name,
                value: geoCoord.concat(data[i].value)
            });
        }
    }
    return res;
};

function renderItem(params, api) {
    var coords = [
        [116.7,39.53],
        [103.73,36.03],
        [112.91,27.87],
        [120.65,28.01],
        [119.57,39.95]
    ];
    var points = [];
    for (var i = 0; i < coords.length; i++) {
        points.push(api.coord(coords[i]));
    }
    var color = api.visual('color');

    return {
        type: 'polygon',
        shape: {
            points: echarts.graphic.clipPointsByRect(points, {
                x: params.coordSys.x,
                y: params.coordSys.y,
                width: params.coordSys.width,
                height: params.coordSys.height
            })
        },
        style: api.style({
            fill: color,
            stroke: echarts.color.lift(color)
        })
    };
}
/**
 * 获取每天课程城市topN访问量统计
 */
function findDayCityTopNStat () {
    var cityAccessEcharts = echarts.init(document.getElementById('city_stat'));
    cityTimesOption = {
        // backgroundColor: '#404a59',
        title: {
            text: '地区访问课程情况',
            subtext: 'data from imooc!',
            sublink: 'http://www.imooc.com',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip : {
            trigger: 'item'
        },
        bmap: {
            center: [104.114129, 37.550339],
            zoom: 5,
            roam: true,
            mapStyle: {
                styleJson: [
                        {
                            "featureType": "water",
                            "elementType": "all",
                            "stylers": {
                                "color": "#044161"
                            }
                        },
                        {
                            "featureType": "land",
                            "elementType": "all",
                            "stylers": {
                                "color": "#004981"
                            }
                        },
                        {
                            "featureType": "boundary",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#064f85"
                            }
                        },
                        {
                            "featureType": "railway",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "highway",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#004981"
                            }
                        },
                        {
                            "featureType": "highway",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#005b96",
                                "lightness": 1
                            }
                        },
                        {
                            "featureType": "highway",
                            "elementType": "labels",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "arterial",
                            "elementType": "geometry",
                            "stylers": {
                                "color": "#004981"
                            }
                        },
                        {
                            "featureType": "arterial",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#00508b"
                            }
                        },
                        {
                            "featureType": "poi",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "green",
                            "elementType": "all",
                            "stylers": {
                                "color": "#056197",
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "subway",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "manmade",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "local",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "arterial",
                            "elementType": "labels",
                            "stylers": {
                                "visibility": "off"
                            }
                        },
                        {
                            "featureType": "boundary",
                            "elementType": "geometry.fill",
                            "stylers": {
                                "color": "#029fd4"
                            }
                        },
                        {
                            "featureType": "building",
                            "elementType": "all",
                            "stylers": {
                                "color": "#1a5787"
                            }
                        },
                        {
                            "featureType": "label",
                            "elementType": "all",
                            "stylers": {
                                "visibility": "off"
                            }
                        }
                ]
            }
        },
        series : [
            {
                name: '访问量',
                type: 'scatter',
                coordinateSystem: 'bmap',
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#ddb926'
                    }
                }
            }
        ]
    };
    $.ajax({
        url: BASE_URL + 'video/city/times',
        data: {
            day: "20161110" 
        },
        success: function (r) {
            if (r.success) {
                debugger;
                cityTimesOption.series[0].data = convertData(r.data);
                cityAccessEcharts.setOption(cityTimesOption);
            }
        },
        error: function(e) {
            console.log(e)
        }
    })
}

/**
 * 获取课程每天流量topN统计
 */
function findDayTrafficsTopNStat () {
    var trafficsAccessEcharts = echarts.init(document.getElementById('traffics_stat'));
    option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [{
            data: [120, 200, 150, 80, 70, 110, 130],
            type: 'bar'
        }]
    };
    $.ajax({
        url: BASE_URL + 'video/traffics',
        data: {
            day: "20161110" 
        },
        success: function (r) {
            console.log(r);
        },
        error: function(e) {
            console.log(e)
        }
    })
    trafficsAccessEcharts.setOption(option);
}