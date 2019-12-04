/* 
 * 
 */
var table;

$(window).on('load', function () {


    // DATA TABLES
    // =================================================================
    // Require Data Tables
    // -----------------------------------------------------------------
    // http://www.datatables.net/
    // =================================================================

//    $.fn.DataTable.ext.pager.numbers_length = 5;
//

    table = $('#demo-dt-basic').DataTable();


    $('#demo-dt-basic tbody').on('click', 'tr', function () {
        
//        var data = table.row(this).data();
//        var statusID = data[0];
//        document.getElementById('userStatus').action = contextPath + "/userStatus/editStatus/"+statusID;
//        document.getElementById('userStatus').submit();

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


});

function searchAuthLeave() {
    var sDate = $("#start_date").val();
    var eDate = $("#end_date").val();
    var uCode = $("#userCodeList").val();
    if(uCode == "Please Select One"){
        uCode = '';
    }
  
    $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/leave/getAuthLeaveList?start_date="+sDate +" &end_date=" +eDate +" &user_code=" +uCode).load();
        document.getElementById("res").style.display = 'none';
        document.getElementById("errorMsg").innerHTML = "";
    
    

}


