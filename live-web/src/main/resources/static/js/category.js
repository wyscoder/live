function categoryList(topage, mycategory) {
    $.ajax({
        url: "/categories/" + mycategory,
        type: 'post',
        dataType: 'json',
        data: {'pageid': topage},
        success: function (data) {
            $('#totalCount').text(data.data.totalCount);
            $('#thePage').text(innerText = data.data.thePage);
            $('#totalPage').text(innerText = data.data.totalPage);

            /*console.log(data.data.thePage);
            console.log(data.data.totalPage);*/
            if (data.data.thePage - 1 >= 1) {
                $('#b').attr("onclick", "categoryList(" + (data.data.thePage - 1) + ",'" + mycategory + "')");
            } else {
                $('#b').attr("onclick", "categoryList(" + 1 + ",'" + mycategory + "')");
            }

            if (data.data.thePage + 1 <= data.data.totalPage) {
                $('#c').attr("onclick", "categoryList(" + (data.data.thePage + 1) + ",'" + mycategory + "')");
            } else {
                $('#c').attr("onclick", "categoryList(" + (data.data.totalPage) + ",'" + mycategory + "')");
            }


            $('tr').empty();
            $('td').empty();
            $('th').empty();
            var rooms = data.data.rooms;


            var head = "<tr><th class=\"text-center\" style=\"background-color: #FFFAFA;\">房间名称</th><th class=\"text-center\" style=\"background-color: #FFFAFA;\">房间号码</th><th class=\"text-center\" style=\"background-color: #FFFAFA;\">主播名称</th><th style=\"background-color: #FFFAFA;\"></th></tr>"
            $('#tableId').append(head);
            for (var i = 0; i < rooms.length; i++) {
                var node = "<tr><td style=\"background-color: #F8F8FF;text-align:center\">" + rooms[i].roomName + "</td><td style=\"background-color: #F8F8FF;text-align:center\">" + rooms[i].roomId + "</td><td style=\"background-color: #F8F8FF;text-align:center\">" + rooms[i].anchorName + "</td><td style=\"background-color: #F8F8FF;text-align:center\">" +
                    "<a href='/live/" + rooms[i].roomId + "'>播放</a>"
                    + "</td></tr>";
                $('#tableId').append(node);
            }

        }
    })
}
