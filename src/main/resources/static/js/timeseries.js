  nv.addGraph(function() {
    var chart = nv.models.lineWithFocusChart()
    .useInteractiveGuideline(false)
    .clipEdge(false);

    chart.x2Axis
        .tickFormat(function(d) { return d3.time.format('%Y-%m-%d')(new Date(d)); })

    chart.xAxis
        .tickFormat(function(d) { return d3.time.format('%m-%d %H%M')(new Date(d)); })

    chart.xScale(d3.time.scale());

    chart.yAxis
        .tickFormat(d3.format(',.2f'));

    chart.y2Axis
        .tickFormat(d3.format(',.2f'));

    d3.select('#chart svg')
        .datum(testData1())
        .transition().duration(10)
        .call(chart);

    nv.utils.windowResize(chart.update);

    return chart;
    });
