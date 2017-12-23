/**
 * Created by zhiran.sun on 2017/5/4.
 */
function editHomework(obj){

    var id=obj;
    location.href="editHomework?homeworkId="+id;
}

function deleteHomework(obj)
{
    if(confirm("确定要删除吗？")) {
        var id = obj;
        location.href = "deleteHomework?homeworkId=" + id;
    }
}

function moreHomework(obj) {
    var id=obj;
    location.href="moreHomeworkInfo?homeworkId="+id;
}

function editUser(obj){

    var id=obj;
    location.href="editUser?userId="+id;
}

function uploadHomework(obj){

    var id=obj;
    location.href="/web-oj/jsp/homework-upload.jsp?homeworkId="+id;
}