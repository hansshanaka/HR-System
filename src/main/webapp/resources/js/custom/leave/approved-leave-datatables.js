/* 
 
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

function searchAppLeave() {
    var sDate = $("#start_date").val();
    var eDate = $("#end_date").val();
    var ltypeCode = $("#ltype_id").val();
    
    if(ltypeCode == "Please Select One"){
        ltypeCode = 0;
    }
  
    $('#demo-dt-basic').DataTable().ajax.url(contextPath + "/leave/getApproveLeaveList?start_date="+sDate +" &end_date=" +eDate +" &ltype_id=" +ltypeCode).load();
    
}




