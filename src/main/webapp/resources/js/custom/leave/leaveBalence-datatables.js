/* 
 * leaveBalence-datatables
 */

var table;

$(window).on('load', function () {


    table = $('#demo-dt-basic').DataTable();

    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        var data = table.row(this).data();       
        var id = data[3];
        
        document.getElementById('leaveBal').action = contextPath + "/leave/editLeaveBalance/"+id;
        document.getElementById('leaveBal').submit();

    });

    var rowSelection = table;
    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        if ($(this).hasClass('selected')) {
            $(this).removeClass('selected');
        }
        else {
            rowSelection.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    });

    // =================================================================


});


function searchLeaveBalance() {
    var userCode = $("#user_code").val();
    var lType = $("#ltype_id").val();


    if (userCode != "Please Select One") {
        $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/leave/leaveBalenceList?user_code=" + userCode + "&ltype_id=" + lType).load();
        document.getElementById("res").style.display = 'none';
        document.getElementById("errorMsg").innerHTML = "";
    } else {
        document.getElementById("res").style.display = 'block';
        document.getElementById("errorMsg").innerHTML = "Please Select User Code";
        window.location.hash = '#errorMsg';
    }



}

