$(function() {
    var graphwidth = $('.widget_graph .inner').width();
    $('.widget_graph .graph').css('height', parseInt(graphwidth/1.7));
    $(window).resize(function() {
        graphwidth = $('.widget_graph .inner').width();
        $('.widget_graph .graph').css('height', parseInt(graphwidth/1.7));
    });


    var d1 =  [[0, 9], [1, 23], [1.8, 7], [2.2, 24], [2.8, 18], [4, 36]];
    var graphholder = $("#graph");

    var plot = $.plot(graphholder, [d1], {
        colors: ["#669146", "#afd8f8", "#cb4b4b", "#4da74d", "#9440ed"],
        xaxis: {
            min: null,
            max: null
        },
        yaxis: {
            autoscaleMargin: 0.02
        },
        series: {
            lines: {
                show: true,
                lineWidth: 2,
                fill: true,
                fillColor: "rgba(188,176,150,0.3)"
            },
            points: {
                show: true,
                radius: 4,
                lineWidth: 2,
                fillColor: "#f6f3ee"
            }
        },
        grid: {
            hoverable: true,
            clickable: true,
            margin: 12,
            color: "#a09d96",
            borderColor: "#b7ab92"
        }
    });

    function showTooltip(x, y, contents) {
        $("<div id='graph-tooltip'>" + contents + "</div>").css({top: y - 36, left: x - 19}).appendTo("body").fadeIn(200);
    };

    var previousPoint = null;
    $("#graph").on("plothover", function (event, pos, item) {

        if (item) {
            if (previousPoint != item.dataIndex) {

                previousPoint = item.dataIndex;

                $("#graph-tooltip").remove();
                var x = item.datapoint[0].toFixed(2),
                    y = item.datapoint[1].toFixed(2);

                showTooltip(item.pageX, item.pageY, '$' + y*100);
            }
        } else {
            $("#graph-tooltip").remove();
            previousPoint = null;
        }
    });
});


$(document).ready(function(){

    new jPlayerPlaylist({
        jPlayer: "#jquery_jplayer_1",
        cssSelectorAncestor: "#jp_container_1"
    }, [
        {
            title:"<ul><li class='item-artist'>3 Doors Down&#160;&#8211;&#160;</li><li class='item-song'>Superman</li></ul>",
            mp3:"http://www.jplayer.org/audio/mp3/TSP-05-Your_face.mp3",
            oga:"http://www.jplayer.org/audio/ogg/TSP-05-Your_face.ogg"
        },
        {
            title:"<ul><li class='item-artist'>Daft Punk&#160;&#8211;&#160;</li><li class='item-song'>Get Lucky</li></ul>",
            mp3:"http://www.jplayer.org/audio/mp3/TSP-07-Cybersonnet.mp3",
            oga:"http://www.jplayer.org/audio/ogg/TSP-07-Cybersonnet.ogg"
        },
        {
            title:"<ul><li class='item-artist'>Justin Timberlake&#160;&#8211;&#160;</li><li class='item-song'>Mirrors</li></ul>",
            mp3:"http://www.jplayer.org/audio/mp3/Miaow-07-Bubble.mp3",
            oga:"http://www.jplayer.org/audio/ogg/Miaow-07-Bubble.ogg"
        }
    ], {
        swfPath: "js",
        supplied: "oga, mp3",
        wmode: "window",
        smoothPlayBar: false,
        keyEnabled: false
    });
});


/*3*/

jQuery(function()
{
    jQuery('.scrollbar').jScrollPane({
        verticalDragMaxHeight: 55,
        verticalDragMinHeight:55
    });

    jQuery('.scrollbar.style2').jScrollPane({
        verticalDragMaxHeight: 30,
        verticalDragMinHeight:30
    });

    jQuery('.scrollbar.style3').jScrollPane({
        verticalDragMaxHeight: 14,
        verticalDragMinHeight:14
    });

    jQuery('.scrollbar.style4').jScrollPane({
        verticalDragMaxHeight: 55,
        verticalDragMinHeight:55
    });
});
/*4*/
jQuery(document).ready(function() {
    jQuery('#contact_name').chosen({ width: "100%" });
});
