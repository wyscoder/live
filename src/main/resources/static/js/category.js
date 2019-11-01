function previous() {
    $.ajax({
        url:"/categories/{category}",
        type:"post",
        data:{pageid:1},
        dataType:'text',
        success:function (data) {
            console.log(data);
        }
    })
}